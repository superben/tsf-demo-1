package com.tencent.tsf.inventory.exception;

/**
 * 库存不足异常<br>
 * 
 * 此类业务异常在业务系统中最好定义一个统一的基类，便于进行通用处理
 *
 * @author hongweizhu
 */
public class InventoryNotEnoughException extends RuntimeException {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 5307080447385531897L;

    /**
     * 库存不足异常
     * 
     * @param productId 库存不足的产品ID
     */
    public InventoryNotEnoughException(String productId) {
        super(String.format("%s产品库存不足", productId));
    }
}
