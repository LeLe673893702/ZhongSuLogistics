package com.zhongsuwuliu.zhongsulogistics.Presenter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.zhongsuwuliu.zhongsulogistics.Activity.ContainerManageActivity;
import com.zhongsuwuliu.zhongsulogistics.ActivityInterface.IContainerManageActivity;
import com.zhongsuwuliu.zhongsulogistics.JavaBean.ContainerBean;
import com.zhongsuwuliu.zhongsulogistics.Model.ContainerManageModel;
import com.zhongsuwuliu.zhongsulogistics.ModelInterface.IContainerManageModel;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 刺雒 on 2016/11/26.
 */
public class ContainerManagePresenter {
    private SharedPreferences.Editor mEditor;
    private Context mContext;
    private IContainerManageActivity mActivity;
    private IContainerManageModel mModel;
    private String warehouseID;
    private String containerID;
    private ContainerBean mContainerBean = new ContainerBean();

    /*构造函数*/
    public ContainerManagePresenter(Context context,ContainerManageActivity containerManageActivity){
        this.mContext = context;
        this.mActivity = containerManageActivity;
        mModel = new ContainerManageModel(mContext);
    }

    /*返回扫码后的结果*/
    public void getResult(int requestCode,Intent data){
        Bundle bundle = data.getExtras();
        switch (requestCode){
            case 0:
                containerID = bundle.getString("result");
                mActivity.setConIDText(containerID);
                break;
            case 1:
                warehouseID = bundle.getString("result");
                mActivity.setPosIDText(warehouseID);
                break;
            default:
                break;
        }
    }

    /*设置绑定周转箱按钮*/
    public void setBtnBindingCon(String kindID){
        if(isNumber(kindID)) {
            if (containerID != null && warehouseID != null) {
                mContainerBean.setContainerID(containerID);
                mContainerBean.setWarehouseID(warehouseID);
                mContainerBean.setKindID(kindID);
                mModel.insertContainer(mContainerBean);
            }else {
                Toast.makeText(mContext,"库区条码或仓位条码不能为空",Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(mContext,"请输入正确的13位种类ID",Toast.LENGTH_SHORT).show();
        }
    }

    /*设置解绑周转箱按钮*/
    public void setBtnUnbindingCon(){
        if(containerID != null){
            mContainerBean.setContainerID(containerID);
            mContainerBean.setKindID(null);
            mContainerBean.setWarehouseID(null);
            mModel.updateContainer(mContainerBean);
        }else {
            Toast.makeText(mContext,"请扫描货箱条码",Toast.LENGTH_SHORT).show();
        }
    }

    /*判断是否输入的是13位数字ID*/
    private boolean isNumber(String string){
        Pattern pattern = Pattern.compile("[0-9]{13}");
        Matcher isNum = pattern.matcher(string);
        if(!!isNum.matches()){
            return false;
        }
        return true;
    }
}
