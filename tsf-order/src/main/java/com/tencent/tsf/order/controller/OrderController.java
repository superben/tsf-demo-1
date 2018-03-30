package com.tencent.tsf.order.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tencent.tsf.order.Order;
import com.tencent.tsf.order.SalesOrder;
import com.tencent.tsf.order.SupplyOrder;
import com.tencent.tsf.order.proxy.InventoryService;
import com.tencent.tsf.order.proxy.Product;
import com.tencent.tsf.order.proxy.ProductService;
import com.tencent.tsf.order.service.IOrderService;
import com.tencent.tsf.order.util.TsfMapUtil;

/**
 * 订单控制器
 *
 * @author hongweizhu
 */
@RestController
public class OrderController {

    private static final Logger LOG = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private IOrderService orderService;

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private ProductService productService;

    /**
     * 销售产品
     * 
     * @param salesOrder 销售订单
     * @return 结果
     */
    @RequestMapping("/order/sales")
    public String sales(@RequestBody SalesOrder salesOrder) {
        LOG.info(String.format("[TSF Order] Sales product [ID: %s], quantity: %s", salesOrder.getProductId(),
                salesOrder.getQuantity()));
        // 调用出库
        if (inventoryService.outbound(salesOrder)) {
            // 成功后记录订单
            orderService.saveOrder(salesOrder);
            return "下单完成";
        } else {
            return "库存不足";
        }
    }

    /**
     * 供应链补货
     * 
     * @param supplyOrder 供应订单
     * @return 补货结构
     */
    @RequestMapping("/order/supply")
    public String supply(@RequestBody SupplyOrder supplyOrder) {
        LOG.info(String.format("[TSF Order] Supply product [ID: %s], quantity: %s", supplyOrder.getProductId(),
                supplyOrder.getQuantity()));
        // 调用入库
        if (inventoryService.inbound(supplyOrder)) {
            // 成功后记录订单
            orderService.saveOrder(supplyOrder);
            return "补货完成";
        } else {
            return "补货失败";
        }
    }

    /**
     * 订单查询
     * 
     * @param orderId 订单ID
     * @return 订单详情
     */
    @RequestMapping("/order/{orderId}")
    public Map<String, Object> findOne(@PathVariable("orderId") String orderId) {
        LOG.info(String.format("[TSF Order] Query order [ID: %s]", orderId));
        // 提取订单
        Order order = orderService.findOne(orderId);
        // 提取产品
        Product product = productService.findOne(order.getProductId());
        // 合并对象
        return TsfMapUtil.getMapValue(true, order, product);
    }

    /**
     * 订单列表查询
     * 
     * @return 订单列表
     */
    @RequestMapping("/order/list")
    public List<Map<String, Object>> findList() {
        LOG.info("[TSF Order] Query order list.");
        // 提取订单
        List<Order> orderList = orderService.findAll();
        // 提取产品
        List<Product> productList = productService.findAll();
        // hash产品，待用
        Map<String, Product> productMap = new HashMap<>();
        for (Product p : productList) {
            productMap.put(p.getProductId(), p);
        }
        // 合并结果
        List<Map<String, Object>> result = new ArrayList<>();
        for (Order order : orderList) {
            result.add(TsfMapUtil.getMapValue(true, order, productMap.get(order.getProductId())));
        }
        return result;
    }
}
