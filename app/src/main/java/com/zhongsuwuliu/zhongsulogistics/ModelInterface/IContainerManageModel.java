package com.zhongsuwuliu.zhongsulogistics.ModelInterface;

import com.zhongsuwuliu.zhongsulogistics.JavaBean.ContainerBean;

/**
 * Created by 刺雒 on 2016/11/26.
 */
public interface IContainerManageModel {
    public void bindingCon(ContainerBean containerBean);
    public void unbindingCon(ContainerBean containerBean);
    public void insertContainer(ContainerBean containerBean);
    public boolean updateContainer(ContainerBean containerBean);
}
