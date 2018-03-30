package com.tencent.tsf.product.service;

import java.util.List;

import com.tencent.tsf.product.Product;

/**
 * 产品服务接口
 *
 * @author hongweizhu
 */
public interface IProductService {
    /**
     * 获取产品列表
     * 
     * @return 产品列表
     */
    public List<Product> findAll();

    /**
     * 根据产品ID获取产品信息
     * 
     * @param productId 需要查询的产品ID
     * @return 产品
     */
    public Product findOne(String productId);
}
