package com.smile.colud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.smile.colud.service.ExeclToMysql;
import com.smile.colud.service.MysqlData;

@RestController
public class CouponController {

	@GetMapping(value = "/test")
	public String test() {
		return "true";
	}
	
	@GetMapping(value = "/getExeclToMysql")
	public String getExeclToMysql() {
		//获取execl数据
		ExeclToMysql execlToMysql = new ExeclToMysql();
		try {
			execlToMysql.insterMysql();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "成功";
	}
	 
	@GetMapping(value = "/getMysqlData")
	public String getMysqlData() {
		//获取execl数据
		MysqlData mysqlData = new MysqlData();
		String mysqlData2 ="";
		try {
			 mysqlData2 = mysqlData.getMysqlData();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mysqlData2;
	}

    
  
   
}