package com.zhongsuwuliu.zhongsulogistics.JavaBean;

import java.io.Serializable;

/**
 * Created by 刺雒 on 2016/11/23.
 * 仓位Bean
 */
public class PaneBean implements Serializable{
    private String PaneID;//仓位ID
    private String WarehouseID;//库区ID
    private int Standard;//仓位规格
    private String MerchantID;//商家ID
    private String KindsID;//货物种类

    public String getPaneID() {
        return PaneID;
    }

    public void setPaneID(String paneID) {
        PaneID = paneID;
    }

    public String getWarehouseID() {
        return WarehouseID;
    }

    public void setWarehouseID(String warehouseID) {
        WarehouseID = warehouseID;
    }

    public int getStandard() {
        return Standard;
    }

    public void setStandard(int standard) {
        Standard = standard;
    }

    public String getMerchantID() {
        return MerchantID;
    }

    public void setMerchantID(String merchantID) {
        MerchantID = merchantID;
    }

    public String getKindsID() {
        return KindsID;
    }

    public void setKindsID(String kindsID) {
        KindsID = kindsID;
    }
}
