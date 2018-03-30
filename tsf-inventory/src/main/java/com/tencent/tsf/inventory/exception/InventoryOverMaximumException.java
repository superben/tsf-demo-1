package com.tencent.tsf.inventory.exception;

/**
 * 库存超过最大值异常<br>
 * 
 * 此类业务异常在业务系统中最好定义一个统一的基类，便于进行通用处理
 *
 * @author hongweizhu
 */
public class InventoryOverMaximumException extends RuntimeException {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -1452283299547288169L;

    /**
     * 库存超过最大值异常
     * 
     * @param productId 产品ID
     * @param maximumInventory 库存最大值
     */
    public InventoryOverMaximumException(String productId, Long maximumInventory) {
        super(String.format("%s产品库存不能超过上限：%s", productId, maximumInventory));
    }
}
