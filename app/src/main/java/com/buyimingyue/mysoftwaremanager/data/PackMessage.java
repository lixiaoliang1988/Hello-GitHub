package com.buyimingyue.mysoftwaremanager.data;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.util.Log;

import com.buyimingyue.mysoftwaremanager.MyApplication;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by lixiaoliang on 2016/7/18.
 * Description:
 */
public class PackMessage {
    public static List<ResolveInfo> getActivityMessage(){
        PackageManager packageManager = MyApplication.getInstance().getPackageManager();
        // 设置<intent-filter>标签内需要满足的条件
        Intent intent = new Intent(Intent.ACTION_MAIN,null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        // 通过queryIntentActivities获取ResolveInfo对象
        List<ResolveInfo> resolveInfos =  packageManager.queryIntentActivities(intent,PackageManager.MATCH_ALL);
        Collections.sort(resolveInfos,new ResolveInfo.DisplayNameComparator(packageManager));
        return  resolveInfos;
    }
    public static List<ApplicationInfo> getApplicationMessage(){
        PackageManager packageManager = MyApplication.getInstance().getPackageManager();
        List<ApplicationInfo> appInfos = packageManager.getInstalledApplications(PackageManager.GET_META_DATA);
        Collections.sort(appInfos, new ApplicationInfo.DisplayNameComparator(packageManager));
        if (appInfos==null)return null;
        return appInfos;
    }
    public static List<ApplicationInfo> getThirdApplicationMessage(){
        List<ApplicationInfo> thirdAppInfos = new ArrayList<>();
        if(getApplicationMessage()==null)return thirdAppInfos;
        for (ApplicationInfo applicationInfo : getApplicationMessage()) {
            // 非系统应用
            if ((applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) <= 0) {
                thirdAppInfos.add(applicationInfo);
            }
            // 系统应用，但更新后变成不是系统应用了
            else if ((applicationInfo.flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) != 0) {
                thirdAppInfos.add(applicationInfo);
            }
        }
        return thirdAppInfos;
    }
}
