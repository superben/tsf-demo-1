package com.tencent.tsf.order;

/**
 * 销售订单
 *
 * @author hongweizhu
 */
public class SalesOrder extends Order {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -8456942789826833433L;

    /**
     * 空构造函数默认设置OrderType为Sales
     */
    public SalesOrder() {
        super();
        super.setOrderType("Sales");
    }

    /**
     * 创建销售订单构造函数
     * 
     * @param orderId 订单ID
     * @param productId 产品ID
     * @param quantity 数量
     */
    public SalesOrder(String orderId, String productId, Long quantity) {
        super(orderId, productId, "Sales", quantity);
    }

}
