package com.tencent.tsf.order.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tencent.tsf.order.Order;

/**
 * 打桩的订单数据，业务系统中请使用持久化替代此部分内容
 *
 * @author hongweizhu
 */
public class OrderRepository {

    private static long idx = 10L;

    public static Long getId() {
        return idx++;
    }

    /**
     * 订单清单，key：订单Id，value：订单对象
     */
    private static Map<String, Order> ORDER_MAP = new HashMap<>();

    /**
     * 初始化打桩数据
     */
    public static void init() {
        for (int i = 0; i < 10; i++) {
            ORDER_MAP.put("order-" + i,
                    new Order("order-" + i, "product-" + i, i % 2 == 0 ? "Sales" : "Supply", i * 5L));
        }
    }

    /**
     * 记录订单
     * 
     * @param order 订单
     * @return true：记录成功；false：记录失败；
     */
    public static Boolean save(Order order) {
        // 补充ID
        if (null == order.getOrderId()) {
            order.setOrderId("order-" + getId());
        }
        // 记录
        ORDER_MAP.put(order.getOrderId(), order);
        return true;
    }

    /**
     * 根据产品ID获取产品
     * 
     * @param productId 产品ID
     * @return 产品
     */
    public static Order getOrder(String orderId) {
        return ORDER_MAP.get(orderId);
    }

    /**
     * 获取产品列表
     * 
     * @return 产品列表
     */
    public static List<Order> getOrderList() {
        return new ArrayList<Order>(ORDER_MAP.values());
    }

}
