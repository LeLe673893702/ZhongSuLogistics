package com.zhongsuwuliu.zhongsulogistics.Dialog;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.zhongsuwuliu.zhongsulogistics.Adapter.ContainerDetailAdapter;
import com.zhongsuwuliu.zhongsulogistics.JavaBean.ContainerDetailsBean;
import com.zhongsuwuliu.zhongsulogistics.R;

import java.util.ArrayList;

/**
 * Created by 刺雒 on 2016/11/25.
 */
public class DetailDialog {
    private ListView lvDetails;
    private ContainerDetailAdapter mAdapter;
    private ArrayList<ContainerDetailsBean> mBeans;
    private Context mContext;
    public DetailDialog(ArrayList<ContainerDetailsBean> containerDetailsBeans,Context context){
        this.mBeans = containerDetailsBeans;
        this.mContext = context;
        createDialog();
    }

    private void createDialog(){
        View contentView = LayoutInflater.from(mContext).inflate(R.layout.dialog_container_details,null);
        PopupWindow popupWindow = new PopupWindow(contentView, LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(contentView);
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.activity_unloading,null);
        popupWindow.showAtLocation(rootView, Gravity.BOTTOM,0,0);
        lvDetails = (ListView)contentView.findViewById(R.id.container_details_list);
        mAdapter = new ContainerDetailAdapter(mBeans,mContext);
        lvDetails.setAdapter(mAdapter);
    }
}
