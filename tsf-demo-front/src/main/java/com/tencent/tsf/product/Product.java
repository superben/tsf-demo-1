package com.tencent.tsf.product;

import java.io.Serializable;

/**
 * 产品
 *
 * @author hongweizhu
 */
public class Product implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 8700183116652587177L;

    /**
     * 空构造函数
     */
    public Product() {

    }

    /**
     * 产品构造函数
     * 
     * @param productId 产品ID
     * @param productName 产品名称
     */
    public Product(String productId, String productName) {
        this.productId = productId;
        this.productName = productName;
    }

    /**
     * 产品ID
     */
    private String productId;
    /**
     * 产品名称
     */
    private String productName;

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
     * 获取产品名称
     * 
     * @return 产品名称
     */
    public String getProductName() {
        return productName;
    }

    /**
     * 设置产品名称
     * 
     * @param productName 产品名称
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

}
