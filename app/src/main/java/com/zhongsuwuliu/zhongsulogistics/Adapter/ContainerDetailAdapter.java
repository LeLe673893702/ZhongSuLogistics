package com.zhongsuwuliu.zhongsulogistics.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhongsuwuliu.zhongsulogistics.JavaBean.ContainerDetailsBean;
import com.zhongsuwuliu.zhongsulogistics.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by 刺雒 on 2016/11/25.
 */
public class ContainerDetailAdapter extends BaseAdapter{
    private ViewHolder mHolder;
    private LayoutInflater mInflater;
    private View v;
    private ArrayList<ContainerDetailsBean> mBeans;
    private Context mContext = null;
    public ContainerDetailAdapter(ArrayList<ContainerDetailsBean> containerDetailsBeans,Context context){
        this.mContext = context;
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mBeans = containerDetailsBeans;

    }
    @Override
    public int getCount() {
        return mBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HashMap<Integer,View> viewMap = new HashMap<Integer,View>();
        mHolder = null;

        if(viewMap.get(position) == null){
            v = mInflater.inflate(R.layout.list_container_details,null);
            mHolder = new ViewHolder();
            initView();
            viewMap.put(position,v);
            v.setTag(mHolder);
        }else {
            v = viewMap.get(position);
            mHolder = (ViewHolder)v.getTag();
        }
        return null;
    }

    /*初始化控件*/
    public void initView(){
        mHolder.tvID = (TextView)v.findViewById(R.id.container_details_id);
        mHolder.tvKind = (TextView)v.findViewById(R.id.container_details_kind);
        mHolder.tvSex = (TextView)v.findViewById(R.id.container_details_sex);
        mHolder.tvStandard = (TextView)v.findViewById(R.id.container_details_standard);
        mHolder.tvTrademark = (TextView)v.findViewById(R.id.container_details_trademark);
    }

    /*设置文字*/
    public void setText(int position){
        mHolder.tvID.setText(mBeans.get(position).getGoodsID());
        mHolder.tvKind.setText(mBeans.get(position).getKindName());
        mHolder.tvStandard.setText(mBeans.get(position).getStandard());
        mHolder.tvTrademark.setText(mBeans.get(position).getStandard());
        /*设置性别*/
        if(mBeans.get(position).getSex().equals("1")){
            mHolder.tvSex.setText("男性");
        }else if(mBeans.get(position).equals("0")){
            mHolder.tvSex.setText("女性");
        }else if(mBeans.get(position).equals("2")){
            mHolder.tvSex.setText("男女皆可");
        }
    }

    /*holder类*/
    public class ViewHolder{
        public TextView tvID,tvTrademark,tvSex,tvKind,tvStandard;
    }
}
