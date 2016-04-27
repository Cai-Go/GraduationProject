package com.wzh.www.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.wzh.www.Utils.BaseActivity;
import com.wzh.www.adapter.ContentAdapter;
import com.wzh.www.bean.News;
import com.wzh.www.graduationproject.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * 项目名称：Graduation Project
 * 作者： Created by WWW on 2016/4/25.
 * Date: 2016-04-25
 * Time: 15:17
 * 功能：社团动态展示，根据分类，展示社团的动态数据
 */
public class SocietyContentActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.society_content_recyclerView)
    RecyclerView societyContentRecyclerView;
    @Bind(R.id.society_content_swipRefreshLayout)
    SwipeRefreshLayout societyContentSwipRefreshLayout;

    private LinearLayoutManager mLinearLayoutManager;
    private ContentAdapter mContentAdapter;

    private List<News> mList = new ArrayList<>();

    private String name;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.societycontent_activity);
        ButterKnife.bind(this);
        getName();
        initView();
    }

    private void initView() {
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("动态");
        actionBar.setDisplayHomeAsUpEnabled(true);

        societyContentRecyclerView.setHasFixedSize(true);
        mLinearLayoutManager = new LinearLayoutManager(this);
        societyContentRecyclerView.setLayoutManager(mLinearLayoutManager);
        mContentAdapter = new ContentAdapter(mList, this);
        societyContentRecyclerView.setAdapter(mContentAdapter);
        getData();
        societyContentSwipRefreshLayout.setColorSchemeResources(R.color.blue,
                R.color.green);
        societyContentSwipRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        societyContentSwipRefreshLayout.setRefreshing(true);
                        getData();
                        societyContentSwipRefreshLayout.setRefreshing(false);
                    }
                }, 1000);
            }
        });
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

    public void getData() {
        BmobQuery<News> query = new BmobQuery<>();
        query.addWhereEqualTo("author", name);
        query.setLimit(30);
        query.order("-createdAt");
        query.findObjects(this, new FindListener<News>() {
            @Override
            public void onSuccess(List<News> list) {
//                toast("查到"+list.size());
                if (list.size() > 0) {
                    mContentAdapter.clear();
                    for (News news : list) {
                        mList.add(news);
                    }
                    mContentAdapter.refresh(mList);
                    mContentAdapter.notifyDataSetChanged();
                } else {
                    toast("没有更多数据了");
                }
            }

            @Override
            public void onError(int i, String s) {

            }
        });
    }

    public void getName() {
        name = getIntent().getStringExtra("SocietyName");
    }

    private void toast(String toast) {
        Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
    }
}
