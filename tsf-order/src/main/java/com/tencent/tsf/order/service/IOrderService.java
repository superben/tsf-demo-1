package com.tencent.tsf.order.service;

import java.util.List;

import com.tencent.tsf.order.Order;

/**
 * 订单服务接口
 *
 * @author hongweizhu
 */
public interface IOrderService {

    /**
     * 记录订单
     * 
     * @param order 订单
     * @return true：记录成功；false：记录失败
     */
    public Boolean saveOrder(Order order);

    /**
     * 查询订单详情
     * 
     * @param orderId 订单ID
     * @return 订单
     */
    public Order findOne(String orderId);

    /**
     * 查询订单列表
     * 
     * @return 订单列表
     */
    public List<Order> findAll();
}
