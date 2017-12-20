package com.zhongsuwuliu.zhongsulogistics.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xys.libzxing.zxing.activity.CaptureActivity;
import com.zhongsuwuliu.zhongsulogistics.ActivityInterface.IInStockActivity;
import com.zhongsuwuliu.zhongsulogistics.Presenter.InStockPresenter;
import com.zhongsuwuliu.zhongsulogistics.R;

public class InStockActivity extends AppCompatActivity implements View.OnClickListener,IInStockActivity{
    private TextView tvTotal,tvNum;
    private Button btScanCon,btScanPos,btScanGoods,btComplete;
    private InStockPresenter mInStockPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_stock);
        initView();
    }

    /*初始化控件*/
    private void initView(){
        tvTotal = (TextView)findViewById(R.id.in_stock_goods_total_text);
        tvNum = (TextView)findViewById(R.id.in_stock_compete_num_text);
        btScanCon = (Button)findViewById(R.id.in_stock_scan_container_button);
        btScanPos = (Button)findViewById(R.id.in_stock_scan_position_button);
        btScanGoods = (Button)findViewById(R.id.in_stock_scan_goods_button);
        btComplete = (Button)findViewById(R.id.in_stock_compete_button);
        btScanPos.setOnClickListener(this);
        btScanGoods.setOnClickListener(this);
        btComplete.setOnClickListener(this);
        mInStockPresenter = new InStockPresenter(InStockActivity.this,this);
    }

    /*点击事件*/
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            /*扫描周转箱条码*/
            case R.id.in_stock_scan_container_button:
                startActivityForResult(new Intent(this, CaptureActivity.class),0);
                break;
            /*扫描仓位条码*/
            case R.id.in_stock_scan_position_button:
                startActivityForResult(new Intent(this, CaptureActivity.class),1);
                break;
            /*扫描货物条码*/
            case R.id.in_stock_scan_goods_button:
                startActivityForResult(new Intent(this, CaptureActivity.class),1);
                break;
            /*完成上架*/
            case R.id.in_stock_compete_button:
                mInStockPresenter.setBtnCompleteClick();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK){
            mInStockPresenter.getResult(requestCode,data);
        }
    }

    /*设置总数文本*/
    @Override
    public void setTotalText(String totalText) {
        tvTotal.setText(totalText);
    }

    /*设置数量文本*/
    @Override
    public void setNumText(String numText) {
        tvNum.setText(numText);
    }
}
