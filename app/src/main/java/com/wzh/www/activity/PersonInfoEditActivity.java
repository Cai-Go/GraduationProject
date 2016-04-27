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
import android.widget.EditText;
import android.widget.Toast;

import com.wzh.www.Utils.BaseActivity;
import com.wzh.www.bean.User;
import com.wzh.www.config.Constant;
import com.wzh.www.graduationproject.R;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * 项目名称：Graduation Project
 * 作者： Created by WWW on 2016/4/13.
 * Date: 2016-04-13
 * Time: 14:23
 * 功能：
 */
public class PersonInfoEditActivity extends BaseActivity {

    private Toolbar toolbar;

    private EditText school_name_et;
    private EditText name_et;
    private EditText studentId_et;
    private EditText sex_et;
    private EditText cademy_et;
    private EditText grade_et;
    private EditText society_et;
    private EditText phonenuber_et;
    private EditText email_et;

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
        setContentView(R.layout.person_inf_edit);
        setCurUser();
    }

    private void setCurUser() {
        BmobUser bmobUser = BmobUser.getCurrentUser(this);
        BmobQuery<User> query = new BmobQuery<>();
        query.addWhereEqualTo("objectId", bmobUser.getObjectId());//条件查询
        query.findObjects(this, new FindListener<User>() {
            @Override
            public void onSuccess(List<User> list) {
                curUser = list.get(0);
                Message msg = new Message();
                msg.what = Constant.USER_INFO_FINISH_FIND_USER;
                handler.sendMessage(msg);
            }

            @Override
            public void onError(int i, String s) {
                toast("获取当前用户失败");
            }
        });
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("信息修改");
        actionBar.setDisplayHomeAsUpEnabled(true);

        school_name_et = (EditText) findViewById(R.id.school_name_et);
        name_et = (EditText) findViewById(R.id.name_et);
        studentId_et = (EditText) findViewById(R.id.studentId_et);
        sex_et = (EditText) findViewById(R.id.sex_et);
        cademy_et = (EditText) findViewById(R.id.cademy_et);
        grade_et = (EditText) findViewById(R.id.grade_et);
        society_et = (EditText) findViewById(R.id.society_et);
        phonenuber_et = (EditText) findViewById(R.id.phonenuber_et);
        email_et = (EditText) findViewById(R.id.eamil_et);

        school_name_et.setText(curUser.getSchoolname());
        name_et.setText(curUser.getStudentName());
        studentId_et.setText(curUser.getUsername());
        sex_et.setText(curUser.getSex());
        cademy_et.setText(curUser.getCademy());
        grade_et.setText(curUser.getGrade());
        society_et.setText(curUser.getSociety());
        phonenuber_et.setText(curUser.getMobilePhoneNumber());
        email_et.setText(curUser.getEmail());

    }

    private void saveUserInfo() {
        if (curUser == null) {
            toast("请登录");
            jumpToActivity(PersonInfoEditActivity.this, LoginActivity.class, null);
        } else {
            curUser.setSchoolname(school_name_et.getText().toString());
            curUser.setStudentName(name_et.getText().toString());
            curUser.setUsername(studentId_et.getText().toString());
            curUser.setSex(sex_et.getText().toString());
            curUser.setCademy(cademy_et.getText().toString());
            curUser.setGrade(grade_et.getText().toString());
            curUser.setSociety(society_et.getText().toString());
            curUser.setMobilePhoneNumber(phonenuber_et.getText().toString());
            curUser.setEmail(email_et.getText().toString());
            curUser.update(this, curUser.getObjectId(), new UpdateListener() {
                @Override
                public void onSuccess() {
                    Intent back = new Intent(PersonInfoEditActivity.this,
                            PersonInfoActivity.class);
                    toast("更新成功");
                    setResult(200, back); // 返回成功码并且返回
                    finish();
                }

                @Override
                public void onFailure(int i, String s) {
                    toast("更新失败");
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.confirm_mnu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.confirm:
                saveUserInfo();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    private void toast(String toast) {
        Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
    }
}
