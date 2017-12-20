package com.zhongsuwuliu.zhongsulogistics.JavaBean;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by 刺雒 on 2016/11/23.
 */
public class GoodsKindsBean implements Serializable{
    private String KindsID;//货物种类ID
    private String Product;//供货商
    private String MerchantID;//商家ID
    private String Trademark;//品牌
    private String KindName;//种类名称
    private String Sex;//性别
    private String Standard;//规格
    private String ProducingArea;//产地
    private String Weight;//重量
    private short ColorID;//颜色ID
    private short UnitID;//单位
    private double Price;//价格
    private double DiscountPrice;//颜折价
    private double ReturnPrice;//退货价
    private long AmountUp;//数量上限
    private long AmountDown;//数量下限
    private String Shelflife;//保质期
    private long Integral;//单件积分
    private int Status;//状态
    private int Count;//当前数量

    public String getKindsID() {
        return KindsID;
    }

    public void setKindsID(String kindsID) {
        KindsID = kindsID;
    }

    public String getProduct() {
        return Product;
    }

    public void setProduct(String product) {
        Product = product;
    }

    public String getMerchantID() {
        return MerchantID;
    }

    public void setMerchantID(String merchantID) {
        MerchantID = merchantID;
    }

    public String getTrademark() {
        return Trademark;
    }

    public void setTrademark(String trademark) {
        Trademark = trademark;
    }

    public String getKindName() {
        return KindName;
    }

    public void setKindName(String kindName) {
        KindName = kindName;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public String getStandard() {
        return Standard;
    }

    public void setStandard(String standard) {
        Standard = standard;
    }

    public String getProducingArea() {
        return ProducingArea;
    }

    public void setProducingArea(String producingArea) {
        ProducingArea = producingArea;
    }

    public String getWeight() {
        return Weight;
    }

    public void setWeight(String weight) {
        Weight = weight;
    }

    public short getColorID() {
        return ColorID;
    }

    public void setColorID(short colorID) {
        ColorID = colorID;
    }

    public short getUnitID() {
        return UnitID;
    }

    public void setUnitID(short unitID) {
        UnitID = unitID;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public double getDiscountPrice() {
        return DiscountPrice;
    }

    public void setDiscountPrice(double discountPrice) {
        DiscountPrice = discountPrice;
    }

    public double getReturnPrice() {
        return ReturnPrice;
    }

    public void setReturnPrice(double returnPrice) {
        ReturnPrice = returnPrice;
    }

    public long getAmountUp() {
        return AmountUp;
    }

    public void setAmountUp(long amountUp) {
        AmountUp = amountUp;
    }

    public long getAmountDown() {
        return AmountDown;
    }

    public void setAmountDown(long amountDown) {
        AmountDown = amountDown;
    }

    public String getShelflife() {
        return Shelflife;
    }

    public void setShelflife(String shelflife) {
        Shelflife = shelflife;
    }

    public long getIntegral() {
        return Integral;
    }

    public void setIntegral(long integral) {
        Integral = integral;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public int getCount() {
        return Count;
    }

    public void setCount(int count) {
        Count = count;
    }
}
