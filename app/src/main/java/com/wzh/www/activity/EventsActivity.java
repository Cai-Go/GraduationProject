package com.wzh.www.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wzh.www.Utils.BaseActivity;
import com.wzh.www.graduationproject.R;

/**
 * 项目名称：Graduation Project
 * 作者： Created by WWW on 2016/4/21.
 * Date: 2016-04-21
 * Time: 14:33
 * 功能：活动详情页
 */
public class EventsActivity extends BaseActivity implements View.OnClickListener {


    private ImageView events_image;

    private TextView eventsName_tv;
    private TextView eventstime_tv;
    private TextView eventsOrganizer_tv;
    private TextView eventsLocation_tv;
    private TextView eventsLimitMember_tv;
    private TextView eventsStatus_tv;
    private TextView eventsContent_tv;

    private Button submit_btn;


    private String image;
    private String name;
    private String time;
    private String organizer;
    private String loaction;
    private String member;
    private String status;
    private String content;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.events_activity);
        getData();
        initView();
    }

    private void initView() {
        final Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("活动详情页");
        actionBar.setDisplayHomeAsUpEnabled(true);


        events_image = (ImageView) findViewById(R.id.events_image);

        eventsName_tv = (TextView) findViewById(R.id.eventsName_tv);
        eventstime_tv = (TextView) findViewById(R.id.eventstime_tv);
        eventsOrganizer_tv = (TextView) findViewById(R.id.eventsOrganizer_tv);
        eventsLocation_tv = (TextView) findViewById(R.id.eventsLocation_tv);
        eventsLimitMember_tv = (TextView) findViewById(R.id.eventsLimitMember_tv);
        eventsStatus_tv = (TextView) findViewById(R.id.eventsStatus_tv);
        eventsContent_tv = (TextView) findViewById(R.id.eventsContent_tv);

        submit_btn = (Button) findViewById(R.id.submit_btn);
        submit_btn.setOnClickListener(this);


    }

    public void getData() {
        image = getIntent().getStringExtra("EventImage");
        name = getIntent().getStringExtra("EventsName");
        time = getIntent().getStringExtra("EventsTime");
        organizer = getIntent().getStringExtra("EventsOrganizer");
        loaction = getIntent().getStringExtra("EventsLocation");
        member = getIntent().getStringExtra("EventsLimitMember");
        status = getIntent().getStringExtra("EventsStatus");
        content = getIntent().getStringExtra("EventsContent");

    }


    @Override
    protected void onResume() {
        super.onResume();
        Glide.with(EventsActivity.this)
                .load(image)
                .placeholder(R.drawable.tests)
                .fitCenter()
                .into(events_image);

        eventsName_tv.setText(name);
        eventstime_tv.setText(time);
        eventsOrganizer_tv.setText(organizer);
        eventsLocation_tv.setText(loaction);
        eventsLimitMember_tv.setText(member);
        eventsStatus_tv.setText(status);
        eventsContent_tv.setText(content);


    }

    private void refresh() {
        initView();
        getData();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 200) {
            refresh();
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(EventsActivity.this, EventsOrderActivity.class);
        intent.putExtra("Name", name);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
