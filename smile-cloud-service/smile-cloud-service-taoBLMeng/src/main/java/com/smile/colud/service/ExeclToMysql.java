package com.smile.colud.service;

import java.io.File;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.smile.colud.model.Taoyhq;
import com.smile.colud.util.ConnectionUtil;

import jxl.Sheet;
import jxl.Workbook;

public class ExeclToMysql {

    public  void insterMysql() {
        //得到表格中所有的数据
        List<Taoyhq> listExcel=getAllByExcel("D:\\eclipseMars2Workspace\\webProject\\smilecloud\\smile-cloud-service\\smile-cloud-service-taoBLMeng\\src\\main\\resources\\2019-07-31.xls");
       System.out.println(listExcel.size());
        Statement stmt = null; 
        ConnectionUtil connectionUtil = new ConnectionUtil();
        Connection conn = ConnectionUtil.getConn();
        try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
    	String deleteSql="truncate taoyhq";  
    	try {
			boolean execute = stmt.execute(deleteSql);
			System.out.println("表数据清空"+execute );
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        for (Taoyhq t : listExcel) {
        	 //String sql="insert into student (a,b,c,d,e,f,g,h,ii,jj,k,l,n,m,o,p,q,r,s,t,u,v) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        	String sql="insert into taoyhq (a,b,c,d,e,f,g,h,i,j,k,l,n,m,o,p,q,r,s,t,u,v) values('"+t.getA()+"','"+t.getB()+"','"+t.getC()+"','"+t.getD()+"','"+t.getE()+"','"+t.getF()+"','"+t.getG()+"','"+t.getH()+"','"+t.getI()+"','"+t.getJ()+"','"+t.getK()+"','"+t.getL()+"','"+t.getN()+"','"+t.getM()+"','"+t.getO()+"','"+t.getP()+"','"+t.getQ()+"','"+t.getR()+"','"+t.getS()+"','"+t.getT()+"','"+t.getU()+"','"+t.getV()+"')";
        	 
             try {
     			stmt.addBatch(sql);
     		} catch (SQLException e) {
     			// TODO Auto-generated catch block
     			e.printStackTrace();
     		}
		}
        try {
			int[] executeBatch = stmt.executeBatch();
			System.out.println(executeBatch.toString()); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

}
    /**
     * 查询指定目录中电子表格中所有的数据
     * @param file 文件完整路径
     * @return
     */
    public static List<Taoyhq> getAllByExcel(String file){
        List<Taoyhq> list=new ArrayList<Taoyhq>();
        try {
            Workbook rwb=Workbook.getWorkbook(new File("D:\\eclipseMars2Workspace\\webProject\\smilecloud\\smile-cloud-service\\smile-cloud-service-taoBLMeng\\src\\main\\resources\\2019-07-31.xls"));
            Sheet rs=rwb.getSheet(0);//表
            int clos=rs.getColumns();//得到所有的列
            int rows=rs.getRows();//得到所有的行
            
            System.out.println("表的列数："+clos+" 表的行数:"+rows);
            for (int i = 1; i < rows; i++) {
                for (int j = 0; j < clos; j++) {
                    //第一个是列数，第二个是行数
                    String a=rs.getCell(j++, i).getContents();//默认最左边编号也算一列 所以这里得j++
                    String b=rs.getCell(j++, i).getContents();
                    String c=rs.getCell(j++, i).getContents();
                    String d=rs.getCell(j++, i).getContents();
                    String e=rs.getCell(j++, i).getContents();
                    String f=rs.getCell(j++, i).getContents();
                    String g=rs.getCell(j++, i).getContents();
                    String h=rs.getCell(j++, i).getContents();
                    String ii=rs.getCell(j++, i).getContents();
                    String jj=rs.getCell(j++, i).getContents();
                    String k=rs.getCell(j++, i).getContents();
                    String l=rs.getCell(j++, i).getContents();
                    String n=rs.getCell(j++, i).getContents();
                    String m=rs.getCell(j++, i).getContents();
                    String o=rs.getCell(j++, i).getContents();
                    String p=rs.getCell(j++, i).getContents();
                    String q=rs.getCell(j++, i).getContents();
                    String r=rs.getCell(j++, i).getContents();
                    String s=rs.getCell(j++, i).getContents();
                    String t=rs.getCell(j++, i).getContents();
                    String u=rs.getCell(j++, i).getContents();
                    String v=rs.getCell(j++, i).getContents();        
                    Taoyhq taoyhq = new Taoyhq(a,b,c,d,e,f,g,h,ii,jj,k,l,n,m,o,p,q,r,s,t,u,v);
                    System.out.println(taoyhq.toString());
                    list.add(taoyhq);
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        return list;
        
    }
    
}
