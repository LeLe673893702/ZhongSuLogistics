package com.zhongsuwuliu.zhongsulogistics.JavaBean;

import java.io.Serializable;

/**
 * Created by 刺雒 on 2016/11/23.
 */
public class ContainerBean implements Serializable{
    private String ContainerID;//货箱ID
    private String KindID;//种类ID
    private String WarehouseID;//绑定库区ID
    private String temporaryID;//临时编号

    public String getTemporaryID() {
        return temporaryID;
    }

    public void setTemporaryID(String temporaryID) {
        this.temporaryID = temporaryID;
    }
    
    public String getContainerID() {
        return ContainerID;
    }

    public void setContainerID(String containerID) {
        ContainerID = containerID;
    }

    public String getKindID() {
        return KindID;
    }

    public void setKindID(String kindID) {
        KindID = kindID;
    }

    public String getWarehouseID() {
        return WarehouseID;
    }

    public void setWarehouseID(String warehouseID) {
        WarehouseID = warehouseID;
    }
}
