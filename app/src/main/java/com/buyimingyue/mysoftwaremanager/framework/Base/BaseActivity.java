package com.buyimingyue.mysoftwaremanager.framework.Base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by lixiaoliang on 2016/7/17.
 * Description:
 */
public  abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());
        beforeInit();
        initialView();
        viewDataLogic();
    }
    protected abstract int getLayoutResource();
    protected abstract void beforeInit();
    protected  abstract void initialView();
    protected  abstract void viewDataLogic();
    protected <T extends View> T findViewByIdNoCast(int id){
        return (T)findViewById(id);
    }
}
