package com.wzh.www.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.wzh.www.Utils.BaseActivity;
import com.wzh.www.bean.User;
import com.wzh.www.config.Constant;
import com.wzh.www.graduationproject.R;

import cn.bmob.v3.BmobUser;

/**
 * 项目名称：Graduation Project
 * 作者： Created by WWW on 2016/4/13.
 * Date: 2016-04-13
 * Time: 13:52
 * 功能：
 */
public class PersonInfoActivity extends BaseActivity {

    private Toolbar toolbar;

    private TextView school_name_tv;
    private TextView name_tv;
    private TextView studentId_tv;
    private TextView sex_tv;
    private TextView cademy_tv;
    private TextView grade_tv;
    private TextView society_tv;
    private TextView phoneNumber_tv;
    private TextView email_tv;

    private User curUser;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Constant.USER_INFO_FINISH_FIND_USER:
                    initView();
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_info);
        getCurUser();
    }

    private void initView() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("个人信息");
        actionBar.setDisplayHomeAsUpEnabled(true);

        school_name_tv = (TextView) findViewById(R.id.school_name_tv);
        name_tv = (TextView) findViewById(R.id.name_tv);
        studentId_tv = (TextView) findViewById(R.id.studentId_tv);
        sex_tv = (TextView) findViewById(R.id.sex_tv);
        cademy_tv = (TextView) findViewById(R.id.cademy_tv);
        grade_tv = (TextView) findViewById(R.id.grade_tv);
        society_tv = (TextView) findViewById(R.id.society_tv);
        phoneNumber_tv = (TextView) findViewById(R.id.phoneNumber_tv);
        email_tv = (TextView) findViewById(R.id.email_tv);

        school_name_tv.setText(curUser.getSchoolname());
        name_tv.setText(curUser.getStudentName());
        studentId_tv.setText(curUser.getUsername());
        sex_tv.setText(curUser.getSex());
        cademy_tv.setText(curUser.getCademy());
        grade_tv.setText(curUser.getGrade());
        society_tv.setText(curUser.getSociety());
        phoneNumber_tv.setText(curUser.getMobilePhoneNumber());
        email_tv.setText(curUser.getEmail());

    }

    public void getCurUser() {
        curUser = BmobUser.getCurrentUser(this, User.class);
        if (curUser != null) {
            Message msg = new Message();
            msg.what = Constant.USER_INFO_FINISH_FIND_USER;
            handler.sendMessage(msg);
        }
    }

    private void refresh() {
        getCurUser();
        initView();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 200) {
            refresh();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.personinfo_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.edit_btn:
                jumpToActivity(PersonInfoActivity.this, PersonInfoEditActivity.class, null);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
