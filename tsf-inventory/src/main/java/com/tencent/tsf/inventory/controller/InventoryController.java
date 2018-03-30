package com.tencent.tsf.inventory.controller;

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

import com.tencent.tsf.inventory.Inventory;
import com.tencent.tsf.inventory.exception.InventoryNotEnoughException;
import com.tencent.tsf.inventory.exception.InventoryOverMaximumException;
import com.tencent.tsf.inventory.proxy.Product;
import com.tencent.tsf.inventory.proxy.ProductService;
import com.tencent.tsf.inventory.service.IInventoryService;
import com.tencent.tsf.util.TsfMapUtil;

/**
 * 库存控制器
 *
 * @author hongweizhu
 */
@RestController
public class InventoryController {

    private static final Logger LOG = LoggerFactory.getLogger(InventoryController.class);

    /**
     * 库存服务
     */
    @Autowired
    private IInventoryService inventoryService;

    /**
     * 产品服务：远程调用
     */
    @Autowired
    private ProductService productService;

    /**
     * 产品入库接口
     * 
     * @param inventory 入库库存
     *            <table>
     *            <tr>
     *            <td>productId</td>
     *            <td>String</td>
     *            <td>产品ID</td>
     *            </tr>
     *            <tr>
     *            <td>quantity</td>
     *            <td>Long</td>
     *            <td>入库数量</td>
     *            </tr>
     *            <table>
     * 
     * @return true：入库成功；false：入库失败
     */
    @RequestMapping("/inventory/inbound")
    public Boolean inbound(@RequestBody Inventory inventory) {
        LOG.info(String.format("[TSF Inventory] Inbound product [ID: %s], quantity: %s", inventory.getProductId(),
                inventory.getQuantity()));
        // 参数转换/校验等逻辑...
        try {
            // 入库服务
            return null != inventoryService.inbound(inventory);
        } catch (InventoryOverMaximumException e) {
            LOG.error(e.getMessage());
            return false;
        }
    }

    /**
     * 产品出库
     * 
     * @param inventory 出库库存
     *            <table>
     *            <tr>
     *            <td>productId</td>
     *            <td>String</td>
     *            <td>产品ID</td>
     *            </tr>
     *            <tr>
     *            <td>quantity</td>
     *            <td>Long</td>
     *            <td>出库数量</td>
     *            </tr>
     *            <table>
     * 
     * @return true：出库成功；false：出库失败
     */
    @RequestMapping("/inventory/outbound")
    public Boolean outbound(@RequestBody Inventory inventory) {
        LOG.info(String.format("[TSF Inventory] Outbound product [ID: %s], quantity: %s", inventory.getProductId(),
                inventory.getQuantity()));
        // 参数转换/校验等逻辑...
        try {
            // 出库服务
            return null != inventoryService.outbound(inventory);
        } catch (InventoryNotEnoughException e) {
            LOG.error(e.getMessage());
            return false;
        }
    }

    /**
     * 产品库存
     * 
     * @param productId 产品ID
     * @return 产品库存信息
     *         <table>
     *         <tr>
     *         <td>productId</td>
     *         <td>String</td>
     *         <td>产品ID</td>
     *         </tr>
     *         <tr>
     *         <td>productName</td>
     *         <td>String</td>
     *         <td>产品名称</td>
     *         </tr>
     *         <tr>
     *         <td>quantity</td>
     *         <td>Long</td>
     *         <td>产品库存</td>
     *         </tr>
     *         <table>
     */
    @RequestMapping("/inventory/{productId}")
    public Map<String, Object> findOne(@PathVariable("productId") String productId) {
        LOG.info(String.format("[TSF Inventory] Query inventory of product [ID: %s]", productId));
        // 提取产品信息
        Product product = productService.findOne(productId);
        // 提取产品库存
        Inventory inventory = inventoryService.findOne(productId);
        // 组装返回值
        return TsfMapUtil.getMapValue(true, inventory, product);
    }

    /**
     * 查询产品库存列表
     * 
     * @return 产品库存信息
     *         <table>
     *         <tr>
     *         <td>productId</td>
     *         <td>String</td>
     *         <td>产品ID</td>
     *         </tr>
     *         <tr>
     *         <td>productName</td>
     *         <td>String</td>
     *         <td>产品名称</td>
     *         </tr>
     *         <tr>
     *         <td>quantity</td>
     *         <td>Long</td>
     *         <td>产品库存</td>
     *         </tr>
     *         <table>
     */
    @RequestMapping("/inventory/list")
    public List<Map<String, Object>> findAll() {
        LOG.info(String.format("[TSF Inventory] Query inventory list"));
        // 提取订单
        List<Inventory> inventoryList = inventoryService.findAll();
        // 提取产品
        List<Product> productList = productService.findAll();
        // hash产品，待用
        Map<String, Product> productMap = new HashMap<>();
        for (Product p : productList) {
            productMap.put(p.getProductId(), p);
        }
        // 合并结果
        List<Map<String, Object>> result = new ArrayList<>();
        for (Inventory inventory : inventoryList) {
            if (productMap.containsKey(inventory.getProductId())) {
                result.add(TsfMapUtil.getMapValue(true, inventory, productMap.get(inventory.getProductId())));
            }
        }
        return result;
    }

}
