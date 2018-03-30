package com.tencent.tsf.product.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tencent.tsf.product.Product;

/**
 * 打桩的产品数据，业务系统中请使用持久化替代此部分内容
 *
 * @author hongweizhu
 */
public class ProductRepository {

    /**
     * 产品清单，key：产品Id，value：产品对象
     */
    private static Map<String, Product> PRODUCT_MAP = new HashMap<>();

    /**
     * 初始化打桩数据
     */
    public static void init() {
        for (int i = 0; i < 10; i++) {
            PRODUCT_MAP.put("product-" + i, new Product("product-" + i, "productName-" + i));
        }
    }

    /**
     * 根据产品ID获取产品
     * 
     * @param productId 产品ID
     * @return 产品
     */
    public static Product getProduct(String productId) {
        return PRODUCT_MAP.get(productId);
    }

    /**
     * 获取产品列表
     * 
     * @return 产品列表
     */
    public static List<Product> getProductList() {
        return new ArrayList<Product>(PRODUCT_MAP.values());
    }
}
