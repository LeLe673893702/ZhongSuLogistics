package com.zhongsuwuliu.zhongsulogistics.ModelInterface;

import java.util.ArrayList;

/**
 * Created by 刺雒 on 2016/11/24.
 */
public interface IUnloadingModel {
    public void unloadingComplete(ArrayList<String> containerIDs);
    public void containerDetails(String containerID);
}
