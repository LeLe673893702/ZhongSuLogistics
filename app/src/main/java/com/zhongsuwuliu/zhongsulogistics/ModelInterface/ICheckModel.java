package com.zhongsuwuliu.zhongsulogistics.ModelInterface;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by 刺雒 on 2016/11/25.
 */
public interface ICheckModel {
    public void scanContainer(String containerID);
    public void scanPosition(String positionID);
    public void conComplete(HashMap<String,ArrayList<String>> damageConGoodsMap);
    public void posComplete(HashMap<String,ArrayList<String>> damagePosGoodsMap);
}
