package com.buyimingyue.mysoftwaremanager.framework.Utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

/**
 * Created by lixiaoliang on 2016/7/17.
 * Description:
 */
public class WindowUtil {
    public static void setTranslucentWindow(Activity activity){ //状态栏透明只有Android 4.4 以上才支持
        if(activity.getActionBar()!=null)activity.getActionBar().hide();
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
            Window window =  activity.getWindow();
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            layoutParams.flags |=WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            window.setAttributes(layoutParams);
        }
    }
}
