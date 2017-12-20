package com.zhongsuwuliu.zhongsulogistics.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.xys.libzxing.zxing.activity.CaptureActivity;
import com.zhongsuwuliu.zhongsulogistics.Helper.DataBaseHelper;
import com.zhongsuwuliu.zhongsulogistics.JavaBean.ContainerBean;
import com.zhongsuwuliu.zhongsulogistics.R;

import java.util.ArrayList;
import java.util.logging.SocketHandler;

public class SetNumActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView tvContainerID,tvKindID,tvWarehouseID;
    private EditText etTemporaryID;
    private Button btScanContainer,btSure,btCancel,btComplete;
    private String containerID = null;
    private DataBaseHelper mDbHelper;
    private SQLiteDatabase mDbReader;
    private ContainerBean mContainerBean;
    private ArrayList<ContainerBean> mContainerBeans = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_num);
        mContainerBean = new ContainerBean();
        initView();
    }

    /*初始化控件*/
    private void initView(){
        tvContainerID = (TextView)findViewById(R.id.set_num_binning_id_text);
        tvKindID = (TextView)findViewById(R.id.set_num_kind_id_text);
        tvWarehouseID = (TextView)findViewById(R.id.set_num_warehouse_id_text);
        etTemporaryID = (EditText)findViewById(R.id.set_num_temporary_id_edit);
        btScanContainer = (Button)findViewById(R.id.set_num_scan_button);
        btSure = (Button)findViewById(R.id.set_num_sure_button);
        btCancel = (Button)findViewById(R.id.set_num_cancel_button);
        btComplete = (Button)findViewById(R.id.set_num_complete_button);
        mDbHelper = new DataBaseHelper(this,"zswl.db3");
        mDbReader = mDbHelper.getReadableDatabase();
        btScanContainer.setOnClickListener(this);
        btSure.setOnClickListener(this);
        btCancel.setOnClickListener(this);
        btComplete.setOnClickListener(this);
    }

    /*扫码后返回结果*/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(RESULT_OK == resultCode){
            Bundle bundle = data.getExtras();
            containerID = bundle.getString("result");

        }
    }

    /*从数据库查询*/
    private int queryContainer(String temporaryID){
        Cursor cursor = mDbReader.rawQuery("select * from Container where id = ?",new String[]{containerID});
        cursor.moveToFirst();
        while (cursor.moveToNext()){
            if(cursor.getString(cursor.getColumnIndex("_id")).equals(containerID)){
                String kindID = cursor.getString(cursor.getColumnIndex("KindID"));
                String warehouseID = cursor.getString(cursor.getColumnIndex("WarehouseID"));
                if(kindID!=null && warehouseID !=null){
                    tvWarehouseID.setText(warehouseID);
                    tvKindID.setText(kindID);
                    tvContainerID.setText(containerID);
                    mContainerBean.setContainerID(containerID);
                    mContainerBean.setKindID(kindID);
                    mContainerBean.setTemporaryID(temporaryID);
                    mContainerBean.setWarehouseID(warehouseID);
                    return 1;
                }else {
                    Toast.makeText(this,"该周转箱没有被绑定,请绑定之后再进行操作",Toast.LENGTH_SHORT).show();
                    return 2;
                }
            }
        }
        return 0;
    }

    /*点击事件*/
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            /*扫码按钮*/
            case R.id.set_num_scan_button:
                startActivityForResult(new Intent(this, CaptureActivity.class),0);
                break;
            /*确认按钮*/
            case R.id.set_num_sure_button:
                setBtnSureClick();
                break;
            /*取消按钮*/
            case R.id.set_num_cancel_button:
                setBtnCancelClick();
                break;
            /*设置完成按钮*/
            case R.id.set_num_complete_button:
                setBtnCompleteClick();
                break;
        }
    }

    /*设置确定设置按钮*/
    private void setBtnSureClick(){
        String temporaryID = etTemporaryID.getText().toString();
        if(containerID != null && temporaryID != null){
            if(queryContainer(temporaryID) == 0){
                Toast.makeText(this,"该周转箱ID没有被录入，请设置后在进行操作",Toast.LENGTH_SHORT).show();
            }else if(queryContainer(temporaryID) == 1){
                addContainerList();
            }
        }
    }

    /*设置完成按钮*/
    private void setBtnCompleteClick(){
        Intent intent = new Intent(this,ContainerManageActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("containerBeans",mContainerBeans);
        intent.putExtras(bundle);
        setResult(RESULT_OK, intent);
    }

    /*设置取消设置按钮*/
    private void setBtnCancelClick(){
        finishActivity(RESULT_CANCELED);
    }


    /*添加到List的条件*/
    private void addContainerList(){
        for(int i = 0; i < mContainerBeans.size(); i++){
            ContainerBean c = mContainerBeans.get(i);
            //如果存在一样的就把原来的替换
            if(c.getContainerID().equals(mContainerBean.getContainerID())){
                mContainerBeans.remove(i);
                mContainerBeans.add(mContainerBean);
                return;
            }else {
                //判断两个临时编号是否相同，相同也不行
                if(c.getTemporaryID().equals(mContainerBean.getTemporaryID())) {
                    Toast.makeText(this,"请不要设置相同临时编号的周转箱",Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        }
        mContainerBeans.add(mContainerBean);
    }
}
