package com.wzh.www.activity;

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
import com.wzh.www.bean.Suggestion;
import com.wzh.www.graduationproject.R;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;

/**
 * 项目名称：Graduation Project
 * 作者： Created by WWW on 2016/4/6.
 * Date: 2016-04-06
 * Time: 15:48
 */
public class SuggsetionActivity extends BaseActivity {
    private Toolbar toolbar;

    private EditText name_et;
    private EditText phoneNumber_et;
    private EditText email_et;
    private EditText qq_et;
    private EditText wechat_et;
    private EditText content_et;

    private String name;
    private String phoneNumber;
    private String email;
    private String qq;
    private String wechat;
    private String content;

    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.support_activity);
        init();
    }

    private void init() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("意见反馈");
        actionBar.setDisplayHomeAsUpEnabled(true);

        name_et = (EditText) findViewById(R.id.name_et);
        phoneNumber_et = (EditText) findViewById(R.id.phonenum_et);
        email_et = (EditText) findViewById(R.id.eamil_et);
        qq_et = (EditText) findViewById(R.id.qqNumber_et);
        wechat_et = (EditText) findViewById(R.id.wechat_et);
        content_et = (EditText) findViewById(R.id.message_et);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.send_suggestion_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();//返回
                break;
            case R.id.send_support_info:
                name = name_et.getText().toString();
                phoneNumber = phoneNumber_et.getText().toString();
                email = email_et.getText().toString();
                qq = qq_et.getText().toString();
                wechat = wechat_et.getText().toString();
                content = content_et.getText().toString();
                if (!Util.isNetworkConnected(this)) {
                    toast("检查网络链接");
                } else if (content.equals("")) {
                    toast("不能提交空信息");
                } else {
                    if (Util.isFastDoubleClick()) {
                        toast("已经提交");
                    } else {//提交意见
                        BmobUser User = BmobUser.getCurrentUser(this);
                        Suggestion sg = new Suggestion();
                        sg.setName(name);
                        sg.setStudentId(User.getUsername());
                        sg.setPhoneNumber(phoneNumber);
                        sg.setEmail(email);
                        sg.setQq(qq);
                        sg.setWechat(wechat);
                        sg.setContent(content);
                        sg.save(this, new SaveListener() {
                            @Override
                            public void onSuccess() {
                                toast("谢谢你的宝贵意见，我们会努力的");
                            }

                            @Override
                            public void onFailure(int arg0, String arg1) {
                                toast("对不起，提交失败了");
                            }
                        });
                    }
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void toast(String toast) {
        Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
    }
}
