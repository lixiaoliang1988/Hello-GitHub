package com.buyimingyue.mysoftwaremanager.data;

import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lixiaoliang on 2016/7/17.
 * Description:
 */
public class AppFileName {
    private static String url="data/data/";
    public static List<String> getFileName(){
        File file = new File(url);
        try {
            Log.i("msg","有这个文件夹");
            if (file.exists() && file.isDirectory()) {
                Log.i("msg","有这个文件夹");
                List<String> nameFile = new ArrayList<>();
                nameFile = Arrays.asList(file.list());
                File[] fileList = file.listFiles();
//                for (int i = 0; i <fileList.length ; i++) {
//                    nameFile.add(fileList[i].getName());
//                }
                return nameFile;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
