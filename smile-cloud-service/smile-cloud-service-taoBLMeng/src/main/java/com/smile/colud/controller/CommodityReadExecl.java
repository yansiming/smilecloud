package com.smile.colud.controller;

import com.smile.colud.model.Item;
import com.smile.colud.service.CommodityReadExeclService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author yansm
 * @version 1.0
 * @date 06/21/2021 11:22
 */
@Controller
public class CommodityReadExecl {

    @Autowired
    private CommodityReadExeclService ommodityReadExeclService;
    @RequestMapping("/readEcexl")
    private ModelAndView  commodityReadExecl(ModelAndView modelAndView, @RequestParam("execlName") String execlName){
        List<Item>  items =  ommodityReadExeclService.commodityReadExecl(execlName);
//        model.addAttribute("items", items);
        modelAndView.setViewName("index");
        modelAndView.addObject("items",items);
        return modelAndView;
    }

}
