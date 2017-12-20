package com.zhongsuwuliu.zhongsulogistics.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.xys.libzxing.zxing.activity.CaptureActivity;
import com.zhongsuwuliu.zhongsulogistics.ActivityInterface.IContainerManageActivity;
import com.zhongsuwuliu.zhongsulogistics.JavaBean.ContainerBean;
import com.zhongsuwuliu.zhongsulogistics.Presenter.ContainerManagePresenter;
import com.zhongsuwuliu.zhongsulogistics.R;

import java.util.ArrayList;

public class ContainerManageActivity extends AppCompatActivity implements IContainerManageActivity,View.OnClickListener{
    private TextView tvContainerID,tvBindingPosition;
    private EditText etKindsID;
    private Button btBinding,btUnbinding,btScanPos,btScanCon;
    private ContainerManagePresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container_manage);
        initView();
    }

    /*初始化控件*/
    private void initView(){
        tvContainerID = (TextView)findViewById(R.id.container_manage_id_text);
        tvBindingPosition = (TextView)findViewById(R.id.container_manage_pos_id_text);
        etKindsID = (EditText)findViewById(R.id.container_manage_kind_id_edit);
        btBinding = (Button)findViewById(R.id.container_manage_binding_button);
        btUnbinding = (Button)findViewById(R.id.container_manage_unbinding_button);
        btScanPos = (Button)findViewById(R.id.container_manage_scan_pos_button);
        btScanCon = (Button)findViewById(R.id.container_manage_scan_box_button);
        btBinding.setOnClickListener(this);
        btUnbinding.setOnClickListener(this);
        btScanPos.setOnClickListener(this);
        btScanCon.setOnClickListener(this);
        mPresenter = new ContainerManagePresenter(ContainerManageActivity.this,this);
    }

    /*点击事件*/
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //绑定按钮
            case R.id.container_manage_binding_button:
                mPresenter.setBtnBindingCon(etKindsID.getText().toString());
                break;
            //解绑按钮
            case R.id.container_manage_unbinding_button:
                mPresenter.setBtnUnbindingCon();
                break;
            //扫描周转箱
            case R.id.container_manage_scan_box_button:
                startActivityForResult(new Intent(this, CaptureActivity.class),0);
                break;
            //扫描仓位
            case R.id.container_manage_scan_pos_button:
                startActivityForResult(new Intent(this, CaptureActivity.class),1);
                break;
        }
    }

    /*返回结果*/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK){
            mPresenter.getResult(requestCode,data);
        }
    }

    /*设置货箱文本*/
    @Override
    public void setConIDText(String containerID) {
        tvContainerID.setText(containerID);
    }

    /*设置仓位文本*/
    @Override
    public void setPosIDText(String positionID) {
        tvBindingPosition.setText(positionID);
    }
}
