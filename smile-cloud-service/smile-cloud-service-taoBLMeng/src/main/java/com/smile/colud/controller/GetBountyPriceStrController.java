package com.smile.colud.controller;

import com.smile.colud.service.CommodityReadExeclService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: yansiming
 * Date: 2023/1/18 23:55
 * Describe:
 */
@RestController
public class GetBountyPriceStrController {
    @Autowired
    private CommodityReadExeclService ommodityReadExeclService;
    /**
     * 输入佣金计算出奖励金
     */
    @GetMapping("/getBountyPriceStr")
    private String getBountyPriceStr(@RequestParam("commissionStr") String commissionStr){
        return  ommodityReadExeclService.getBountyPriceStr(commissionStr);
    }
}
