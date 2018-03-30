package com.tencent.tsf.inventory.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@ConfigurationProperties("tsf.inventory")
@Component
@RefreshScope
public class InventoryConfiguration {

    /**
     * 最大库存
     */
    private Long maxInventory = 100L;

    /**
     * 获取最大库存
     * 
     * @return 最大库存
     */
    public Long getMaxInventory() {
        return maxInventory;
    }

    /**
     * 设置最大库存
     * 
     * @param maxInventory 最大库存
     */
    public void setMaxInventory(Long maxInventory) {
        this.maxInventory = maxInventory;
    }

}
