package com.llkj.creditchecking;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.llkj.creditchecking.base.BaseActivity;
import com.llkj.creditchecking.module.CacheDemoActivity;
import com.llkj.creditchecking.utils.LogUtils;

import cn.jpush.android.api.JPushInterface;

/**
 * 闪屏界面 单图界面2秒后跳转到主界面
 */
public class GuideActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.SplashTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_guide);
        setUpView();
    }

    protected void setUpView() {

        LogUtils.e("设备号："+ JPushInterface.getRegistrationID(this));
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(GuideActivity.this, CacheDemoActivity.class));
                finish();
            }
        }, 2000);

    }



}

