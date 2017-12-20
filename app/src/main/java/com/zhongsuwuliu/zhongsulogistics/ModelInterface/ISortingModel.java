package com.zhongsuwuliu.zhongsulogistics.ModelInterface;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by 刺雒 on 2016/11/26.
 */
public interface ISortingModel {
    public void ScanContainer(String containerID);
    public void SortingComplete(HashMap<String,ArrayList<String>> completeGoodsIDMap);
}
