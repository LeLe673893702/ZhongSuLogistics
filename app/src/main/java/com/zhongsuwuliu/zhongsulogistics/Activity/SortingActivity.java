package com.zhongsuwuliu.zhongsulogistics.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zhongsuwuliu.zhongsulogistics.ActivityInterface.ISortingActivity;
import com.zhongsuwuliu.zhongsulogistics.Application;
import com.zhongsuwuliu.zhongsulogistics.JavaBean.ContainerBean;
import com.zhongsuwuliu.zhongsulogistics.ModelInterface.ISortingModel;
import com.zhongsuwuliu.zhongsulogistics.Presenter.SortingPresenter;
import com.zhongsuwuliu.zhongsulogistics.R;

import java.util.ArrayList;

public class SortingActivity extends AppCompatActivity implements View.OnClickListener,ISortingActivity{
    private Button btSetCode,btScanGoods,btScanContainer,btComplete;
    private TextView tvBinningID,tvTotal,tvSortingNum;
    private SortingPresenter mSortingPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sorting);
        initView();
    }

    /*初始化控件*/
    private void initView(){
        btSetCode = (Button)findViewById(R.id.sorting_set_code_button);
        btScanGoods = (Button)findViewById(R.id.sorting_scan_goods_button);
        btScanContainer = (Button)findViewById(R.id.sorting_scan_container_button);
        btComplete = (Button)findViewById(R.id.sorting_compete_button);
        tvBinningID = (TextView)findViewById(R.id.sorting_binning_id_text);
        tvTotal = (TextView)findViewById(R.id.sorting_goods_total_text);
        tvSortingNum = (TextView)findViewById(R.id.sorting_goods_num_text);
        btSetCode.setOnClickListener(this);
        btScanGoods.setOnClickListener(this);
        btScanContainer.setOnClickListener(this);
        btComplete.setOnClickListener(this);
        this.mSortingPresenter = new SortingPresenter(SortingActivity.this,this);
    }

    /*设置点击事件*/
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            /*设置编号*/
            case R.id.sorting_set_code_button:
                startActivityForResult(new Intent(this,SetNumActivity.class),0);
                break;
            /*扫描货物*/
            case R.id.check_scan_goods_button:
                startActivityForResult(new Intent(this,SetNumActivity.class),1);
                break;
            /*扫描箱子*/
            case R.id.sorting_scan_container_button:
                startActivityForResult(new Intent(this,SetNumActivity.class),2);
                break;
            /*分拣完成*/
            case R.id.sorting_compete_button:
                mSortingPresenter.setBtnCompleteClick();
                break;
        }
    }



    /*返回数据*/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode){
            case RESULT_OK:
                mSortingPresenter.getResultOk(requestCode,data);
                break;
            case RESULT_CANCELED:
                mSortingPresenter.getResultCancel();
                break;
            default:
                break;
        }
    }

    /*箱子的临时ID*/
    @Override
    public void setIDText(String binningID) {
        tvBinningID.setText(binningID);
    }

    /*货物的总数量*/
    @Override
    public void setTotalText(String total) {
        tvTotal.setText(total);
    }

    /*货物已经分拣的数量*/
    @Override
    public void setNumText(String goodsNum) {
        tvSortingNum.setText(goodsNum);
    }
}
