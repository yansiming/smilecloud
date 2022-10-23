package com.smile.colud.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dtk.main.ApiClient;
import com.smile.colud.model.Commodity;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.apache.commons.lang.StringUtils;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author yansm
 * @version 1.0
 * @date 06/24/2021 8:51
 */
@Component
public class TaokeApiUtil {

    @Value("${taokeApi.appKey}")
    private String appKey;
    @Value("${taokeApi.appSecret}")
    private String appSecret;
    /***
     * 淘口令转换淘口令
     * @param taoPassword 淘口令
     * @return 淘口令
     */
    public String getTaoPassword(String taoPassword){
//        String[] taoPasswords = taoPassword.split("\\s+");
        String url = "https://openapi.dataoke.com/api/tb-service/twd-to-twd";
        TreeMap<String, String> paraMap = new TreeMap<>();
        paraMap.put("version", "v1.0.0");
        paraMap.put("appKey", appKey);
        paraMap.put("content", taoPassword);
        String data = ApiClient.sendReq(url, appSecret, paraMap);
        System.out.println("调用淘口令转换淘口令接口: "+data);
        String  tpwd =null;
        if(StringUtils.isNotBlank(data)){
            JSONObject jsonObject = JSON.parseObject(data);
            JSONObject data2 = jsonObject.getJSONObject("data");
            if(data2!=null){
                tpwd = jsonObject.getJSONObject("data").get("tpwd").toString();
            }
        }
//        if(StringUtils.isEmpty(tpwd)){
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            tpwd = getTaoPassword(taoPassword);
//        }
        return tpwd;
    }

    /***
     * 根据标题获取推广文案
     * @param title
     * @return
     */
    public HashMap<String,String> getDtitleAndDesc(String title){
        HashMap<String, String> newHashMap = new HashMap<>();
        String url = "https://openapi.dataoke.com/api/goods/get-dtk-search-goods";
        TreeMap<String, String> paraMap = new TreeMap<>();
        paraMap.put("version", "v2.0.0");
        paraMap.put("appKey", appKey);
        paraMap.put("pageId", "1");
        paraMap.put("pageSize", "20");
        paraMap.put("keyWords", title);
        String data = ApiClient.sendReq(url, appSecret, paraMap);
        System.out.println(data);
        if(StringUtils.isNotBlank(data)){
            JSONObject jsonObject = JSON.parseObject(data);
            if("10006".equals(jsonObject.get("code").toString())){
               return  null;
            }
            JSONObject data2 = jsonObject.getJSONObject("data");
            if(data2!=null){
                Object o = jsonObject.getJSONObject("data").get("list");
                if(objjectToList(o).size()>0){
                    Map<String,String> strMap= objjectToList(o).get(0);
                    newHashMap.put("dtitle",strMap.get("dtitle"));
                    newHashMap.put("circleText",strMap.get("desc"));
                }
            }
        }
        if(StringUtils.isEmpty(newHashMap.get("dtitle"))&&StringUtils.isEmpty(newHashMap.get("circleText"))){
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            newHashMap = getDtitleAndDesc(title);
        }
        return newHashMap;
    }

    /***
     * 根据商品id获取朋友圈文案和短标题
     * @param commodity
     * @return
     */
    public HashMap<String,String> getFriendsCircleText(Commodity commodity){
        HashMap<String, String> newHashMap = new HashMap<>();
        String url = "https://openapi.dataoke.com/api/goods/friends-circle-list";
        TreeMap<String, String> paraMap = new TreeMap<>();
        paraMap.put("version", "v1.3.0");
        paraMap.put("appKey", appKey);
        paraMap.put("goodsId", commodity.getCommodityId());
        String data = ApiClient.sendReq(url, appSecret, paraMap);
        System.out.println("调用获取朋友圈文案和短标题:"+data);
        String  circleText =null;
        String  dtitle =null;
        if(StringUtils.isNotBlank(data)){
            JSONObject jsonObject = JSON.parseObject(data);
            Object o = jsonObject.get("code");
            if("10006".equals(jsonObject.get("code").toString())){
                newHashMap = getDtitleAndDesc(commodity.getCommodityName());
                if(newHashMap==null){
                    return null;
                }
            }
            JSONObject data2 = jsonObject.getJSONObject("data");

            if(data2!=null){
                Object jsonData = jsonObject.getJSONObject("data").get("list");
                if(objjectToList(jsonData).size()>0){
                    Map<String,String> strMap= objjectToList(jsonData).get(0);
                    circleText = strMap.get("circleText");
                    dtitle = strMap.get("dtitle");
                    newHashMap.put("dtitle",dtitle);
                    newHashMap.put("circleText",circleText);
                    if(StringUtils.isEmpty(circleText)){
                        circleText = strMap.get("desc");
                        newHashMap.put("circleText",circleText);
                    }
                }

            }
        }
        if(StringUtils.isEmpty(newHashMap.get("dtitle"))&&StringUtils.isEmpty(newHashMap.get("circleText"))){
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            newHashMap = getDtitleAndDesc(commodity.getCommodityId());
        }

        return newHashMap;
    }

    private List<Map<String,String>> objjectToList(Object obj){
        List<Map<String,String>> result = new ArrayList<>();
        if (obj instanceof List<?>) {
            for (Object o : (List<?>) obj) {
                result.add(Map.class.cast(o));
            }
        }
        return result;
    }

    /***
     * 每日爆品推荐
     * @param pageId 页数
     * @param pageSize 分页条数
     * @param priceCid 价格区间，1表示10~20元区；2表示20~40元区；3表示40元以上区；默认为1
     * @param cids 大淘客的一级分类id，如果需要传多个，以英文逗号相隔，如：”1,2,3”。1 -女装，2 -母婴，3 -美妆，4 -居家日用，5 -鞋品，6 -美食，7 -文娱车品，8 -数码家电，9 -男装，10 -内衣，11 -箱包，12 -配饰，13 -户外运动，14 -家装家纺。不填默认全部
     * @return
     */
    public String getExplosiveGoodsList(String pageId,String pageSize,String priceCid,String cids){
        String url = "https://openapi.dataoke.com/api/goods/explosive-goods-list";
        TreeMap<String, String> paraMap = new TreeMap<>();
        paraMap.put("version", "v1.0.0");
        paraMap.put("appKey", appKey);
        paraMap.put("pageId", pageId);
        paraMap.put("pageSize", pageSize);
        paraMap.put("PriceCid", priceCid);
        paraMap.put("cids", cids);
        String data = ApiClient.sendReq(url, appSecret, paraMap);
        System.out.println("调用获取朋友圈文案和短标题:"+data);
        return data;
    }



}
