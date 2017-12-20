package com.zhongsuwuliu.zhongsulogistics.Model;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.OnResponseListener;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.RequestQueue;
import com.yolanda.nohttp.rest.Response;
import com.zhongsuwuliu.zhongsulogistics.Helper.ObserverHelper;
import com.zhongsuwuliu.zhongsulogistics.JavaBean.ContainerDetailsBean;
import com.zhongsuwuliu.zhongsulogistics.ModelInterface.IUnloadingModel;
import com.zhongsuwuliu.zhongsulogistics.Resquest.BaseRequestListener;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by 刺雒 on 2016/11/24.
 */
public class UnloadingModel extends Observable implements IUnloadingModel{
    private String url = "";
    private static final int NOHTTP_WHAT_TEST = 0x002;
    private  JSONObject jsonObject;
    private ObserverHelper mObserverHelper ;
    public UnloadingModel(){
        mObserverHelper = new ObserverHelper();
    }

    /*发送完成卸货请求*/
    @Override
    public void unloadingComplete(ArrayList<String> containerIDs) {
        Request<String> request = NoHttp.createStringRequest(url,RequestMethod.POST);
        String containerIdsJson = JSON.toJSONString(containerIDs);
        request.add("GoodTemps",containerIdsJson);
        RequestQueue requestQueue = NoHttp.newRequestQueue();
        requestQueue.add(NOHTTP_WHAT_TEST, request, new BaseRequestListener() {
            @Override
            public void onSucceed(int i, Response<String> response) {
                jsonObject = JSON.parseObject(response.get());
                int status = jsonObject.getInteger("Status");
                String msg = jsonObject.getString("Message");
                if(status == 0){
                    Log.e("error",msg);
                }else {
                    String data = jsonObject.getString("JsonData");
                    ArrayList<String> errorList =  (ArrayList<String>)(JSONArray.parseArray(data,String.class));
                    mObserverHelper.sign = "containerErrors";
                    mObserverHelper.errors =errorList;
                    setChanged();
                    notifyObservers(mObserverHelper);
                }
            }
        });
    }

    /*发送查看箱子详情*/
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
                    Log.e("error","error-----"+msg);
                }else {
                    String data = jsonObject.getString("jsonData");
                    ArrayList<ContainerDetailsBean> containerDetailsBeans =
                            (ArrayList<ContainerDetailsBean>) JSONArray.parseArray(data,ContainerDetailsBean.class);
                    mObserverHelper.sign = "containerDetails";
                    mObserverHelper.containerDetails = containerDetailsBeans;
                    setChanged();
                    notifyObservers(mObserverHelper);
                }
            }
        });
    }

}
