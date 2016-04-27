package com.wzh.www.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.wzh.www.Utils.BaseActivity;
import com.wzh.www.graduationproject.R;

/**
 * 项目名称：Graduation Project
 * 作者： Created by WWW on 2016/4/14.
 * Date: 2016-04-14
 * Time: 11:10
 * 功能：
 */
public class NewsContentActivity extends BaseActivity implements View.OnClickListener{


    private FloatingActionButton floatingActionButton;
    private CollapsingToolbarLayout collapsingToolbarLayout;

    private TextView author_tv;
    private TextView publishtime_tv;
    private TextView content_tv;
    private ImageView imageview;

    private String newsTitle;
    private String newsAuthor;
    private String newsTime;
    private String newsContent;
    private String newsImage;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        }

        setContentView(R.layout.newscontent_activity);

        //注意getIntentData()和initView()的摆放位置，反了加载不出数据
        getIntentData();
        initView();

    }

    private void initView() {
        final Toolbar mToolbar = (Toolbar) findViewById(R.id.news_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(newsTitle);

        floatingActionButton = (FloatingActionButton) findViewById(R.id.news_flaotingAction_btn);
        floatingActionButton.setOnClickListener(this);

        author_tv = (TextView) findViewById(R.id.author_tv);
        publishtime_tv = (TextView) findViewById(R.id.publishtime_tv);
        content_tv = (TextView) findViewById(R.id.content_tv);

        imageview = (ImageView) findViewById(R.id.news_back);

        author_tv.setText(newsAuthor);
        publishtime_tv.setText(newsTime);
        content_tv.setText(newsContent);
        Glide.with(NewsContentActivity.this)
                .load(newsImage)
                .fitCenter()
                .into(imageview);

    }

    public void getIntentData() {
        newsAuthor = getIntent().getStringExtra("Author");
        newsTime = getIntent().getStringExtra("NewsTime");
        newsContent = getIntent().getStringExtra("Content");
        newsTitle = getIntent().getStringExtra("NewsTitle");
        newsImage = getIntent().getStringExtra("Image");
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.news_flaotingAction_btn:
                Intent intent = new Intent(NewsContentActivity.this,CommentActivity.class);
                intent.putExtra("Title",newsTitle);
                startActivity(intent);
                break;
        }
    }
}
