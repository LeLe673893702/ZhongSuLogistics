package com.zhongsuwuliu.zhongsulogistics.Model;

import java.util.ArrayList;

/**
 * Created by 刺雒 on 2016/11/29.
 */
public interface IOutStockModel {
    public void scanContainer(String goodsID);
    public void completeOutStock(ArrayList<String> goodsList,int positionType);

}
