package com.wzh.www.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.wzh.www.Utils.BaseActivity;
import com.wzh.www.Utils.Util;
import com.wzh.www.bean.Events;
import com.wzh.www.bean.EventsOrder;
import com.wzh.www.bean.User;
import com.wzh.www.graduationproject.R;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.GetListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * 项目名称：Graduation Project
 * 作者： Created by WWW on 2016/4/22.
 * Date: 2016-04-22
 * Time: 09:51
 * 功能：活动报名
 */
public class EventsOrderActivity extends BaseActivity {
    private TextView eventsorder_studentName_et;
    private TextView eventsorder_studentId_et;
    private TextView eventsorder_eventName_et;
    private TextView eventsorder_phoneNumber_et;

    private String eventsName;
    private String studentName;
    private String studentId;
    private String phoneNumber;


    private String currentMember;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eventsorder_activity);
        getData();
        initView();
    }

    private void initView() {

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("活动申请");
        actionBar.setDisplayHomeAsUpEnabled(true);

        eventsorder_studentName_et = (TextView) findViewById(R.id.eventsorder_studentName_et);
        eventsorder_studentId_et = (TextView) findViewById(R.id.eventsorder_studentId_et);
        eventsorder_eventName_et = (TextView) findViewById(R.id.eventsorder_eventName_et);
        eventsorder_phoneNumber_et = (TextView) findViewById(R.id.eventsorder_phoneNumber_et);

    }

    @Override
    protected void onResume() {
        super.onResume();

        BmobUser User = BmobUser.getCurrentUser(this);
        eventsorder_studentId_et.setText(User.getUsername());
        eventsorder_eventName_et.setText(eventsName);

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

                eventsName = eventsorder_eventName_et.getText().toString();
                studentId = eventsorder_studentId_et.getText().toString();
                studentName = eventsorder_studentName_et.getText().toString();
                phoneNumber = eventsorder_phoneNumber_et.getText().toString();


                if (!Util.isNetworkConnected(this)) {
                    toast("检查网络链接");
                } else if (studentName.equals("")
                        || phoneNumber.equals("")) {
                    toast("不能提交空信息");
                } else if (!Util.isPhoneNumberValid(phoneNumber)) {
                    toast("请输入正确的手机号码!");
                } else {
                    if (Util.isFastDoubleClick()) {
                        toast("已经提交");
                    } else {//提交意见
                        EventsOrder eo = new EventsOrder();
                        eo.setStudentId(studentId);
                        eo.setPhoneNumber(phoneNumber);
                        eo.setEventsName(eventsName);
                        eo.setName(studentName);
                        eo.save(this, new SaveListener() {
                            @Override
                            public void onSuccess() {
                                toast("报名成功");
                                Intent back = new Intent(EventsOrderActivity.this,
                                        EventsActivity.class);
                                toast("更新成功");
                                setResult(200, back); // 返回成功码并且返回
                                finish();
                            }

                            @Override
                            public void onFailure(int arg0, String arg1) {
                                toast("请勿重复提交");
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

    public void getData() {
        eventsName = getIntent().getStringExtra("Name");
    }
}
