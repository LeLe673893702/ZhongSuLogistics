package com.zhongsuwuliu.zhongsulogistics.Presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.xys.libzxing.zxing.activity.CaptureActivity;
import com.zhongsuwuliu.zhongsulogistics.Activity.CheckActivity;
import com.zhongsuwuliu.zhongsulogistics.ActivityInterface.ICheckActivity;
import com.zhongsuwuliu.zhongsulogistics.Helper.ObserverHelper;
import com.zhongsuwuliu.zhongsulogistics.JavaBean.ContainerDetailsBean;
import com.zhongsuwuliu.zhongsulogistics.JavaBean.PositionDetailsBean;
import com.zhongsuwuliu.zhongsulogistics.Model.CheckModel;
import com.zhongsuwuliu.zhongsulogistics.ModelInterface.ICheckModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by 刺雒 on 2016/11/25.
 */
public class CheckPresenter implements Observer{
    private Context mContext;
    private ICheckActivity mActivity;
    private ICheckModel mModel;
    private String containerID = null;
    private String goodsID = null;
    private String positionID = null;
    public static int goodsNum = 0;
    private ObserverHelper mHelper;
    private int scanSign = 2;//判断扫的是哪一位
    private ArrayList<ContainerDetailsBean> mContainerDetailsBeans;
    private ArrayList<PositionDetailsBean> mPositionDetailsBeans ;
    //货箱损坏货物List
    private ArrayList<String> damageContainerGoodsList = new ArrayList<>();
    //仓位损坏货物List
    private ArrayList<String> damagePositionGoodsList = new ArrayList<>();
    //货箱损坏货物Map
    private HashMap<String,ArrayList<String>> damageContainerGoodsMap = new HashMap<>();
    //仓位损坏货物Map
    private HashMap<String,ArrayList<String>> damagePositionGoodsMap = new HashMap<>();
    //记录上次扫过的货箱ID
    private String lastContainerID= null;
    //记录上次扫过的仓位ID
    private String lastPositionID = null;

    /*构造函数*/
    public CheckPresenter(CheckActivity checkActivity,Context context){
        this.mActivity = checkActivity;
        this.mModel = new CheckModel();
        this.mContext  = context;
    }

    /*返回Activity的结果*/
    public void getResult(int request,Intent data){
        Bundle bundle = data.getExtras();
        switch (request){
            /*扫描货箱返回的结果*/
            case 0:
                lastContainerID = containerID;
                containerID = bundle.getString("result");
                setScanContainer();
                addDamageGoodsList();
                break;
            /*扫描货物返回的结果*/
            case 1:
                goodsID = bundle.getString("result");
                setScanGoods();
                break;
            /*扫描仓位返回的结果*/
            case 2:
                positionID = bundle.getString("result");
                setScanPosition();
                addDamageGoodsList();
                break;
        }
    }

    /*设置扫描货物按钮*/
    public Intent setBtnScanGoodsClick(){
        if(containerID != null || positionID != null){
            return new Intent(mContext, CaptureActivity.class);
        }
        return null;
    }

    /*设置扫货箱玩货箱后的操作*/
    private void setScanContainer(){
        if(containerID != null){
            scanSign = 0;
            mModel.scanContainer(containerID);
            goodsNum = 0;
        }else {
            Toast.makeText(mContext,"请您重新扫描货箱条码",Toast.LENGTH_SHORT).show();
        }
    }

    /*设置扫货物完后的操作*/
    private void setScanGoods(){
        if (goodsID != null) {
            switch (scanSign) {
                //检查箱子内的物品是否存在
                case 0:
                    if (mContainerDetailsBeans != null && mContainerDetailsBeans.size() != 0) {
                        if (!findContainerGoodsID()) {
                            Toast.makeText(mContext, "货箱内不存在该物品", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(mContext, "请先扫描货箱", Toast.LENGTH_SHORT).show();
                    }

                    break;

                //检查仓库内的物品是否存在
                case 1:
                    if(mPositionDetailsBeans != null && mPositionDetailsBeans.size() != 0){
                        if(!findPositionGoodsID()){
                            Toast.makeText(mContext,"仓位不存在该物品",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(mContext,"请先扫描仓位",Toast.LENGTH_SHORT).show();
                        }
                    }
                break;

                //都没扫描
                case 2:
                    Toast.makeText(mContext,"请先扫描货箱或者仓位",Toast.LENGTH_SHORT).show();
                    break;
            }
            goodsNum++;
        }else {
            Toast.makeText(mContext,"请重新扫描货物",Toast.LENGTH_SHORT).show();
        }
    }

    /*设置扫仓位之后的操作*/
    private void setScanPosition(){
        if(positionID != null){
            scanSign = 1;
            mModel.scanPosition(positionID);
            goodsNum = 0;
        }else {
            Toast.makeText(mContext,"请重新扫描仓位条码",Toast.LENGTH_SHORT).show();
        }
    }

    /*寻找扫描货箱的东西*/
    private boolean findContainerGoodsID(){
        for(int i = 0; i < mContainerDetailsBeans.size(); i++){
            if(mContainerDetailsBeans.get(i).getGoodsID().equals(goodsID)){
                mActivity.setGoodsText(mContainerDetailsBeans.get(i).getTrademark(), mContainerDetailsBeans.get(i).getKindName(),
                        mContainerDetailsBeans.get(i).getStandard(), mContainerDetailsBeans.size() + "");
                return true;
            }
        }
        return false;
    }

    /*寻找扫描仓位的东西*/
    private boolean findPositionGoodsID(){
        for (int i = 0; i < mPositionDetailsBeans.size(); i++){
            if(mPositionDetailsBeans.get(i).getGoodsID().equals(goodsID)){
                mActivity.setGoodsText(mPositionDetailsBeans.get(i).getTrademark(),mPositionDetailsBeans.get(i).getKindName(),
                        mPositionDetailsBeans.get(i).getStandard(),mPositionDetailsBeans.size()+"");
                mActivity.setHeadText(mPositionDetailsBeans.get(i).getMangerID(), mPositionDetailsBeans.get(i).getMangerName());
                return true;
            }
        }
        return false;
    }

    /*设置损坏按钮操作*/
    public void setBtnDamageClick(){
        if(goodsID != null){
            switch (scanSign){
                case 0:
                    if(containerID != null){
                       damageContainerGoodsList.add(goodsID);
                    }
                    break;
                case 1:
                    if(positionID != null){
                        damagePositionGoodsList.add(goodsID);
                    }
                    break;
                case 2:
                    Toast.makeText(mContext,"请先扫描货箱或者仓位",Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

    /*设置完成货箱盘点*/
    public void setBtnConCompleteClick(){
        if(containerID != null){
            mModel.conComplete(damageContainerGoodsMap);
            damageContainerGoodsList.clear();
            damageContainerGoodsList.clear();
        }else {
            Toast.makeText(mContext,"请先扫描货箱",Toast.LENGTH_SHORT).show();
        }
    }

    /*设置完成仓位盘点*/
    public void setBtnPosCompleteClick(){
        if(positionID != null){
            mModel.posComplete(damagePositionGoodsMap);
            damagePositionGoodsList.clear();//清空处理
            damagePositionGoodsMap.clear();//清空处理
        }else {
            Toast.makeText(mContext,"请先扫描仓位",Toast.LENGTH_SHORT).show();
        }
    }
    /*收到通知后更新*/
    @Override
    public void update(Observable o, Object arg) {
        mHelper = (ObserverHelper)arg;
        switch (mHelper.sign){
            case "containerDetails":
                mContainerDetailsBeans = mHelper.containerDetails;
                break;
            case "positionDetails":
                mPositionDetailsBeans = mHelper.positionDetails;
                break;
        }
    }

    /*添加损坏的货物List*/
    public void addDamageGoodsList(){
        switch (scanSign){
            /*如果是货箱*/
            case 0:
                if(containerID != null && goodsID != null){
                    damageContainerGoodsMap.put(containerID,damageContainerGoodsList);
                    if(!lastContainerID.equals(containerID)){
                        damageContainerGoodsList.clear();
                    }
                }
                break;
            /*如果是仓位*/
            case 1:
                if(positionID != null & goodsID != null){
                    damagePositionGoodsMap.put(positionID,damagePositionGoodsList);
                    if(!lastPositionID.equals(positionID)){
                        damagePositionGoodsMap.clear();
                    }
                }
                break;
        }
    }
}
