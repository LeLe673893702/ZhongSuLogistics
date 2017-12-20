package com.zhongsuwuliu.zhongsulogistics.JavaBean;

/**
 * Created by 刺雒 on 2016/11/26.
 */
public class PositionDetailsBean {
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

    public String getStandard() {
        return Standard;
    }

    public void setStandard(String standard) {
        Standard = standard;
    }

    public String getGoodsID() {
        return GoodsID;
    }

    public void setGoodsID(String goodsID) {
        GoodsID = goodsID;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public String getMangerID() {
        return MangerID;
    }

    public void setMangerID(String mangerID) {
        MangerID = mangerID;
    }

    public String getMangerName() {
        return MangerName;
    }

    public void setMangerName(String mangerName) {
        MangerName = mangerName;
    }

    private String Trademark;//品牌
    private String KindName;//种类名称
    private String Standard;//规格
    private String GoodsID;//货物ID
    private String Sex;//性别
    private String MangerID;//负责人ID
    private String MangerName;//负责人姓名
}
