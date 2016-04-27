package com.wzh.www.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.wzh.www.Utils.BaseActivity;
import com.wzh.www.Utils.Util;
import com.wzh.www.bean.User;
import com.wzh.www.graduationproject.R;

import cn.bmob.v3.listener.SaveListener;

/**
 * 项目名称:GraduationProject
 * 类描述:
 * 创建人:WWW
 * 创建时间:2016/3/13 19:20
 * 修改人:WWW
 * 修改时间:2016/3/13 19:20
 * 修改备注:注册界面
 */
public class RegisterActivity extends BaseActivity {

    private Toolbar toolbar;

    private EditText name_et;
    private EditText studentId_et;
    private EditText mobliePhoneNumber_et;
    private EditText email_et;
    private EditText password_et;
    private EditText confirm_password_et;

    private String username = null;
    private String mobliePhoneNumber = null;
    private String email = null;
    private String studentname= null;
    private String password = null;
    private String confirmpsd = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        init();

    }

    private void init() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("注册");
        actionBar.setDisplayHomeAsUpEnabled(true);
        name_et = (EditText) findViewById(R.id.name_edit);
        studentId_et = (EditText) findViewById(R.id.studentId_edit);
        password_et = (EditText) findViewById(R.id.password_edit);
        confirm_password_et = (EditText) findViewById(R.id.confirm_password_edit);
        mobliePhoneNumber_et = (EditText) findViewById(R.id.phonenuber_edit);
        email_et = (EditText) findViewById(R.id.email_edit);

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
                username = name_et.getText().toString();
                studentname = studentId_et.getText().toString();
                password = password_et.getText().toString();
                confirmpsd = confirm_password_et.getText().toString();
                mobliePhoneNumber = mobliePhoneNumber_et.getText().toString();
                email = email_et.getText().toString();

                if (!Util.isNetworkConnected(this)) {
                    toast("亲，请检查网络连接");
                } else if (username.equals("")) {
                    toast("亲，请输入用户名");
                } else if (studentname.equals("")) {
                    toast("亲，请输入用户名");
                } else if (!Util.isUserNameValid(username)) {
                    toast("亲，请使用学号注册");
                } else if (!confirmpsd.equals(password)) {
                    toast("亲，两次密码输入不一致哦");
                } else if (!Util.isPhoneNumberValid(mobliePhoneNumber)) {
                    toast("亲, 请输入正确的手机号码");
                } else if (email.equals("")) {
                    toast("亲，请输入邮箱");
                } else if (!Util.isEmailValid(email)) {
                    toast("亲，请输入正确的邮箱");
                } else {
                    if (Util.isFastDoubleClick()) {
                        toast("亲，已经提交了");
                    } else {
                        //提交注册信息
                        User user = new User();
                        user.setUsername(username);
                        user.setEmail(email);
                        user.setStudentName(studentname);
                        user.setPassword(password);
                        user.setMobilePhoneNumber(mobliePhoneNumber);
                        user.signUp(this, new SaveListener() {
                            @Override
                            public void onSuccess() {
                                toast("亲, 恭喜你注册成功");
                                Intent backLogin = new Intent(
                                        RegisterActivity.this, LoginActivity.class);
                                startActivity(backLogin);
                                finish();
                            }

                            @Override
                            public void onFailure(int i, String s) {
                                toast("亲, 被人捷足先登了, 可能用户名已经注册了"+s);
                            }
                        });
                    }
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void toast(String toast) {
        Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
    }
}
