package com.zhongsuwuliu.zhongsulogistics;


import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.rest.RequestQueue;
import com.zhongsuwuliu.zhongsulogistics.JavaBean.ContainerBean;

import java.util.ArrayList;


/**
 * Created by Administrator on 2016/8/7 0007.
 */
public class Application extends android.app.Application {


    public Application mApplication;
    public static String HTTP_URL;
    public static ArrayList<ContainerBean> sContainerBeans = null;
    public static RequestQueue sRequestQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        this.mApplication = this;
        NoHttp.initialize(mApplication);
        sRequestQueue = NoHttp.newRequestQueue();
    }
}
