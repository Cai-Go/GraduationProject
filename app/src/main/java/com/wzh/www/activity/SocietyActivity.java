package com.wzh.www.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.wzh.www.Utils.BaseActivity;
import com.wzh.www.graduationproject.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 项目名称：Graduation Project
 * 作者： Created by WWW on 2016/4/15.
 * Date: 2016-04-15
 * Time: 11:46
 * 功能：
 */
public class SocietyActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.order_btn)
    Button orderBtn;
    @Bind(R.id.enter_btn)
    Button enterBtn;
    private TextView societyname_tv;
    private ImageView QRCode_iv;
    private TextView societySchool_tv;
    private TextView societyInfo_tv;
    private TextView societyleader_tv;
    private TextView societymembers_tv;
    private ImageView logo_iv;

    private String societyname;
    private String QRCode;
    private String societySchool;
    private String societyInfo;
    private String societyleader;
    private String societymembers;
    private String logo;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.society_activity);
        ButterKnife.bind(this);
        getIntentData();
        initView();
    }

    private void initView() {
        final Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(societyname);
        actionBar.setDisplayHomeAsUpEnabled(true);


        societyname_tv = (TextView) findViewById(R.id.societyname_tv);
//        QRCode_iv = (ImageView) findViewById(R.id.QRCode_iv);
        societySchool_tv = (TextView) findViewById(R.id.societySchool_tv);
        societyInfo_tv = (TextView) findViewById(R.id.societyInfo_tv);
        societyleader_tv = (TextView) findViewById(R.id.societyleader_tv);
        societymembers_tv = (TextView) findViewById(R.id.societymembers_tv);
        logo_iv = (ImageView) findViewById(R.id.logo_iv);


        orderBtn.setOnClickListener(this);
        enterBtn.setOnClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        societyname_tv.setText(societyname);
        societySchool_tv.setText(societySchool);
        societyInfo_tv.setText(societyInfo);
        societyleader_tv.setText(societyleader);
        societymembers_tv.setText(societymembers);

//        Glide.with(SocietyActivity.this)
//                .load(QRCode)
//                .fitCenter()
//                .into(QRCode_iv);

        Glide.with(SocietyActivity.this)
                .load(logo)
                .fitCenter()
                .into(logo_iv);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 200) {
            refresh();
        }
    }

    private void refresh() {
        initView();
        getIntentData();
    }

    public void getIntentData() {
        societyname = getIntent().getStringExtra("SocietyName");
//        QRCode = getIntent().getStringExtra("QRCode");
        societySchool = getIntent().getStringExtra("SchoolName");
        societyInfo = getIntent().getStringExtra("SocietyInfo");
        societyleader = getIntent().getStringExtra("Leader");
        societymembers = getIntent().getStringExtra("Member");
        logo = getIntent().getStringExtra("Logo");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
//            case R.id.favoriate_menu:
//                /*
//                *未实现：
//                * 点击变为实心，再点击变为空心，
//                *需要获取点击的状态
//                * */
//                boolean isChecked = item.isChecked();
//                if (!isChecked) {
//                    item.setIcon(R.mipmap.ic_favoriated);
//                    Toast.makeText(SocietyActivity.this, "你关注了", Toast.LENGTH_SHORT).show();
//                } else {
//                    item.setIcon(R.mipmap.ic_favoriate);
//                }
//
//                break;
        }
        return super.onOptionsItemSelected(item);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.favoriaty_menu, menu);
//        return true;
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.order_btn:
                Intent intent = new Intent(SocietyActivity.this, SocietyOrderActivity.class);
                intent.putExtra("SocietyName", societyname);
                startActivity(intent);
                break;
            case R.id.enter_btn:
                Intent intent_enter = new Intent(SocietyActivity.this, SocietyContentActivity.class);
                intent_enter.putExtra("SocietyName", societyname);
                startActivity(intent_enter);
                break;
        }
    }

}
