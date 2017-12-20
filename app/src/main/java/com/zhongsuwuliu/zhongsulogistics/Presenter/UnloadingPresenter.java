package com.zhongsuwuliu.zhongsulogistics.Presenter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.zhongsuwuliu.zhongsulogistics.Activity.UnloadingActivity;
import com.zhongsuwuliu.zhongsulogistics.ActivityInterface.IUnloadingActivity;
import com.zhongsuwuliu.zhongsulogistics.Dialog.DetailDialog;
import com.zhongsuwuliu.zhongsulogistics.Helper.ObserverHelper;
import com.zhongsuwuliu.zhongsulogistics.Model.UnloadingModel;
import com.zhongsuwuliu.zhongsulogistics.ModelInterface.IUnloadingModel;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by 刺雒 on 2016/11/24.
 */
public class UnloadingPresenter implements Observer{
    private IUnloadingActivity mUnloadingActivity ;
    private IUnloadingModel mModel;
    private ArrayList<String> containerIDs = new ArrayList<String>();
    private String containerCode = null;
    private String containerID = null;
    public static int containerTotal = 0;
    private Context mContext;
    public UnloadingPresenter(UnloadingActivity unloadingActivity,Context context){
        this.mUnloadingActivity = unloadingActivity;
        this.mModel = new UnloadingModel();
        this.mContext = context;
    }

    /*获取扫码后返回的字符串*/
    public void getScanContainer(Intent data){
        Bundle bundle = data.getExtras();
        containerCode = bundle.getString("result");
        if(containerID != null) {
            containerIDs.add(containerCode);
            containerTotal++;
        }
    }

    /*完成分拣按钮点击事件*/
    public void setBtnCompeteClick(){
        if(containerIDs.size() != 0) {
            mModel.unloadingComplete(containerIDs);
            containerIDs.clear();
        }else {
            Toast.makeText(mContext,"请您开始扫描货箱",Toast.LENGTH_SHORT).show();
        }
    }

    /*获取货箱内详情按钮*/
    public void setBtnDetailsClick(){
        if(containerID != null) {
            mModel.containerDetails(containerID);
        }else {
            Toast.makeText(mContext,"请您扫描货箱",Toast.LENGTH_SHORT).show();
        }
    }


    /*更新数据*/
    @Override
    public void update(Observable o, Object response) {
        ObserverHelper helper = (ObserverHelper)response;
        switch (helper.sign){
            case "containerErrors":
                errorDialog(helper.errors);
                break;
            case "containerDetails":
                DetailDialog detailDialog = new DetailDialog(helper.containerDetails,mContext);
                break;
        }
    }

    /*设置对话框*/
    public void errorDialog(ArrayList<String> errors){
        StringBuilder errorBuilder = new StringBuilder();
        String errorString  = "错误箱子编号为:";
        for(int i = 0; i < errors.size(); i++){
            errorBuilder.append(errorString).append(errors.get(i)).append("\n");
        }
        errorBuilder.append("请进行核查");
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("设置标题").setMessage(errorBuilder).create().show();
    }
}
