package com.wzh.www.Utils;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.wzh.www.Utils.MyApplication;

/**
 * Activity基类
 * Created by WWW on 2016/2/12.
 */
public class BaseActivity extends AppCompatActivity{

    protected MyApplication mMyApplication;
    protected Resources mResources;
    protected Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        initConfigure();
    }

    private void initConfigure() {
        mContext = this;
        if (null == mMyApplication) {
            mMyApplication = MyApplication.getInstance();
        }
        mResources = getResources();
    }

    /**
     * Activity跳转
     *
     * @param context
     * @param targetActivity
     * @param bundle
     */
    public void jumpToActivity(Context context, Class<?> targetActivity, Bundle bundle) {
        Intent intent = new Intent(context, targetActivity);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }


}
