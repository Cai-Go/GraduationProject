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
import com.wzh.www.Utils.Util;
import com.wzh.www.bean.SocietyOrder;
import com.wzh.www.bean.User;
import com.wzh.www.config.Constant;
import com.wzh.www.graduationproject.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * 项目名称：Graduation Project
 * 作者： Created by WWW on 2016/4/22.
 * Date: 2016-04-22
 * Time: 16:26
 * 功能：社团申请
 */
public class SocietyOrderActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.societysorder_studentName_et)
    EditText societysorderStudentNameEt;
    @Bind(R.id.societysorder_studentId_et)
    EditText societysorderStudentIdEt;
    @Bind(R.id.societysorder_societyName_et)
    EditText societysorderSocietyNameEt;
    @Bind(R.id.societysorder_cademy_et)
    EditText societysorderCademyEt;
    @Bind(R.id.societysorder_grade_et)
    EditText societysorderGradeEt;
    @Bind(R.id.societysorder_class_et)
    EditText societysorderClassEt;
    @Bind(R.id.societysorder_qq_et)
    EditText societysorderQqEt;
    @Bind(R.id.societysorder_phoneNumber_et)
    EditText societysorderPhoneNumberEt;
    @Bind(R.id.societysorder_reason_et)
    EditText societysorderReasonEt;
    @Bind(R.id.societysorder_sex_et)
    EditText societysorderSexEt;

    private User curUser;
    private String name;


    private String studentName;
    private String studentId;
    private String societyName;
    private String cademy;
    private String grade;
    private String mClass;
    private String qq;
    private String phoneNumber;
    private String reason;
    private String sex;


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case Constant.USER_INFO_FINISH_FIND_USER:
                    initView();
                    break;
            }
        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.societyorder_activity);
        ButterKnife.bind(this);
        getSocietyName();
        getCurrentUser();
    }

    public void getCurrentUser() {
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
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("社团申请");
        actionBar.setDisplayHomeAsUpEnabled(true);

        societysorderStudentNameEt.setText(curUser.getStudentName());
        societysorderStudentIdEt.setText(curUser.getUsername());
        societysorderSocietyNameEt.setText(name);
        societysorderCademyEt.setText(curUser.getCademy());
        societysorderGradeEt.setText(curUser.getGrade());
        societysorderPhoneNumberEt.setText(curUser.getMobilePhoneNumber());
        societysorderSexEt.setText(curUser.getSex());

    }

    private void saveUserSocietyOrder() {
        studentName = societysorderStudentNameEt.getText().toString();
        studentId = societysorderStudentIdEt.getText().toString();
        societyName = societysorderSocietyNameEt.getText().toString();
        cademy = societysorderCademyEt.getText().toString();
        grade = societysorderGradeEt.getText().toString();
        mClass = societysorderClassEt.getText().toString();
        qq = societysorderQqEt.getText().toString();
        phoneNumber = societysorderPhoneNumberEt.getText().toString();
        reason = societysorderReasonEt.getText().toString();
        sex = societysorderSexEt.getText().toString();

        if (!Util.isNetworkConnected(this)) {
            toast("检查网络链接");
        } else if (mClass.equals("")
                || qq.equals("")
                || reason.equals("")) {
            toast("不能提交空信息");
        } else {
            if (Util.isFastDoubleClick()) {
                toast("已经提交");
            } else {
                SocietyOrder so = new SocietyOrder();
                so.setName(studentName);
                so.setStudentId(studentId);
                so.setSex(sex);
                so.setSocietyName(societyName);
                so.setCademy(cademy);
                so.setGrade(grade);
                so.setmClass(mClass);
                so.setQq(qq);
                so.setPhone(phoneNumber);
                so.setReason(reason);
                so.save(this, new SaveListener() {
                    @Override
                    public void onSuccess() {
                        toast("提交成功，审核中...");
                        Intent intent = new Intent(SocietyOrderActivity.this,SocietyActivity.class);
                        setResult(200,intent);
                        finish();
                    }

                    @Override
                    public void onFailure(int i, String s) {
                        toast("提交失败，请重试...");
                    }
                });
            }
        }
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
                onBackPressed();
                break;
            case R.id.send_support_info:
                saveUserSocietyOrder();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    private void toast(String toast) {
        Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
    }

    public void getSocietyName() {
        name = getIntent().getStringExtra("SocietyName");
    }
}
