package com.tencent.tsf.product.service.impl;

import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tencent.tsf.product.Product;
import com.tencent.tsf.product.data.ProductRepository;
import com.tencent.tsf.product.service.IProductService;

/**
 * 产品服务实现类
 *
 * @author hongweizhu
 */
@Service
public class ProductService implements IProductService {

    @Override
    public List<Product> findAll() {
        List<Product> list = ProductRepository.getProductList();
        // 按产品ID排序，升序
        list.sort(new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return Integer.parseInt(o1.getProductId().substring(8))
                        - Integer.parseInt(o2.getProductId().substring(8));
            }
        });
        return list;
    }

    @Override
    public Product findOne(String productId) {
        return ProductRepository.getProduct(productId);
    }

}
