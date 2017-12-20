package com.zhongsuwuliu.zhongsulogistics.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.RequestQueue;
import com.yolanda.nohttp.rest.Response;
import com.zhongsuwuliu.zhongsulogistics.Helper.DataBaseHelper;
import com.zhongsuwuliu.zhongsulogistics.JavaBean.ContainerBean;
import com.zhongsuwuliu.zhongsulogistics.ModelInterface.IContainerManageModel;
import com.zhongsuwuliu.zhongsulogistics.Resquest.BaseRequestListener;
/**
 * Created by 刺雒 on 2016/11/26.
 */
public class ContainerManageModel implements IContainerManageModel{
    private DataBaseHelper mDbHelper;
    private Context mContext;
    private SQLiteDatabase mDbWriter,mDbReader;
    private int NOHTTP_WHAT = 0x02;
    private String url;
    private JSONObject jsonObject;
    private int status;
    private String msg;
    public ContainerManageModel(Context context){
        mDbHelper = new DataBaseHelper(context,"zswl.db3");
        mDbReader = mDbHelper.getReadableDatabase();
        mDbWriter = mDbHelper.getWritableDatabase();

    }
    @Override
    public void bindingCon(ContainerBean containerBean) {
        final Request<String> request = NoHttp.createStringRequest(url, RequestMethod.POST);
        request.add("", JSON.toJSONString(containerBean));
        RequestQueue requestQueue = NoHttp.newRequestQueue();
        requestQueue.add(NOHTTP_WHAT,request,new BaseRequestListener(){
            @Override
            public void onSucceed(int i, Response<String> response) {
                jsonObject = JSON.parseObject(response.get());
                status = jsonObject.getInteger("Status");
                msg = jsonObject.getString("Message");
                if(status == 0){
                    Log.e("containerManage",msg);
                }else {
                    Log.e("containerManage",jsonObject.getString("JsonData"));
                }
            }
        });
    }

    @Override
    public void unbindingCon(ContainerBean containerBean) {
        final Request<String> request = NoHttp.createStringRequest(url, RequestMethod.POST);
        request.add("", JSON.toJSONString(containerBean));
        RequestQueue requestQueue = NoHttp.newRequestQueue();
        requestQueue.add(NOHTTP_WHAT,request,new BaseRequestListener(){
            @Override
            public void onSucceed(int i, Response<String> response) {
                jsonObject = JSON.parseObject(response.get());
                status = jsonObject.getInteger("Status");
                msg = jsonObject.getString("Message");
                if(status == 0){
                    Log.e("containerManage",msg);
                }else {
                    Log.e("containerManage",jsonObject.getString("JsonData"));
                }
            }
        });
    }

    /*绑定周转箱添加到数据库中*/
    @Override
    public void insertContainer(ContainerBean containerBean) {
        Cursor cursor = mDbReader.rawQuery("select * from table Container", null);
        cursor.moveToFirst();
        while (cursor.moveToNext()) {
            if(cursor.getString(cursor.getColumnIndex("_id")).equals(containerBean.getContainerID())) {
                updateData(containerBean);
                return;
            }
        }
        insertData(containerBean);
    }


    /*更新周转箱表*/
    @Override
    public boolean updateContainer(ContainerBean containerBean) {
        Cursor cursor = mDbReader.rawQuery("select * from table Container", null);
        cursor.moveToFirst();
        while (cursor.moveToNext()) {
            if(cursor.getString(cursor.getColumnIndex("_id")).equals(containerBean.getContainerID())) {
               updateData(containerBean);
                return true;
            }
        }
        return false;
    }

    /*添加数据*/
    private void insertData(ContainerBean containerBean){
        ContentValues values = new ContentValues();
        values.put("_id", containerBean.getContainerID());
        values.put("KindID", containerBean.getKindID());
        values.put("WarehouseID", containerBean.getContainerID());
        mDbWriter.insert("Container", null, values);
    }

    /*更新数据*/
    private void updateData(ContainerBean containerBean) {
        ContentValues values = new ContentValues();
        values.put("KindID", containerBean.getKindID());
        values.put("WarehouseID", containerBean.getContainerID());
        mDbWriter.update("Container", values, "_id=?", new String[]{containerBean.getContainerID()});
    }
}
