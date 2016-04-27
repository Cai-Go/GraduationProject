package com.wzh.www.Utils;

import android.app.Application;

/**
 * Created by WWW on 2016/2/12.
 */
public class MyApplication extends Application {

    private static MyApplication myApplication;

    public static MyApplication getInstance() {
        return myApplication;
    }
}
