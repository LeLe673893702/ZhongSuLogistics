package com.zhongsuwuliu.zhongsulogistics.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xys.libzxing.zxing.activity.CaptureActivity;
import com.zhongsuwuliu.zhongsulogistics.ActivityInterface.IOutStockActivity;
import com.zhongsuwuliu.zhongsulogistics.Presenter.OutStockPresenter;
import com.zhongsuwuliu.zhongsulogistics.R;

public class OutStackActivity extends AppCompatActivity implements View.OnClickListener,IOutStockActivity{
    private TextView tvCompleteNum,tvGoodsID,tvOutStockTime;
    private Button btScanGoods,btComplete;
    private OutStockPresenter mOutStockPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_out_stack);
        initView();
    }

    //初始化控件
    private void initView(){
        tvCompleteNum = (TextView)findViewById(R.id.out_stock_compete_num_text);
        tvOutStockTime = (TextView)findViewById(R.id.out_stock_compete_time_text);
        tvGoodsID = (TextView)findViewById(R.id.out_stock_goods_id_text);
        btScanGoods = (Button)findViewById(R.id.out_stock_scan_goods_button);
        btComplete = (Button)findViewById(R.id.out_stock_compete_button);
        btScanGoods.setOnClickListener(this);
        btComplete.setOnClickListener(this);
        mOutStockPresenter = new OutStockPresenter(OutStackActivity.this,this);
    }

    /*设置点击事件*/
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //完成按钮
            case R.id.out_stock_compete_button:
                mOutStockPresenter.setBtnComplete();
                break;
            //扫描货物按钮
            case R.id.out_stock_scan_goods_button:
                startActivityForResult(new Intent(this, CaptureActivity.class),0);
                break;
        }
    }

    /*返回结果*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK){
            mOutStockPresenter.getResult(data);
        }
    }

    @Override
    public void setGoodsText(String id, String time) {
        tvGoodsID.setText(id);
        tvOutStockTime.setText(time);
    }

    @Override
    public void setNumText(String numText) {
        tvCompleteNum.setText(numText);
    }
}
