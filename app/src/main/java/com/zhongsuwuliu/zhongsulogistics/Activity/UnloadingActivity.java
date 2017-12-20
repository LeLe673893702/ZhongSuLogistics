package com.zhongsuwuliu.zhongsulogistics.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xys.libzxing.zxing.activity.CaptureActivity;
import com.zhongsuwuliu.zhongsulogistics.ActivityInterface.IUnloadingActivity;
import com.zhongsuwuliu.zhongsulogistics.Model.UnloadingModel;
import com.zhongsuwuliu.zhongsulogistics.Presenter.UnloadingPresenter;
import com.zhongsuwuliu.zhongsulogistics.R;

public class UnloadingActivity extends AppCompatActivity implements View.OnClickListener,IUnloadingActivity {
    private TextView tvContainerID,tvContainerTotal;
    private Button btContainerDetails,btScanContainer,btComplete;
    private UnloadingPresenter mPresenter;
    private UnloadingModel mModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unloading);
        initView();
    }

    //初始化控件
    private void initView(){
        tvContainerID = (TextView)findViewById(R.id.unloading_container_id);
        tvContainerTotal = (TextView)findViewById(R.id.unloading_container_total_text);
        btComplete = (Button)findViewById(R.id.unloading_complete_button);
        btScanContainer = (Button)findViewById(R.id.unloading_scan_container_button);
        btContainerDetails = (Button)findViewById(R.id.unloading_container_details_button);
        this.mPresenter = new UnloadingPresenter(this,UnloadingActivity.this);
        this.mModel = new UnloadingModel();
        btContainerDetails.setOnClickListener(this);
        btComplete.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.unloading_scan_container_button:
                startActivityForResult(new Intent(UnloadingActivity.this, CaptureActivity.class),0);
                break;

            case R.id.unloading_complete_button:
                mPresenter.setBtnCompeteClick();
                break;

            case R.id.unloading_container_details_button:
                mPresenter.setBtnDetailsClick();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            mPresenter.getScanContainer(data);
        }

    }

    /*设置文本信息*/
    @Override
    public void setText(String containerID) {
        tvContainerTotal.setText(UnloadingPresenter.containerTotal);
        tvContainerID.setText(containerID);
    }
}
