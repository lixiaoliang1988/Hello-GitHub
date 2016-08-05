package com.buyimingyue.mysoftwaremanager.ui;

import android.os.Handler;
import android.os.Message;
import com.buyimingyue.mysoftwaremanager.R;
import com.buyimingyue.mysoftwaremanager.framework.Base.BaseActivity;
import com.buyimingyue.mysoftwaremanager.framework.Utils.IntentUtil;
import com.buyimingyue.mysoftwaremanager.framework.Utils.WindowUtil;

public class WelcomeActivity extends BaseActivity {
    private Handler mHandler;
    @Override
    protected int getLayoutResource() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void beforeInit() {
        WindowUtil.setTranslucentWindow(this);
    }

    @Override
    protected void initialView() {
        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what==1) {
                    IntentUtil.opentActivity(WelcomeActivity.this, MainActivity.class);
                    WelcomeActivity.this.finish();
                }
            }
        };
    }
    @Override
    protected void viewDataLogic() {
        mHandler.sendEmptyMessageDelayed(1,3*1000);
    }

    @Override
    protected void onDestroy() {
        mHandler.removeCallbacksAndMessages(this);
        super.onDestroy();
    }
}
