package com.zhongsuwuliu.zhongsulogistics.JavaBean;

import java.io.Serializable;

/**
 * Created by 刺雒 on 2016/11/23.
 * 仓库、库区依赖表
 */
public class WarehouseBean implements Serializable{
    private String WarehouseID;//仓库或库区ID
    private String AreaID;//区域ID
    private String WarehouseName;//仓库或库区名称
    private String WarehouseAddress;//仓库地址
    private String Descs;//仓库或库区描述
    private int Standard;//仓库规格
    private float ConstantCost;//费用
   private String MangerID;//负责人ID
    private int Status;//状态
   private String MerchantID;//商家ID
   private String ParentId;//父级ID

    public String getWarehouseID() {
        return WarehouseID;
    }

    public void setWarehouseID(String warehouseID) {
        WarehouseID = warehouseID;
    }

    public String getAreaID() {
        return AreaID;
    }

    public void setAreaID(String areaID) {
        AreaID = areaID;
    }

    public String getWarehouseName() {
        return WarehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        WarehouseName = warehouseName;
    }

    public String getWarehouseAddress() {
        return WarehouseAddress;
    }

    public void setWarehouseAddress(String warehouseAddress) {
        WarehouseAddress = warehouseAddress;
    }

    public String getDescs() {
        return Descs;
    }

    public void setDescs(String descs) {
        Descs = descs;
    }

    public int getStandard() {
        return Standard;
    }

    public void setStandard(int standard) {
        Standard = standard;
    }

    public float getConstantCost() {
        return ConstantCost;
    }

    public void setConstantCost(float constantCost) {
        ConstantCost = constantCost;
    }

    public String getMangerID() {
        return MangerID;
    }

    public void setMangerID(String mangerID) {
        MangerID = mangerID;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public String getMerchantID() {
        return MerchantID;
    }

    public void setMerchantID(String merchantID) {
        MerchantID = merchantID;
    }

    public String getParentId() {
        return ParentId;
    }

    public void setParentId(String parentId) {
        ParentId = parentId;
    }
}
