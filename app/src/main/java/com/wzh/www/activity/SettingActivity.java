package com.wzh.www.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.wzh.www.Utils.BaseActivity;
import com.wzh.www.graduationproject.R;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.update.BmobUpdateAgent;

/**
 * 项目名称：Graduation Project
 * 作者： Created by WWW on 2016/4/13.
 * Date: 2016-04-13
 * Time: 16:36
 * 功能：设置
 */
public class SettingActivity extends BaseActivity implements View.OnClickListener {

    private Toolbar mToolbar;

    private Button exit_btn;
    private Button update_btn;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_activity);
        initView();
        BmobUpdateAgent.initAppVersion(this);
    }


    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("设置");
        actionBar.setDisplayHomeAsUpEnabled(true);

        exit_btn = (Button) findViewById(R.id.exit_btn);
        update_btn = (Button) findViewById(R.id.update_btn);

        exit_btn.setOnClickListener(this);
        update_btn.setOnClickListener(this);

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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.exit_btn:
                BmobUser.logOut(this);
                Intent toLogin = new Intent(SettingActivity.this,
                        LoginActivity.class);
                toLogin.putExtra("LOGIN", false);
                startActivity(toLogin);
                finish();
                break;
            case R.id.update_btn:
                BmobUpdateAgent.forceUpdate(mContext);
//                BmobUpdateAgent.silentUpdate(this);
//                BmobUpdateAgent.setUpdateOnlyWifi(false);// 不仅仅wifi下更新
//                BmobUpdateAgent.update(this);
                break;
        }
    }
}
