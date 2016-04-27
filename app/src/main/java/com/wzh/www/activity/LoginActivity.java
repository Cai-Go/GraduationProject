package com.wzh.www.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wzh.www.Utils.BaseActivity;
import com.wzh.www.Utils.Util;
import com.wzh.www.bean.User;
import com.wzh.www.graduationproject.R;

import cn.bmob.v3.listener.SaveListener;

/**
 * 登录界面
 * Created by WWW on 2016/2/13.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private EditText student_et;
    private EditText psd_et;
    private Button register_btn;
    private Button login_btn;
    private TextView findpsdback_tv;
    private Toolbar toolbar;

    SharedPreferences sp;

    private String studentId;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.login_activity);

        initView();

    }

    private void initView() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("掌上社团");

        student_et = (EditText) findViewById(R.id.login_name_et);
        psd_et = (EditText) findViewById(R.id.login_password_et);
        register_btn = (Button) findViewById(R.id.register_btn);
        login_btn = (Button) findViewById(R.id.login_btn);
        findpsdback_tv = (TextView) findViewById(R.id.findpsdback_tv);

        register_btn.setOnClickListener(this);
        login_btn.setOnClickListener(this);
        findpsdback_tv.setOnClickListener(this);

        getUserInfo();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_btn:
                jumpToActivity(LoginActivity.this, RegisterActivity.class, null);
                break;
            case R.id.login_btn:
                studentId = student_et.getText().toString();
                password = psd_et.getText().toString();
                if (!Util.isNetworkConnected(this)) {
                    toast("亲，请检查你的网络哦");
                } else if (studentId.equals("") || password.equals("")) {
                    toast("亲，请输入账号或密码");
                } else {
                    User user = new User();
                    user.setUsername(studentId);
                    user.setPassword(password);
                    user.login(this, new SaveListener() {
                        @Override
                        public void onSuccess() {
                            saveUserInfo(studentId, password);
                            jumpToActivity(LoginActivity.this, MainActivity.class, null);
                            finish();
                        }

                        @Override
                        public void onFailure(int i, String s) {
                            toast("您的账号信息有误！" + s.toString());
                        }
                    });
                }


                break;
            case R.id.findpsdback_tv:
                jumpToActivity(LoginActivity.this, ChangePSDActivity.class, null);
                break;
        }

    }

    //保存用户信息
    private void saveUserInfo(String studentId, String password) {
        sp = getSharedPreferences("UserInfo", 0);
        SharedPreferences.Editor editor = sp.edit();
        // 将数据保存到SharedPreferences
        editor.putString("studentId", studentId);
        editor.putString("password", password);
        // 通过eidt()方法来修改里面的内容，通过Commit()方法来提交修改后的内容,并保存
        editor.commit();

    }

    public void getUserInfo() {
        sp = getSharedPreferences("UserInfo", 0);//存储信息
        student_et.setText(sp.getString("studentId", null));//取出学号
        psd_et.setText(sp.getString("password", null));//取出密码

        /********自动登录*********/
        String student_Id = sp.getString("studentId", null);
        String pass_word = sp.getString("password", null);
        Boolean booleans = getIntent().getBooleanExtra("LOGIN", true);//判断登录
        // 实现自动登录后单击“退出”返回登录界面由于记住了密码还是会自动登录，
        // 这样利用Intent来实现点击“退出”后不自动登录
        if (booleans) {
            try {
                Thread.currentThread();
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            User user = new User();
            user.setUsername(student_Id);
            user.setPassword(pass_word);
            user.login(this, new SaveListener() {
                @Override
                public void onSuccess() {
                    jumpToActivity(LoginActivity.this, MainActivity.class, null);
                    finish();
                }

                @Override
                public void onFailure(int i, String s) {
                }
            });
        }


    }


    public void toast(String toast) {
        Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
    }
}
