package com.tencent.tsf.order.service.impl;

import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tencent.tsf.order.Order;
import com.tencent.tsf.order.data.OrderRepository;
import com.tencent.tsf.order.proxy.ProductService;
import com.tencent.tsf.order.service.IOrderService;

/**
 * 订单服务实现
 *
 * @author hongweizhu
 */
@Service
public class OrderService implements IOrderService {

    private static final Logger LOG = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private ProductService productService;

    @Override
    public Boolean saveOrder(Order order) {
        // 检查产品存在性
        if (productService.checkExists(order.getProductId())) {
            return OrderRepository.save(order);
        } else {
            LOG.info(String.format("[TSF Order] Product [ID: %s] not exists", order.getProductId()));
            return false;
        }
    }

    @Override
    public Order findOne(String orderId) {
        return OrderRepository.getOrder(orderId);
    }

    @Override
    public List<Order> findAll() {
        List<Order> list = OrderRepository.getOrderList();
        // 按订单号排序，降序
        list.sort(new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                return Integer.parseInt(o2.getOrderId().substring(6)) - Integer.parseInt(o1.getOrderId().substring(6));
            }
        });
        return list;
    }

}
