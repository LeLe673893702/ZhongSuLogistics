package com.zhongsuwuliu.zhongsulogistics.Model;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.RequestQueue;
import com.yolanda.nohttp.rest.Response;
import com.zhongsuwuliu.zhongsulogistics.Helper.ObserverHelper;
import com.zhongsuwuliu.zhongsulogistics.JavaBean.ContainerDetailsBean;
import com.zhongsuwuliu.zhongsulogistics.JavaBean.PositionDetailsBean;
import com.zhongsuwuliu.zhongsulogistics.ModelInterface.ICheckModel;
import com.zhongsuwuliu.zhongsulogistics.Resquest.BaseRequestListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

/**
 * Created by 刺雒 on 2016/11/25.
 */
public class CheckModel extends Observable implements ICheckModel{
    private String url = "";
    private int NOHTTP_WHAT_TEST = 0x02;
    private JSONObject jsonObject;
    private ObserverHelper mObserverHelper;
    public CheckModel(){
        mObserverHelper = new ObserverHelper();
    }

    /*扫货箱*/
    @Override
    public void scanContainer(String containerID) {
        Request<String> request = NoHttp.createStringRequest(url, RequestMethod.POST);
        request.add("ContainerID", containerID);
        request.setConnectTimeout(3000);
        request.setReadTimeout(3000);
        RequestQueue requestQueue = NoHttp.newRequestQueue();
        requestQueue.add(NOHTTP_WHAT_TEST, request, new BaseRequestListener() {
            @Override
            public void onSucceed(int i, Response<String> response) {
                jsonObject = JSON.parseObject(response.get());
                int status = jsonObject.getInteger("Status");
                String msg = jsonObject.getString("Message");
                if(status == 0){
                    Log.e("error","error-----"+msg);
                }else {
                    String data = jsonObject.getString("jsonData");
                    ArrayList<ContainerDetailsBean> containerDetailsBeans =
                            (ArrayList<ContainerDetailsBean>) JSONArray.parseArray(data, ContainerDetailsBean.class);
                    mObserverHelper.sign = "containerDetails";
                    mObserverHelper.containerDetails = containerDetailsBeans;
                    setChanged();
                    notifyObservers(mObserverHelper);
                }
            }
        });

}

    /*扫仓位*/
    @Override
    public void scanPosition(String positionID) {
        Request<String> request = NoHttp.createStringRequest(url,RequestMethod.POST);
        request.add("PositionID", positionID);
        RequestQueue requestQueue =  NoHttp.newRequestQueue();
        requestQueue.add(NOHTTP_WHAT_TEST, request, new BaseRequestListener() {
            @Override
            public void onSucceed(int i, Response<String> response) {
                jsonObject = JSON.parseObject(response.get());
                int status = jsonObject.getInteger("Status");
                String msg = jsonObject.getString("Message");
                String positionDetails = jsonObject.getString("JsonData");
                ArrayList<PositionDetailsBean> positionDetailsBeans =
                        (ArrayList<PositionDetailsBean>)JSONArray.parseArray(positionDetails,PositionDetailsBean.class);
                mObserverHelper.sign = "positionDetails";
                mObserverHelper.positionDetails = positionDetailsBeans;
                setChanged();
                notifyObservers(mObserverHelper);
            }
        });
    }

    @Override
    public void conComplete(HashMap<String, ArrayList<String>> damageConGoodsMap) {
        Request<String> request = NoHttp.createStringRequest(url, RequestMethod.POST);
        request.add("",JSON.toJSONString(damageConGoodsMap));
        RequestQueue requestQueue = NoHttp.newRequestQueue();
        requestQueue.add(NOHTTP_WHAT_TEST,request,new BaseRequestListener(){
            @Override
            public void onSucceed(int i, Response<String> response) {
            }
        });
    }

    @Override
    public void posComplete(HashMap<String, ArrayList<String>> damagePosGoodsMap) {
        Request<String> request = NoHttp.createStringRequest(url,RequestMethod.POST);
        request.add("",JSON.toJSONString(damagePosGoodsMap));
        RequestQueue requestQueue = NoHttp.newRequestQueue();
        requestQueue.add(NOHTTP_WHAT_TEST,request,new BaseRequestListener(){
            @Override
            public void onSucceed(int i, Response<String> response) {
            }
        });
    }

}
