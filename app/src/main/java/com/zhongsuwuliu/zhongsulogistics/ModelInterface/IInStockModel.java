package com.zhongsuwuliu.zhongsulogistics.ModelInterface;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by 刺雒 on 2016/11/28.
 */
public interface IInStockModel {
    public void containerDetails(String containerID);
    public void completeInStock(HashMap<String,ArrayList<String>> inStockMap);
}
