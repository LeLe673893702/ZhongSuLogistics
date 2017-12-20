package com.zhongsuwuliu.zhongsulogistics.JavaBean;

import java.io.Serializable;

/**
 * Created by 刺雒 on 2016/11/23.
 * 地区表
 */
public class AreaBean implements Serializable{

    private String AreaName;//地区名称
    private long ParentID;//父级地区编号
    private long AreaID;//地区ID
    private String Descs;//区域地址描述
    private int Status;//区域状态
    private int MangerID;//负责人ID

    public String getAreaName() {
        return AreaName;
    }

    public void setAreaName(String areaName) {
        AreaName = areaName;
    }

    public long getParentID() {
        return ParentID;
    }

    public void setParentID(long parentID) {
        ParentID = parentID;
    }

    public long getAreaID() {
        return AreaID;
    }

    public void setAreaID(long areaID) {
        AreaID = areaID;
    }

    public String getDescs() {
        return Descs;
    }

    public void setDescs(String descs) {
        Descs = descs;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public int getMangerID() {
        return MangerID;
    }

    public void setMangerID(int mangerID) {
        MangerID = mangerID;
    }
}
