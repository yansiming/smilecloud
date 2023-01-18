package com.smile.colud.service.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dtk.main.ApiClient;
import com.smile.colud.model.Commodity;
import com.smile.colud.model.Item;
import com.smile.colud.service.CommodityReadExeclService;
import com.smile.colud.util.TaokeApiUtil;
import jxl.Sheet;
import jxl.Workbook;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.DocFlavor;
import java.io.File;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

/**
 * @author yansm
 * @version 1.0
 * @date 06/21/2021 11:32
 */
@Service
public class CommodityReadExeclServiceImol implements CommodityReadExeclService {
    @Autowired
    private TaokeApiUtil taokeApiUtil;

    @Override
    public List<Item> commodityReadExecl(String execlName) {
        List<Item> items = getAllByExcel(execlName);
        return items;
    }

    @Override
    public String getBountyPriceStr(String price,String incomeRatio) {
        double commission = Double.parseDouble(price) * Double.parseDouble(incomeRatio) * 0.01;
        Double bountyPrice = 0.00;
        if(commission<=1){
            bountyPrice = commission*78/100;
        }else if(commission>1 && commission<=3){
            bountyPrice = commission*75/100;
        }else if(commission>3 && commission<=6){
            bountyPrice = commission*73/100;
        }else if(commission>6 && commission<=10){
            bountyPrice = commission*55/100;
        }else if(commission>10 && commission<=30){
            bountyPrice = commission*50/100;
        }else if(commission>30 && commission<=60){
            bountyPrice = commission*50/100;
        }else if(commission>60 && commission<=100){
            bountyPrice = commission*50/100;
        }else if(commission>100 && commission<=500){
            bountyPrice = commission*50/100;
        }
//               String bountyPriceStr =new DecimalFormat("0.00").format(bountyPrice);
        return String.valueOf(new BigDecimal(bountyPrice).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
    }

    /**
     * 读取execl文件内容
     * @param execlName 文件名
     * @return
     */
    public List<Item> getAllByExcel(String execlName){
        List<Commodity> list=new ArrayList<Commodity>();
        List<Item> newList = new ArrayList<>();
        try {
//            Workbook rwb=Workbook.getWorkbook(new File("F:\\taoCommodityExecl\\"+execlName));
            Workbook rwb=Workbook.getWorkbook(new File("C:\\Users\\Administrator\\Desktop\\wxgzh\\execl\\"+execlName));
            Sheet sheet=rwb.getSheet(0);//表
            int columnNums=sheet.getColumns();//得到所有的列
            int rowNums=sheet.getRows();//得到所有的行

            System.out.println("表的列数："+columnNums+" 表的行数:"+rowNums);
            for (int rowNum = 1; rowNum < rowNums; rowNum++) {
                for (int columnNum= 0; columnNum < columnNums; columnNum++) {
                    //第一个是列数，第二个是行数
                    Commodity commodity = new Commodity();
                    commodity.setCommodityId( sheet.getCell(columnNum++, rowNum).getContents());
                    commodity.setCommodityName( sheet.getCell(columnNum++, rowNum).getContents());
                    commodity.setCommodityMasterMap( sheet.getCell(columnNum++, rowNum).getContents());
                    commodity.setCommodityParticularsUrl( sheet.getCell(columnNum++, rowNum).getContents());
                    commodity.setStoreName( sheet.getCell(columnNum++, rowNum).getContents());
                    commodity.setCommodityPrice( sheet.getCell(columnNum++, rowNum).getContents());
                    commodity.setCommodityMonthlySales( sheet.getCell(columnNum++, rowNum).getContents());
                    commodity.setIncomeRatio( sheet.getCell(columnNum++, rowNum).getContents());
                    commodity.setCommission( sheet.getCell(columnNum++, rowNum).getContents());
                    commodity.setSellersWantWant( sheet.getCell(columnNum++, rowNum).getContents());
                    commodity.setTaoShortUrl( sheet.getCell(columnNum++, rowNum).getContents());
                    commodity.setTaoUrl( sheet.getCell(columnNum++, rowNum).getContents());
                    commodity.setTaoPassword( sheet.getCell(columnNum++, rowNum).getContents());
                    commodity.setCouponTotal( sheet.getCell(columnNum++, rowNum).getContents());
                    commodity.setCouponSurplus( sheet.getCell(columnNum++, rowNum).getContents());
                    commodity.setCouponValue( sheet.getCell(columnNum++, rowNum).getContents());
                    commodity.setCouponStartDate( sheet.getCell(columnNum++, rowNum).getContents());
                    commodity.setCouponEndDate(sheet.getCell(columnNum++, rowNum).getContents());
                    commodity.setCouponUrl(sheet.getCell(columnNum++, rowNum).getContents());
                    commodity.setCouponTaoUrl(sheet.getCell(columnNum++, rowNum).getContents());
                    commodity.setCouponShortUrl( sheet.getCell(columnNum++, rowNum).getContents());
                    commodity.setFalseTureMarketing( sheet.getCell(columnNum++, rowNum).getContents());
                    commodity.setClustersNumber( sheet.getCell(columnNum++, rowNum).getContents());
                    commodity.setCloudsPrice( sheet.getCell(columnNum++, rowNum).getContents());
                    commodity.setCloudsCommission( sheet.getCell(columnNum++, rowNum).getContents());
                    commodity.setCloudsStartDate( sheet.getCell(columnNum++, rowNum).getContents());
                    commodity.setCloudsEndDate( sheet.getCell(columnNum++, rowNum).getContents());
                    commodity.setInsertDate(new Date().toString());
                    list.add(commodity);

                }
            }
            System.out.println("-------execl数据: ");
            //转换淘口令
            for(Commodity commodity :list){
                System.out.println("commodity: "+commodity.toString());
                Item item = new Item();
                item.setCommodityName(commodity.getCommodityName());
                Double commodityPrice = Double.parseDouble(commodity.getCommodityPrice());
                Double couponValue = Double.parseDouble(StringUtils.isEmpty(commodity.getCouponValue())? "0" : commodity.getCouponValue());
                Double  priceDouble  = commodityPrice-couponValue;
                priceDouble = new BigDecimal(priceDouble).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
                String price  = String.valueOf(priceDouble);
                item.setPrice("\uD83D\uDE18付款价: "+price+"  ¥");
                //预计奖励金
                Double commission = Double.parseDouble(commodity.getCommission());
                Double bountyPrice = 0.00;
                if(commission<=1){
                    bountyPrice = commission*78/100;
                }else if(commission>1 && commission<=3){
                    bountyPrice = commission*75/100;
                }else if(commission>3 && commission<=6){
                    bountyPrice = commission*73/100;
                }else if(commission>6 && commission<=10){
                    bountyPrice = commission*55/100;
                }else if(commission>10 && commission<=30){
                    bountyPrice = commission*50/100;
                }else if(commission>30 && commission<=60){
                    bountyPrice = commission*50/100;
                }else if(commission>60 && commission<=100){
                    bountyPrice = commission*50/100;
                }else if(commission>100 && commission<=500){
                    bountyPrice = commission*50/100;
                }
//               String bountyPriceStr =new DecimalFormat("0.00").format(bountyPrice);
                String bountyPriceStr = String.valueOf(new BigDecimal(bountyPrice).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

                //预计奖励金
                item.setBountyPrice("✅预计奖励金: "+bountyPriceStr+" ¥");
                //预计最终价
                String finalPrice = String.valueOf(new BigDecimal(priceDouble - Double.parseDouble(bountyPriceStr)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                item.setFinalPrice("\uD83D\uDCA5预计到手价: "+finalPrice+" ¥");
                //原  价
                item.setCommodityPrice("\uD83E\uDD14原  价: "+commodity.getCommodityPrice()+" ¥");
                //券  额
                item.setCouponValue(StringUtils.isEmpty(commodity.getCouponValue()) ? "" : "\uD83D\uDC93券  额: "+commodity.getCouponValue()+" ¥");
                //月销量
                item.setCommodityMonthlySales("\uD83D\uDC51月销量: "+commodity.getCommodityMonthlySales());
                //主图url
                item.setCommodityMasterMap(commodity.getCommodityMasterMap());
                //剁手口令 8輹Zhi0$M3IKXSfluTb$://,打開🍑/
                String taoPassword = taokeApiUtil.getTaoPassword(commodity.getTaoPassword());
//                String taoPassword = commodity.getTaoPassword().split(" ")[0]+commodity.getTaoPassword().split(" ")[1]+commodity.getTaoPassword().split(" ")[2];
                item.setTaoPassword("8輹Zhi0"+taoPassword+",打開\uD83C\uDF51/");
                HashMap<String,String> dtitleAndCircleText = taokeApiUtil.getFriendsCircleText(commodity);
                if(dtitleAndCircleText!=null&&dtitleAndCircleText.size()>0 ){
                    //短标题
                    item.setDtitle(dtitleAndCircleText.get("dtitle"));
                    //朋友圈文案
                    item.setCircleText(dtitleAndCircleText.get("circleText"));
                }else{
                    item.setDtitle(commodity.getCommodityName());
                }
//                //短标题
//                item.setDtitle("PINKBEAR皮可熊唇釉~水润配方");
//                //朋友圈文案
//                item.setDesc("有首单礼金，送礼品！小红书、微博爆火！PINKBEAR皮可熊唇釉~水润配方，上唇成膜锁色，不粘腻，甜度满分！看着超诱人~有蔷薇红茶、爆浆草莓等多色号任选，速囤【全国包邮，赠运费险】");
                newList.add(item);
            }
            System.out.println("------------html对象newList: "+newList.toString());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return newList;

    }



}
