package com.zhongsuwuliu.zhongsulogistics.JavaBean;

import java.io.Serializable;

/**
 * Created by 刺雒 on 2016/11/23.
 */
public class GoodsTempBean implements Serializable{
    private String GoodsID;//货物编号
    private String ContainerID;//货箱编号
    private short Status;//状态

    public String getGoodsID() {
        return GoodsID;
    }

    public void setGoodsID(String goodsID) {
        GoodsID = goodsID;
    }

    public String getContainerID() {
        return ContainerID;
    }

    public void setContainerID(String containerID) {
        ContainerID = containerID;
    }

    public short getStatus() {
        return Status;
    }

    public void setStatus(short status) {
        Status = status;
    }
}
