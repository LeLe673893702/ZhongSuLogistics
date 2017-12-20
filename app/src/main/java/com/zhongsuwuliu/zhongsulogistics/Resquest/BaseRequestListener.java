package com.zhongsuwuliu.zhongsulogistics.Resquest;

import com.yolanda.nohttp.rest.OnResponseListener;
import com.yolanda.nohttp.rest.Response;

/**
 * Created by 刺雒 on 2016/11/26.
 */
public class BaseRequestListener implements OnResponseListener<String> {
    @Override
    public void onStart(int i) {
    }

    @Override
    public void onSucceed(int i, Response<String> response) {
    }

    @Override
    public void onFailed(int i, String s, Object o, Exception e, int i1, long l) {

    }

    @Override
    public void onFinish(int i) {

    }
}
