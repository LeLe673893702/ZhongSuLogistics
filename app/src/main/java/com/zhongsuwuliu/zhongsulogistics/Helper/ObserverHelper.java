package com.zhongsuwuliu.zhongsulogistics.Helper;

import com.zhongsuwuliu.zhongsulogistics.JavaBean.ContainerDetailsBean;
import com.zhongsuwuliu.zhongsulogistics.JavaBean.PositionDetailsBean;

import java.util.ArrayList;

/**
 * Created by 刺雒 on 2016/11/25.
 */
public class ObserverHelper {
    //标志位
    public String sign;
    //货箱内货物详情
    public ArrayList<ContainerDetailsBean> containerDetails;
    //反馈错误箱子详情
    public ArrayList<String> errors;
    //仓位内货物详情
    public ArrayList<PositionDetailsBean> positionDetails;
}
