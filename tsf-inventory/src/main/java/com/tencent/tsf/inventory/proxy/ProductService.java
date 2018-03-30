package com.tencent.tsf.inventory.proxy;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 产品服务接口（远程服务客户端）
 *
 * @author hongweizhu
 */
@FeignClient("tsf-product")
public interface ProductService {
    /**
     * 获取产品
     * 
     * @param productId 需要查询的产品ID
     * @return 产品内容
     */
    @RequestMapping("/product/{productId}")
    public Product findOne(@PathVariable("productId") String productId);

    /**
     * 获取产品列表
     * 
     * @return 产品列表
     */
    @RequestMapping("/product/list")
    public List<Product> findAll();

    /**
     * 检查产品是否存在
     * 
     * @param productId 产品ID
     * @return true：产品存在；false：产品不存在
     */
    @RequestMapping("/product/check/{productId}")
    public Boolean checkExists(@PathVariable("productId") String productId);

}
