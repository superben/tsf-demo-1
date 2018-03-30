package com.tencent.tsf.order.proxy;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tencent.tsf.order.SalesOrder;
import com.tencent.tsf.order.SupplyOrder;

/**
 * 库存服务接口
 *
 * @author hongweizhu
 */
@FeignClient("tsf-inventory")
public interface InventoryService {

    /**
     * 产品入库
     * 
     * @param supplyOrder 供应订单
     * 
     * @return true：入库成功；false：入库失败
     */
    @RequestMapping("/inventory/inbound")
    public Boolean inbound(SupplyOrder supplyOrder);

    /**
     * 产品出库
     * 
     * @param salesOrder 销售订单
     * 
     * @return true：出库成功；false：出库失败
     */
    @RequestMapping("/inventory/outbound")
    public Boolean outbound(SalesOrder salesOrder);
}
