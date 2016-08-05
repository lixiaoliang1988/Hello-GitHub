package com.buyimingyue.mysoftwaremanager.ui;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.buyimingyue.mysoftwaremanager.R;
import com.buyimingyue.mysoftwaremanager.data.AppFileName;
import com.buyimingyue.mysoftwaremanager.data.PackMessage;
import com.buyimingyue.mysoftwaremanager.framework.Base.BaseActivity;
import com.buyimingyue.mysoftwaremanager.framework.Utils.ToastUtil;
import com.buyimingyue.mysoftwaremanager.framework.Utils.WindowUtil;
import com.buyimingyue.mysoftwaremanager.ui.Adapter.MyListViewAdapter;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements Handler.Callback, View.OnClickListener{
    private Handler mHandler;
    private ListView listView;
    private List<ApplicationInfo> applicationInfos;
    private List<ApplicationInfo> searchList= new ArrayList<>();
    private EditText editText_search;
    private TextView tv_sure;
    private MyListViewAdapter myListViewAdapter;
    private PackageManager pm;
    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }
    @Override
    protected void beforeInit() {
        mHandler = new Handler(this);
        WindowUtil.setTranslucentWindow(this);
    }

    @Override
    protected void initialView() {
        listView = findViewByIdNoCast(R.id.main_listView);
        applicationInfos = PackMessage.getThirdApplicationMessage();
        tv_sure = findViewByIdNoCast(R.id.tv_sure);
        pm =getPackageManager();
        editText_search = findViewByIdNoCast(R.id.ed_search);
    }

    @Override
    protected void viewDataLogic() {
        myListViewAdapter = new MyListViewAdapter(this,applicationInfos);
        listView.setAdapter(myListViewAdapter);
        tv_sure.setOnClickListener(this);
    }
    private boolean isExit =true;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(event.getAction()== KeyEvent.ACTION_DOWN&&keyCode==KeyEvent.KEYCODE_BACK){
            if(isExit){
                isExit = false;
                ToastUtil.showLongTime("再按一次退出应用");
                mHandler.sendEmptyMessageDelayed(1,2*1000);
            }else System.exit(0);
            return  false;
        }
        return super.onKeyDown(keyCode, event);
    }
    @Override
    public boolean handleMessage(Message message) {
        switch (message.what){
            case 1:
                isExit=true;
                break;
        }
        return false;
    }
    protected void dismissSoftKeyboard(Activity activity) {
        try {
            InputMethodManager inputMethodManage = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManage.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.i("msg","onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("msg","onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        applicationInfos = PackMessage.getThirdApplicationMessage();
        myListViewAdapter.setData(applicationInfos);
        Log.i("msg","onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("msg","onResume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("msg","onStart");
    }

    @Override
    public void onClick(View view) {
            switch (view.getId()){//查询应用
                case R.id.tv_sure:
                    dismissSoftKeyboard(this);
                    searchList.clear();//清除上一次的查询结果
                    String name = editText_search.getText().toString();
                    for ( ApplicationInfo app:applicationInfos) {
                        if (app.loadLabel(pm).toString().contains(name))searchList.add(app);//如果应用名包含查询的字段就添加到集合中去；
                    }
                    myListViewAdapter.setData(searchList);//重置listview适配器的数据
                break;
            }
    }
}
