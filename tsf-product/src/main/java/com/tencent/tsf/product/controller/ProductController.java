package com.tencent.tsf.product.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tencent.tsf.product.Product;
import com.tencent.tsf.product.service.IProductService;

/**
 * 产品控制器
 *
 * @author hongweizhu
 */
@RestController
public class ProductController {
    private static final Logger LOG = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private IProductService productService;

    /**
     * 获取产品列表
     * 
     * @return 产品列表
     */
    @RequestMapping("/product/list")
    public List<Product> findAll() {
        LOG.info("[TSF Product] Query Product List.");
        return productService.findAll();
    }

    /**
     * 根据产品ID获取产品信息
     * 
     * @param product 需要查询的产品，Product对象需要包含productId属性
     * @return 产品
     */
    @RequestMapping("/product/{productId}")
    public Product findOne(@PathVariable("productId") String productId) {
        LOG.info(String.format("[TSF Product] Query Product [ID: %s].", productId));
        return productService.findOne(productId);
    }

    /**
     * 检查产品是否存在
     * 
     * @param productId 产品ID
     * @return true：产品存在；false：产品不存在
     */
    @RequestMapping("/product/check/{productId}")
    public Boolean checkExists(@PathVariable("productId") String productId) {
        return null != findOne(productId);
    }

}
