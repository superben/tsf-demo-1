package com.tencent.tsf.inventory;

import java.io.Serializable;

/**
 * 库存信息
 *
 * @author hongweizhu
 */
public class Inventory implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -7974634202814528775L;

    /**
     * 空构造函数
     */
    public Inventory() {
    }

    /**
     * 创建库存信息
     * 
     * @param productId 产品ID
     * @param quantity 存量
     */
    public Inventory(String productId, Long quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    /**
     * 产品ID
     */
    private String productId;
    /**
     * 存量
     */
    private Long quantity;

    /**
     * 获取库存的产品ID
     * 
     * @return 产品ID
     */
    public String getProductId() {
        return productId;
    }

    /**
     * 设置库存的产品ID
     * 
     * @param productId 产品ID
     */
    public void setProductId(String productId) {
        this.productId = productId;
    }

    /**
     * 获取库存的存量
     * 
     * @return 存量
     */
    public Long getQuantity() {
        return quantity;
    }

    /**
     * 设置库存的存量
     * 
     * @param quantity 存量
     */
    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

}
