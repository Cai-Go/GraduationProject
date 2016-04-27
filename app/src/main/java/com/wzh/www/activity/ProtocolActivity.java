package com.wzh.www.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.wzh.www.Utils.BaseActivity;
import com.wzh.www.graduationproject.R;

import java.io.IOException;
import java.io.InputStream;

/**
 * 项目名称：Graduation Project
 * 作者： Created by WWW on 2016/4/7.
 * Date: 2016-04-07
 * Time: 14:44
 */
public class ProtocolActivity extends BaseActivity {
    private Toolbar toolbar;
    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.protocolactivity);
        init();
    }

    private void init() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("服务条款");
        actionBar.setDisplayHomeAsUpEnabled(true);

        textView = (TextView) findViewById(R.id.peotocol_textview);
        textView.setText(readStream(getResources().openRawResource(R.raw.protocol)));

    }

    //读取资源文件-----服务条款
    private String readStream(InputStream is) {
        String res;
        try {
            byte[] buf = new byte[is.available()];
            is.read(buf);
            res = new String(buf, "UTF-8");
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
            res = "";
        }
        return (res);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
