package atest.test.com.app.app;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import atest.test.com.app.service.CheckService;


/**
 * Created by Shadow on 2017/10/13.
 */

public class MyApplication extends Application {

    private static Context context;

    private static final String TEST_BASE_URL = "http://111.205.104.180:80/Push/be/";
    private static final String REAL_BASE_URL = "http://39.104.49.156:80/Push/wi";
    private static final String BASE_URL = TEST_BASE_URL;


    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        //每次启动 打开一次服务  为了检测是否与服务器连接
        openService();
    }

    private void openService() {
        startService(new Intent(this, CheckService.class));
    }

    public static Context getContext() {
        return context;
    }

    public static String getBaseUrl() {
        return BASE_URL;
    }
}
