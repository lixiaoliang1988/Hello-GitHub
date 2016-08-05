package com.buyimingyue.mysoftwaremanager.framework.Utils;

import android.content.Context;
import android.content.Intent;

/**
 * Created by lixiaoliang on 2016/7/17.
 * Description:
 */
public class IntentUtil {
    public static  void opentActivity(Context context,Class<?> cls){
        Intent intent = new Intent(context,cls);
        context.startActivity(intent);
    }
}
