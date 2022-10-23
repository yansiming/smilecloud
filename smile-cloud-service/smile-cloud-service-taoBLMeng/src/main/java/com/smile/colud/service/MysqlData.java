package com.smile.colud.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.mysql.jdbc.Connection;
import com.smile.colud.model.Taoyhq;
import com.smile.colud.util.ConnectionUtil;

public class MysqlData {

	public String getMysqlData() {
		// TODO Auto-generated method stub
		String sql = "select * from taoyhq limit 0,50";
        Statement stmt = null; 
        ConnectionUtil connectionUtil = new ConnectionUtil();
        Connection conn = ConnectionUtil.getConn();
		ArrayList<Object> taoyhqList = new ArrayList<>();

        try {
			Statement createStatement = conn.createStatement();
			ResultSet executeQuery = createStatement.executeQuery(sql);
			HashMap<Object, Object> map = new HashMap<>();
			Taoyhq taoyhq = new Taoyhq();
			while( executeQuery.next()){
//				map.put("a",executeQuery.getString(1));
//				map.put("b",executeQuery.getString(2));
//				map.put("c",executeQuery.getString(3));
//				map.put("d",executeQuery.getString(4));
//				map.put("e",executeQuery.getString(5));
//				map.put("f",executeQuery.getString(6));
//				map.put("g",executeQuery.getString(7));
//				map.put("h",executeQuery.getString(8));
//				map.put("i",executeQuery.getString(9));
//				map.put("j",executeQuery.getString(10));
//				map.put("k",executeQuery.getString(11));
//				map.put("l",executeQuery.getString(12));
//				map.put("m",executeQuery.getString(13));
//				map.put("n",executeQuery.getString(14));
//				map.put("o",executeQuery.getString(15));
//				map.put("p",executeQuery.getString(16));
//				map.put("q",executeQuery.getString(17));
//				map.put("r",executeQuery.getString(18));
//				map.put("s",executeQuery.getString(19));
//				map.put("t",executeQuery.getString(20));
//				map.put("u",executeQuery.getString(21));
//				map.put("v",executeQuery.getString(22));
				taoyhq.setA(executeQuery.getString(1));
				taoyhq.setB(executeQuery.getString(2));
				taoyhq.setC(executeQuery.getString(3));
				taoyhq.setD(executeQuery.getString(4));
				taoyhq.setE(executeQuery.getString(5));
				taoyhq.setF(executeQuery.getString(6));
				taoyhq.setG(executeQuery.getString(7));
				taoyhq.setH(executeQuery.getString(8));
				taoyhq.setI(executeQuery.getString(9));
				taoyhq.setJ(executeQuery.getString(10));
				taoyhq.setK(executeQuery.getString(11));
				taoyhq.setL(executeQuery.getString(12));
				taoyhq.setN(executeQuery.getString(13));
				taoyhq.setM(executeQuery.getString(14));
				taoyhq.setO(executeQuery.getString(15));
				taoyhq.setP(executeQuery.getString(16));
				taoyhq.setQ(executeQuery.getString(17));
				taoyhq.setR(executeQuery.getString(18));
				taoyhq.setS(executeQuery.getString(19));
				taoyhq.setT(executeQuery.getString(20));
				taoyhq.setU(executeQuery.getString(21));
				taoyhq.setV(executeQuery.getString(22));
				taoyhqList.add(taoyhq);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return taoyhqList.toString();
		
	}

}
