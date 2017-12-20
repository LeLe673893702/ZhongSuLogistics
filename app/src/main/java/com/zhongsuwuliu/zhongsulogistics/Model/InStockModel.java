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
import com.zhongsuwuliu.zhongsulogistics.ModelInterface.IInStockModel;
import com.zhongsuwuliu.zhongsulogistics.Resquest.BaseRequestListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

/**
 * Created by 刺雒 on 2016/11/28.
 */
public class InStockModel extends Observable implements IInStockModel{
    private ObserverHelper mObserverHelper ;
    private int NOHTTP_WHAT_TEST = 0x02;
    private String url = "";
    private JSONObject jsonObject;
    public InStockModel(){
        mObserverHelper = new ObserverHelper();
    }

    @Override
    public void containerDetails(String containerID) {
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
                    Log.e("error", "error-----" + msg);
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

    @Override
    public void completeInStock(HashMap<String, ArrayList<String>> inStockMap) {
        Request<String> request = NoHttp.createStringRequest(url,RequestMethod.POST);
        String s = JSON.toJSONString(inStockMap);
        request.setConnectTimeout(3000);
        request.setReadTimeout(3000);
        RequestQueue requestQueue = NoHttp.newRequestQueue();
        requestQueue.add(NOHTTP_WHAT_TEST,request,new BaseRequestListener(){
            @Override
            public void onSucceed(int i, Response<String> response) {
                jsonObject = JSON.parseObject(response.get());
                int status = jsonObject.getInteger("Status");
                String msg = jsonObject.getString("Message");
                if(status == 0){
                    Log.e("error","error----" + msg);
                }else {

                }
            }
        });
    }

}
