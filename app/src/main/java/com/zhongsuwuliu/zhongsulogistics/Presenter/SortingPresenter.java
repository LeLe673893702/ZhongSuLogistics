package com.zhongsuwuliu.zhongsulogistics.Presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.zhongsuwuliu.zhongsulogistics.Activity.SortingActivity;
import com.zhongsuwuliu.zhongsulogistics.ActivityInterface.ISortingActivity;
import com.zhongsuwuliu.zhongsulogistics.Application;
import com.zhongsuwuliu.zhongsulogistics.Helper.ObserverHelper;
import com.zhongsuwuliu.zhongsulogistics.JavaBean.ContainerBean;
import com.zhongsuwuliu.zhongsulogistics.JavaBean.ContainerDetailsBean;
import com.zhongsuwuliu.zhongsulogistics.Model.SortingModel;
import com.zhongsuwuliu.zhongsulogistics.ModelInterface.ISortingModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by 刺雒 on 2016/11/26.
 */
public class SortingPresenter implements Observer{
    private Context mContext;
    private ISortingActivity mActivity;
    private ArrayList<ContainerBean> mContainerBeans = null;
    private ISortingModel mModel;
    private String containerID;
    private String lastGoodsID = null;
    private String goodsID;
    private ArrayList<ContainerDetailsBean> mContainerDetailsBeans;
    private ObserverHelper mHelper;
    private  int sortingNum;
    private int total;

    private HashMap<String,ArrayList<String>> sortingMap = new HashMap<>();
    /*构造函数*/
    public SortingPresenter(Context context,SortingActivity sortingActivity){
        this.mContext = context;
        this.mActivity = sortingActivity;
        mModel = new SortingModel();
        mHelper = new ObserverHelper();
        setSortingMap();
    }

    /*获取成功结果信息*/
    public void getResultOk(int request,Intent data){
        Bundle bundle = data.getExtras();
        switch (request){
            /*设置临时编号*/
            case 0:
                Application.sContainerBeans = (ArrayList<ContainerBean>)bundle.getSerializable("containerBeans");
                mContainerBeans = Application.sContainerBeans;
                break;
            //扫描周转箱条码
            case 1:
                lastGoodsID = containerID;
                containerID = bundle.getString("result");
                setScanContainer();
                sortingNum++;
                mActivity.setNumText(String.valueOf(sortingNum));
                setTotal();
                break;
            //扫描货物条码
            case 2:
                if(containerID != null) {
                    goodsID = bundle.getString("result");
                    showNumber();
                }else {
                    Toast.makeText(mContext,"请先扫描货箱的条码",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    /*设置总数*/
    private void setTotal(){
        if(lastGoodsID != containerID && lastGoodsID != null){
            total += mContainerBeans.size();
            mActivity.setTotalText(String.valueOf(total));
        }
    }

    /*创建分拣结果Map*/
    private void setSortingMap(){
        if(mContainerBeans != null) {
            for (int i = 0; i < mContainerBeans.size(); i++) {
                ArrayList<String> list = new ArrayList<>();
                sortingMap.put(mContainerBeans.get(i).getContainerID(),list);
            }
        }else {
            Log.e("sorting","mContainerBeans为空");
        }
    }

    /*获取取消结果信息*/
    public void getResultCancel( ){
        mContainerBeans = Application.sContainerBeans;
    }

    /*编号做判断*/
    private void showNumber(){
        if(goodsID != null){
            setTempNumber();
        }else {
            Toast.makeText(mContext,"请先扫描货物的条码",Toast.LENGTH_SHORT).show();
        }
    }

    /*寻找KindID进行匹配*/
    private String  matchKindID(){
        if(mContainerDetailsBeans != null && mContainerDetailsBeans.size() != 0){
            for(int i = 0; i < mContainerDetailsBeans.size(); i++){
                ContainerDetailsBean c = mContainerDetailsBeans.get(i);
                if(c.getGoodsID().equals(goodsID)){
                    return c.getKindID();
                }
            }
        }else {
            Toast.makeText(mContext,"货箱为空",Toast.LENGTH_SHORT).show();
            return null;
        }
        return null;
    }

    /*根据kindID设置编号*/
    private void setTempNumber(){
        if(matchKindID() != null){
            for(int i = 0; i < mContainerBeans.size(); i++){
                ContainerBean c = mContainerBeans.get(i);
                if(c.getKindID().equals(matchKindID())){
                    sortingMap.get(c.getTemporaryID()).add(goodsID);
                    mActivity.setIDText(containerID);
                    return;
                }
            }
        }else {
            Log.e("sorting","匹配的KindID为空");
        }
    }

    /*扫描货箱后的操作*/
    private void setScanContainer(){
        if(containerID != null) {
            mModel.ScanContainer(containerID);
        }else {
            Toast.makeText(mContext,"请先扫描货箱的条码",Toast.LENGTH_SHORT).show();
        }
    }

    /*设置完成分拣按钮事件*/
    public void setBtnCompleteClick(){
        if(containerID != null && goodsID != null){
            mModel.SortingComplete(sortingMap);
            sortingMap.clear();
        }else {
            Toast.makeText(mContext,"请先扫描货物或者货箱的条码",Toast.LENGTH_SHORT).show();
        }
    }

    /*更新数据*/
    @Override
    public void update(Observable o, Object arg) {
        mHelper = (ObserverHelper)arg;
        mContainerDetailsBeans = mHelper.containerDetails;
    }
}
