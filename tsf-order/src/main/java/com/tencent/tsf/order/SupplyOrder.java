package com.tencent.tsf.order;

/**
 * 供应订单
 *
 * @author hongweizhu
 */
public class SupplyOrder extends Order {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -5780787025918583324L;

    /**
     * 空构造函数默认设置OrderType为Supply
     */
    public SupplyOrder() {
        super();
        super.setOrderType("Supply");
    }

    /**
     * 创建供应订单构造函数
     * 
     * @param orderId 订单ID
     * @param productId 产品ID
     * @param quantity 数量
     */
    public SupplyOrder(String orderId, String productId, Long quantity) {
        super(orderId, productId, "Supply", quantity);
    }
}
