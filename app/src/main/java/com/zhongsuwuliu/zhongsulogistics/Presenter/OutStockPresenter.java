package com.zhongsuwuliu.zhongsulogistics.Presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.zhongsuwuliu.zhongsulogistics.ActivityInterface.IOutStockActivity;
import com.zhongsuwuliu.zhongsulogistics.JavaBean.GoodsBean;
import com.zhongsuwuliu.zhongsulogistics.Model.IOutStockModel;
import com.zhongsuwuliu.zhongsulogistics.Model.OutStockModel;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by 刺雒 on 2016/11/29.
 */
public class OutStockPresenter implements Observer{
    private Context mContext;
    private IOutStockModel mModel;
    private IOutStockActivity mActivity;
    private String goodsID = null;
    private int num = 0 ;
    private GoodsBean mGoodsBean;
    private ArrayList<String> goodsList = new ArrayList<>();
    /*构造函数*/
    public OutStockPresenter(Context context,IOutStockActivity outStockActivity){
        this.mContext = context;
        this.mActivity = outStockActivity;
        this.mModel = new OutStockModel();
    }

    /*返回结果*/
    public void getResult(Intent data){
        Bundle bundle = data.getExtras();
        goodsID = bundle.getString("result");
        setScanGoods();
    }

    /*扫描商品条码后的操作*/
    private void setScanGoods(){
        if(goodsID != null){
            mModel.scanContainer(goodsID);
            num++;
            mActivity.setNumText(String.valueOf(num));
            goodsList.add(goodsID);
        }else {
            Toast.makeText(mContext,"请先扫描货物条码",Toast.LENGTH_SHORT).show();
        }
    }

    /*完成*/
    public void setBtnComplete(){
        mModel.completeOutStock(goodsList,7);
        goodsList.clear();
    }

    /*更新数据*/
    @Override
    public void update(Observable o, Object arg) {
        mGoodsBean = (GoodsBean)arg;
        mActivity.setGoodsText(mGoodsBean.getGoodsID(),mGoodsBean.getOutTime());
    }
}
