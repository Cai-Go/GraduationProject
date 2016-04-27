package com.wzh.www.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.wzh.www.Utils.BaseActivity;
import com.wzh.www.Utils.Util;
import com.wzh.www.graduationproject.R;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.ResetPasswordByEmailListener;

/**
 * 项目名称: GraduationProject
 * 类描述:
 * 创建人: WWW
 * 创建时间: 2016/3/13 20:03
 * 修改人: WWW
 * 修改时间: 2016/3/13 20:03
 * 功能: 修改密码
 */

public class ChangePSDActivity extends BaseActivity implements View.OnClickListener {
    private Toolbar toolbar;
    private Button send_btn;
    private EditText email_et;
    private RelativeLayout layout;
    private ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.changepsd_activity);
        init();
    }

    private void init() {

        layout = (RelativeLayout) findViewById(R.id.rl_reset_psd_finished4);
        send_btn = (Button) findViewById(R.id.findpsdback_btn);
        send_btn.setOnClickListener(this);

        imageView = (ImageView) findViewById(R.id.image_logo);

        email_et = (EditText) findViewById(R.id.email_et);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();

        actionBar.setTitle("密码找回");
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.findpsdback_btn:
                String Email = email_et.getText().toString();//获取邮箱
                if (Email.equals("")) {//判断邮箱是否为空
                    Toast.makeText(getApplicationContext(), "请输入邮箱地址", Toast.LENGTH_SHORT).show();
                } else if (!Util.isEmailValid(Email)) {//判断邮箱格式
                    Toast.makeText(getApplicationContext(), "请输入正确的邮箱的地址", Toast.LENGTH_SHORT).show();
                } else {
                    BmobUser.resetPasswordByEmail(this, Email,
                            new ResetPasswordByEmailListener() {//重置密码
                                @Override
                                public void onFailure(int arg0, String arg1) {
                                    Toast.makeText(getApplicationContext(), "密码重置失败" + arg1,
                                            Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onSuccess() {
                                    email_et.setVisibility(View.GONE);//email不可见且不占用布局
                                    send_btn.setVisibility(View.GONE);//同上
                                    layout.setVisibility(View.VISIBLE);//显示layout
                                    imageView.setVisibility(View.GONE);
                                }
                            });
                }
                break;
        }
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
