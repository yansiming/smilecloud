package com.smile.colud.model;

/**
 * @author yansm
 * @version 1.0
 * @date 06/22/2021 10:08
 */
public class Item {
    //标题
    private String commodityName;
    //到手价
    private String price;
    //预计最终价
    private String finalPrice;
    //原  价
    private String commodityPrice;
    //券  额
    private String couponValue;
    //预计奖励金
    private String bountyPrice;
    //月销量
    private String commodityMonthlySales;
    //主图
    private String commodityMasterMap;
    //剁手口令
    private String taoPassword;
    //短标题
    private String dtitle;
    //推广文案
    private String desc;
    //朋友圈文案
    private String circleText;

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(String finalPrice) {
        this.finalPrice = finalPrice;
    }

    public String getCommodityPrice() {
        return commodityPrice;
    }

    public void setCommodityPrice(String commodityPrice) {
        this.commodityPrice = commodityPrice;
    }

    public String getCouponValue() {
        return couponValue;
    }

    public void setCouponValue(String couponValue) {
        this.couponValue = couponValue;
    }

    public String getBountyPrice() {
        return bountyPrice;
    }

    public void setBountyPrice(String bountyPrice) {
        this.bountyPrice = bountyPrice;
    }

    public String getCommodityMonthlySales() {
        return commodityMonthlySales;
    }

    public void setCommodityMonthlySales(String commodityMonthlySales) {
        this.commodityMonthlySales = commodityMonthlySales;
    }

    public String getCommodityMasterMap() {
        return commodityMasterMap;
    }

    public void setCommodityMasterMap(String commodityMasterMap) {
        this.commodityMasterMap = commodityMasterMap;
    }

    public String getTaoPassword() {
        return taoPassword;
    }

    public void setTaoPassword(String taoPassword) {
        this.taoPassword = taoPassword;
    }

    public String getDtitle() {
        return dtitle;
    }

    public void setDtitle(String dtitle) {
        this.dtitle = dtitle;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCircleText() {
        return circleText;
    }

    public void setCircleText(String circleText) {
        this.circleText = circleText;
    }

    @Override
    public String toString() {
        return "Item{" +
                "commodityName='" + commodityName + '\'' +
                ", price='" + price + '\'' +
                ", finalPrice='" + finalPrice + '\'' +
                ", commodityPrice='" + commodityPrice + '\'' +
                ", couponValue='" + couponValue + '\'' +
                ", bountyPrice='" + bountyPrice + '\'' +
                ", commodityMonthlySales='" + commodityMonthlySales + '\'' +
                ", commodityMasterMap='" + commodityMasterMap + '\'' +
                ", taoPassword='" + taoPassword + '\'' +
                ", dtitle='" + dtitle + '\'' +
                ", desc='" + desc + '\'' +
                ", circleText='" + circleText + '\'' +
                '}';
    }
}
