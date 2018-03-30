package com.tencent.tsf.inventory.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tencent.tsf.inventory.Inventory;

/**
 * 打桩的库存数据，业务系统中请使用持久化替代此部分内容
 *
 * @author hongweizhu
 */
public class InventoryRepository {

    /**
     * 库存清单，key：产品Id，value：库存对象
     */
    private static Map<String, Inventory> INVENTORY_MAP = new HashMap<>();

    /**
     * 初始化数据
     */
    public static void init() {
        for (int i = 0; i < 10; i++) {
            INVENTORY_MAP.put("product-" + i, new Inventory("product-" + i, i * 10L));
        }
    }

    /**
     * 获取所有库存的列表
     * 
     * @return 返回库存列表
     */
    public static List<Inventory> getInventoryList() {
        return new ArrayList<Inventory>(INVENTORY_MAP.values());
    }

    /**
     * 根据productId获取库存
     * 
     * @param productId 产品ID
     * @return 库存信息
     */
    public static Inventory getInventory(String productId) {
        // 伪造新入库的内容
        if (!INVENTORY_MAP.containsKey(productId)) {
            INVENTORY_MAP.put(productId, new Inventory(productId, 0L));
        }
        return INVENTORY_MAP.get(productId);
    }

}
