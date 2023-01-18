package com.smile.colud.controller;

import com.smile.colud.service.TaoKeApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yansm
 * @version 1.0
 * @date 06/23/2021 21:47
 */
@RestController
public class TaoKeApiController {
    /***
     * 淘口令转换
     */
    @Autowired
    private TaoKeApiService taoKeApiService;
    @GetMapping("/getTaoPassword")
    private String getTaoPassword(@RequestParam("taoPassword") String taoPassword){
        return  taoKeApiService.getTaoPassword(taoPassword);
    }

}
