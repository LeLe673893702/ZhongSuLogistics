package com.zhongsuwuliu.zhongsulogistics.JavaBean;

import java.io.Serializable;

/**
 * Created by 刺雒 on 2016/11/23.
 */
public class GoodsBean implements Serializable{
    private String GoodsID;//货物编号
    private boolean IsInferiorQuality;//是否次品
    private boolean IsReturnGoods;//是否退货
    private String PositionType;//当前位置类型
    private String PaneID;//存储仓位ID
    private String OrderID;//订单编号iID
    private int FlowId;//货物流程
    private double ProcessCost;//加工费用
    private String FromWarehouseId;//发货目的地
    private String ToWarehouseId;//出库目的地
    private int Status;//货物状态
    private String InTime;//进库时间
    private String OutTime;//出库时间
    private String KindsID;//货物种类ID

    public String getGoodsID() {
        return GoodsID;
    }

    public void setGoodsID(String goodsID) {
        GoodsID = goodsID;
    }

    public boolean isInferiorQuality() {
        return IsInferiorQuality;
    }

    public void setIsInferiorQuality(boolean isInferiorQuality) {
        IsInferiorQuality = isInferiorQuality;
    }

    public boolean isReturnGoods() {
        return IsReturnGoods;
    }

    public void setIsReturnGoods(boolean isReturnGoods) {
        IsReturnGoods = isReturnGoods;
    }

    public String getPositionType() {
        return PositionType;
    }

    public void setPositionType(String positionType) {
        PositionType = positionType;
    }

    public String getPaneID() {
        return PaneID;
    }

    public void setPaneID(String paneID) {
        PaneID = paneID;
    }

    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String orderID) {
        OrderID = orderID;
    }

    public int getFlowId() {
        return FlowId;
    }

    public void setFlowId(int flowId) {
        FlowId = flowId;
    }

    public double getProcessCost() {
        return ProcessCost;
    }

    public void setProcessCost(double processCost) {
        ProcessCost = processCost;
    }

    public String getFromWarehouseId() {
        return FromWarehouseId;
    }

    public void setFromWarehouseId(String fromWarehouseId) {
        FromWarehouseId = fromWarehouseId;
    }

    public String getToWarehouseId() {
        return ToWarehouseId;
    }

    public void setToWarehouseId(String toWarehouseId) {
        ToWarehouseId = toWarehouseId;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public String getInTime() {
        return InTime;
    }

    public void setInTime(String inTime) {
        InTime = inTime;
    }

    public String getOutTime() {
        return OutTime;
    }

    public void setOutTime(String outTime) {
        OutTime = outTime;
    }

    public String getKindsID() {
        return KindsID;
    }

    public void setKindsID(String kindsID) {
        KindsID = kindsID;
    }
}
