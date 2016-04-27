package com.wzh.www.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.wzh.www.Utils.BaseActivity;
import com.wzh.www.config.Constant;
import com.wzh.www.graduationproject.R;

import cn.bmob.v3.Bmob;

/**
 * 引导页面
 * Created by WWW on 2016/2/12.
 */
public class SplashActivity extends BaseActivity {

    private Boolean isFirstUse;//是否第一次使用

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        }

        setContentView(R.layout.splashactivity);
        //初始化 Bmob SDK
        Bmob.initialize(this, Constant.BMOB_APPLICATION_ID);
        //开启线程
        MyThread myThread = new MyThread();
        new Thread(myThread).start();
    }

    class MyThread implements Runnable {

        @Override
        public void run() {
//            Log.d("SplsahActivity","开启线程");
            try {
                Thread.sleep(2000);// 延迟2秒
                @SuppressWarnings("deprecation")
                SharedPreferences preferences = getSharedPreferences("isFirstUse",
                        MODE_WORLD_READABLE);// //读取SharedPreferences中需要的数据
                isFirstUse = preferences.getBoolean("isFirstUse", true);
                /**
                 * 如果用户不是第一次使用则直接调转到显示界面,否则调转到引导界面
                 */
                if (isFirstUse) {
                    startActivity(new Intent(SplashActivity.this, SplashActivity.class));
                } else {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                }
                finish();
                // 实例化Editor对象
                SharedPreferences.Editor editor = preferences.edit();
                // 存入数据
                editor.putBoolean("isFirstUse", false);
                // 提交修改
                editor.commit();

            } catch (InterruptedException e) {
            }

        }
    }
}
