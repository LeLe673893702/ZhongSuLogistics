package com.zhongsuwuliu.zhongsulogistics.Presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.zhongsuwuliu.zhongsulogistics.Activity.InStockActivity;
import com.zhongsuwuliu.zhongsulogistics.ActivityInterface.IInStockActivity;
import com.zhongsuwuliu.zhongsulogistics.Helper.ObserverHelper;
import com.zhongsuwuliu.zhongsulogistics.JavaBean.ContainerDetailsBean;
import com.zhongsuwuliu.zhongsulogistics.Model.InStockModel;
import com.zhongsuwuliu.zhongsulogistics.ModelInterface.IInStockModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by 刺雒 on 2016/11/28.
 */
public class InStockPresenter implements Observer{
    private Context mContext;
    private IInStockActivity mActivity;
    private IInStockModel mModel;
    private String containerID = null;
    private String positionID = null;
    private String goodsID = null;
    private String lastPosID = null;
    private ArrayList<ContainerDetailsBean> mContainerDetailsBeans;
    private HashMap<String,ArrayList<String>> inStockMap = new HashMap<>();
    private ArrayList<String> goodsList = new ArrayList<>();
    private int total = 0;
    private int  num = 0;
    private String lastContainerID;

    /*构造函数*/
    public InStockPresenter(Context context,InStockActivity inStockActivity){
        this.mContext = context;
        this.mActivity = inStockActivity;
        this.mModel = new InStockModel();
    }

    /*返回结果*/
    public void getResult(int requestCode,Intent data){
        Bundle bundle = data.getExtras();
        switch (requestCode){
            case 0:
                lastContainerID = containerID;
                containerID = bundle.getString("result");
                setScanContainer();
                break;
            case 1:
                lastPosID = positionID;
                positionID = bundle.getString("result");
                setScanPosition();;
                break;
            case 2:
                goodsID = bundle.getString("result");
                setScanGoods();
                break;
            default:
                break;
        }
    }

    /*获取到周转箱ID发送*/
    private void setScanContainer(){
        if(containerID != null){
            mModel.containerDetails(containerID);
        }else {
            Toast.makeText(mContext,"请先扫描货箱条码",Toast.LENGTH_SHORT).show();
        }
    }

    /*获取到仓位ID后操作*/
    private void setScanPosition(){
        if(containerID !=null && positionID != null) {
            inStockMap.put(positionID, goodsList);
            //如果两次扫描的仓位码不一样就清空了
            if(!lastPosID.equals(positionID) && lastPosID != null){
                goodsList.clear();
            }
        }
    }

    /*设置完成*/
    public void setBtnCompleteClick(){
        if(inStockMap.size() != 0){
            mModel.completeInStock(inStockMap);
            goodsList.clear();
            inStockMap.clear();
        }
    }

    /*获取到goodsID*/
    private void setScanGoods(){
        if(containerID != null && positionID != null && goodsID != null) {
            goodsList.add(goodsID);
            num ++;
            mActivity.setNumText(String.valueOf(num));
        }
    }

    /*设置总数量*/
    private void setTotalText(){
        if(containerID != null && containerID != lastContainerID){
            total += mContainerDetailsBeans.size();
            mActivity.setTotalText(String.valueOf(total));
        }
    }

    /*更新数据*/
    @Override
    public void update(Observable o, Object arg) {
        ObserverHelper helper = (ObserverHelper)arg;
        mContainerDetailsBeans = helper.containerDetails;
        setTotalText();
    }
}
