package com.zhongsuwuliu.zhongsulogistics.Model;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.RequestQueue;
import com.yolanda.nohttp.rest.Response;
import com.zhongsuwuliu.zhongsulogistics.JavaBean.GoodsBean;
import com.zhongsuwuliu.zhongsulogistics.Resquest.BaseRequestListener;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by 刺雒 on 2016/11/29.
 */
public class OutStockModel extends Observable implements IOutStockModel{
    private String url = "";
    private int NOHTTP_WHAT_TEST = 0x02;
    private JSONObject jsonObject;
    private GoodsBean mGoodsBean;
    @Override
    public void scanContainer(String goodsID) {
        final Request<String> request = NoHttp.createStringRequest(url, RequestMethod.POST);
        request.add("goodsID",goodsID);
        RequestQueue requestQueue = NoHttp.newRequestQueue();
        requestQueue.add(NOHTTP_WHAT_TEST,request,new BaseRequestListener(){
            @Override
            public void onSucceed(int i, Response<String> response) {
                jsonObject = JSON.parseObject(response.get());
                int status = jsonObject.getInteger("Status");
                String msg = jsonObject.getString("Message");
                if(status == 0){
                    Log.e("outStock",msg);
                }else {
                    mGoodsBean = jsonObject.getObject("jsonData",GoodsBean.class);
                    setChanged();
                    notifyObservers(mGoodsBean);
                }
            }
        });

    }

    @Override
    public void completeOutStock(ArrayList<String> goodsList,int positionType) {
        String goodsString = JSON.toJSONString(goodsList);
        Request<String> request = NoHttp.createStringRequest(url, RequestMethod.POST);
        request.add("PositionType",positionType);
        request.add("",goodsString);
        RequestQueue requestQueue = NoHttp.newRequestQueue();
        requestQueue.add(NOHTTP_WHAT_TEST,request,new BaseRequestListener(){

        });
    }
}
