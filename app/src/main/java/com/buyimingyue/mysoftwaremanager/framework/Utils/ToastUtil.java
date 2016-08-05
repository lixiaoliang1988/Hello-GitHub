package com.buyimingyue.mysoftwaremanager.framework.Utils;

import android.widget.Toast;

import com.buyimingyue.mysoftwaremanager.MyApplication;

/**
 * Created by lixiaoliang on 2016/7/17.
 * Description:
 */
public class ToastUtil {
   public static void showShortTime(String msg){
       Toast.makeText(MyApplication.getInstance(),msg,Toast.LENGTH_SHORT).show();
   }
    public static void showLongTime(String msg){
        Toast.makeText(MyApplication.getInstance(),msg,Toast.LENGTH_LONG).show();
    }
}
