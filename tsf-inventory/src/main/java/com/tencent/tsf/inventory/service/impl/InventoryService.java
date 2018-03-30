package com.tencent.tsf.inventory.service.impl;

import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tencent.tsf.inventory.Inventory;
import com.tencent.tsf.inventory.config.InventoryConfiguration;
import com.tencent.tsf.inventory.data.InventoryRepository;
import com.tencent.tsf.inventory.exception.InventoryNotEnoughException;
import com.tencent.tsf.inventory.exception.InventoryOverMaximumException;
import com.tencent.tsf.inventory.proxy.ProductService;
import com.tencent.tsf.inventory.service.IInventoryService;

/**
 * 库存服务实现
 *
 * @author hongweizhu
 */
@Service
public class InventoryService implements IInventoryService {

    private static final Logger LOG = LoggerFactory.getLogger(InventoryService.class);

    private static Object INBOUND_LOCK = new Object();
    private static Object OUTBOUND_LOCK = new Object();

    /**
     * 加载库存配置
     */
    @Autowired
    private InventoryConfiguration inventoryConfig;

    /**
     * 加载产品服务
     */
    @Autowired
    private ProductService productService;

    @Override
    public Inventory inbound(Inventory inbound) {
        // 入库业务逻辑
        synchronized (INBOUND_LOCK) {
            // 检查产品存在性
            if (productService.checkExists(inbound.getProductId())) {
                Inventory result = InventoryRepository.getInventory(inbound.getProductId());
                // 检测库存：不允许超过最大库存
                if (result.getQuantity() + inbound.getQuantity() > inventoryConfig.getMaxInventory()) {
                    throw new InventoryOverMaximumException(inbound.getProductId(), inventoryConfig.getMaxInventory());
                } else {
                    result.setQuantity(result.getQuantity() + inbound.getQuantity());
                }
                return result;
            } else {
                LOG.info(String.format("[TSF Inventory] Product [ID: %s] not exists", inbound.getProductId()));
                return null;
            }
        }
    }

    @Override
    public Inventory outbound(Inventory outbound) {
        // 出库业务逻辑
        synchronized (OUTBOUND_LOCK) {
            // 检查产品存在性
            if (productService.checkExists(outbound.getProductId())) {
                Inventory result = InventoryRepository.getInventory(outbound.getProductId());
                // 检测库存：不允许库存小于0
                if (result.getQuantity() < outbound.getQuantity()) {
                    throw new InventoryNotEnoughException(outbound.getProductId());
                } else {
                    result.setQuantity(result.getQuantity() - outbound.getQuantity());
                }
                return result;
            } else {
                LOG.info(String.format("[TSF Inventory] Product [ID: %s] not exists", outbound.getProductId()));
                return null;
            }
        }
    }

    @Override
    public Inventory findOne(String productId) {
        // 直接返回
        return InventoryRepository.getInventory(productId);
    }

    @Override
    public List<Inventory> findAll() {
        List<Inventory> list = InventoryRepository.getInventoryList();
        // 按产品ID排序，升序
        list.sort(new Comparator<Inventory>() {
            @Override
            public int compare(Inventory o1, Inventory o2) {
                return Integer.parseInt(o1.getProductId().substring(8))
                        - Integer.parseInt(o2.getProductId().substring(8));
            }
        });
        // 直接返回
        return list;
    }

}
