package com.tencent.tsf.order;

import java.io.Serializable;

/**
 * 订单
 *
 * @author hongweizhu
 */
public class Order implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -3602686277410639349L;

    /**
     * 订单ID
     */
    private String orderId;
    /**
     * 产品ID
     */
    private String productId;
    /**
     * 订单类型
     */
    private String orderType;
    /**
     * 数量
     */
    private Long quantity;

    /**
     * 空构造函数
     */
    public Order() {
    }

    /**
     * 创建订单的构造函数
     * 
     * @param orderId 订单ID
     * @param productId 产品ID
     * @param orderType 订单类型
     * @param quantity 数量
     */
    public Order(String orderId, String productId, String orderType, Long quantity) {
        this.orderId = orderId;
        this.productId = productId;
        this.orderType = orderType;
        this.quantity = quantity;
    }

    /**
     * 获取订单ID
     * 
     * @return 订单ID
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * 设置订单ID
     * 
     * @param orderId 订单ID
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取产品ID
     * 
     * @return 产品ID
     */
    public String getProductId() {
        return productId;
    }

    /**
     * 设置产品ID
     * 
     * @param productId 产品ID
     */
    public void setProductId(String productId) {
        this.productId = productId;
    }

    /**
     * 获取订单类型
     * 
     * @return 订单类型
     */
    public String getOrderType() {
        return orderType;
    }

    /**
     * 设置订单类型
     * 
     * @param orderType 订单类型
     */
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    /**
     * 获取数量
     * 
     * @return 数量
     */
    public Long getQuantity() {
        return quantity;
    }

    /**
     * 设置数量
     * 
     * @param quantity 数量
     */
    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

}
