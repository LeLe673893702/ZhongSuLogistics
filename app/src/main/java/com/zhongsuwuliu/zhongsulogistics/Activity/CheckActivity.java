package com.zhongsuwuliu.zhongsulogistics.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.xys.libzxing.zxing.activity.CaptureActivity;
import com.zhongsuwuliu.zhongsulogistics.ActivityInterface.ICheckActivity;
import com.zhongsuwuliu.zhongsulogistics.Presenter.CheckPresenter;
import com.zhongsuwuliu.zhongsulogistics.R;

public class CheckActivity extends AppCompatActivity implements View.OnClickListener,ICheckActivity{
    private TextView tvHeadID,tvHeadName,tvTrademark,tvStandard,tvSex,tvGoodsNum,tvGoodsTotal,tvKindName;
    private Button btScanContainer,btScanGoods,btScanPosition,btGoodsDamage,btPositionComplete,btContainerComplete;
    private CheckPresenter mCheckPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);
        initView();
    }

    /*初始化控件*/
    private void initView(){
        tvHeadID = (TextView)findViewById(R.id.check_head_id_text);
        tvHeadName = (TextView)findViewById(R.id.check_head_name_text);
        tvTrademark = (TextView)findViewById(R.id.check_trademark_text);
        tvStandard = (TextView)findViewById(R.id.check_standard_text);
        tvSex = (TextView)findViewById(R.id.check_goods_sex_text);
        tvGoodsNum = (TextView)findViewById(R.id.check_goods_num_text);
        tvGoodsTotal = (TextView)findViewById(R.id.check_goods_total_text);
        tvKindName = (TextView)findViewById(R.id.check_kinds_name_text);
        btScanContainer = (Button)findViewById(R.id.check_scan_container_button);
        btScanGoods = (Button)findViewById(R.id.check_scan_goods_button);
        btScanPosition = (Button)findViewById(R.id.check_scan_position_button);
        btPositionComplete = (Button)findViewById(R.id.check_position_complete_button);
        btContainerComplete = (Button)findViewById(R.id.check_container_complete_button);
        btGoodsDamage = (Button)findViewById(R.id.check_damage_button);
        btScanContainer.setOnClickListener(this);
        btScanGoods.setOnClickListener(this);
        btScanPosition.setOnClickListener(this);
        btPositionComplete.setOnClickListener(this);
        btContainerComplete.setOnClickListener(this);
        btGoodsDamage.setOnClickListener(this);
        btContainerComplete.setOnClickListener(this);
        mCheckPresenter = new CheckPresenter(this,CheckActivity.this);
    }

    /*点击事件*/
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //扫描货箱
            case R.id.check_scan_container_button:
                startActivityForResult(new Intent(CheckActivity.this, CaptureActivity.class),0);
                break;
            //扫描货物
            case R.id.check_scan_goods_button:
                if(mCheckPresenter.setBtnScanGoodsClick() != null) {
                    startActivityForResult(mCheckPresenter.setBtnScanGoodsClick(), 1);
                }else {
                    Toast.makeText(CheckActivity.this,"请先扫描货箱条码或者仓位条码",Toast.LENGTH_SHORT).show();
                }
                break;
            //扫描仓位
            case R.id.check_scan_position_button:
                startActivityForResult(new Intent(CheckActivity.this, CaptureActivity.class),2);
                break;
            //损坏
            case R.id.check_damage_button:
                mCheckPresenter.setBtnDamageClick();
                break;
            //完成仓位盘点
            case R.id.check_position_complete_button:
                mCheckPresenter.setBtnPosCompleteClick();
                break;
            //完成货箱盘点
            case R.id.check_container_complete_button:
                mCheckPresenter.setBtnConCompleteClick();
                break;
        }
    }

    /*返回结果*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            mCheckPresenter.getResult(requestCode,data);
        }
    }

    /*设置商品的基本信息*/
    @Override
    public void setGoodsText(String trademark, String kindName, String standard,String total) {
        tvTrademark.setText(trademark);
        tvKindName.setText(kindName);
        tvStandard.setText(standard);
        tvGoodsTotal.setText(CheckPresenter.goodsNum);
    }

    /*设置负责人的信息*/
    @Override
    public void setHeadText(String headID, String headName) {
        tvHeadID.setText(headID);
        tvHeadName.setText(headName);
    }
}
