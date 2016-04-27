package com.wzh.www.activity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wzh.www.Utils.BaseActivity;
import com.wzh.www.adapter.RecyclerViewAdapter;
import com.wzh.www.bean.Content;
import com.wzh.www.graduationproject.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：Graduation Project
 * 作者： Created by WWW on 2016/4/6.
 * Date: 2016-04-06
 * Time: 15:46
 */
public class AboutActivity extends BaseActivity implements View.OnClickListener {
    private TextView about_info_tv;
    private Toolbar toolbar;
    private List<Content> content;
    private RecyclerView conentrecyclerView;
    private RecyclerViewAdapter mAdapter;
    private TextView version_textview;
    private Button protocol_btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_activity);
        init();
    }

    private void init() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("关于我们");
        actionBar.setDisplayHomeAsUpEnabled(true);

        about_info_tv = (TextView) findViewById(R.id.about_info);
        about_info_tv.setText(readStream(getResources().openRawResource(R.raw.about_info)));

        version_textview = (TextView) findViewById(R.id.version_textview);
        try {
            getVersion();
        } catch (Exception e) {
            e.printStackTrace();
        }

        protocol_btn = (Button) findViewById(R.id.protocol_btn);
        protocol_btn.setOnClickListener(this);

        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        conentrecyclerView = (RecyclerView) findViewById(R.id.about_recyclerview);
        initPersonData();
        //设置高亮
        conentrecyclerView.setHasFixedSize(true);
        //设置布局管理----线性布局
        conentrecyclerView.setLayoutManager(mLinearLayoutManager);
        //设置适配器
        mAdapter = new RecyclerViewAdapter(content, this);
        //绑定适配
        conentrecyclerView.setAdapter(mAdapter);
    }

    //读取文件
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

    private void initPersonData() {
        content = new ArrayList<>();
        content.add(new Content
                (getString(R.string.first_title),
                        getString(R.string.first_content)));
        content.add(new Content(getString(R.string.second_title),
                getString(R.string.second_content)));
        content.add(new Content(getString(R.string.third_title),
                getString(R.string.third_content)));
        content.add(new Content(getString(R.string.fourth_title),
                getString(R.string.fourth_content)));
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

    //获取当前版本号
    public String getVersion() throws Exception {
        PackageManager pakageManager = getPackageManager();
        PackageInfo packageInfo = pakageManager.getPackageInfo(getPackageName(), 0);
        String version = packageInfo.versionName;
        version_textview.setText("V" + version);
        return version;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.protocol_btn:
                jumpToActivity(this, ProtocolActivity.class, null);
                finish();
                break;
        }
    }
}
