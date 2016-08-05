package com.buyimingyue.mysoftwaremanager;

import android.app.Application;

/**
 * Created by lixiaoliang on 2016/7/17.
 * Description:
 */
public class MyApplication extends Application {
    private static MyApplication myApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
    }

    public static MyApplication getInstance(){
        return myApplication;
    }
}
