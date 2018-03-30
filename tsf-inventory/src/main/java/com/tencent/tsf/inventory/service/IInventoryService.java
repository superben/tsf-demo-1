package com.tencent.tsf.inventory.service;

import java.util.List;

import com.tencent.tsf.inventory.Inventory;

/**
 * 库存服务接口
 *
 * @author hongweizhu
 */
public interface IInventoryService {

    /**
     * 入库
     * 
     * @param inventory 入库库存
     * @return 入库后库存
     */
    public Inventory inbound(Inventory inventory);

    /**
     * 出库
     * 
     * @param inventory 出库库存
     * @return 出库后库存
     */
    public Inventory outbound(Inventory inventory);

    /**
     * 查询产品库存
     * 
     * @param productId 产品ID
     * @return 库存
     */
    public Inventory findOne(String productId);

    /**
     * 查询库存列表
     * 
     * @return 库存列表
     */
    public List<Inventory> findAll();

}
