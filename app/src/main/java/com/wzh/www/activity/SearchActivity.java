package com.wzh.www.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.wzh.www.Utils.BaseActivity;
import com.wzh.www.adapter.ResultAdapter;
import com.wzh.www.bean.Society;
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
 * Time: 16:38
 * 功能：搜索页面
 */
public class SearchActivity extends BaseActivity  {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.search_et)
    EditText searchEt;
    @Bind(R.id.search_recyclerView)
    RecyclerView searchRecyclerView;
    @Bind(R.id.search_image)
    ImageView searchImage;

    private LinearLayoutManager mLinearLayoutManager;
    private ResultAdapter mAdapter;

    private String result;

    private List<Society> mList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("搜索");
        actionBar.setDisplayHomeAsUpEnabled(true);

        searchRecyclerView.setHasFixedSize(true);
        mLinearLayoutManager = new LinearLayoutManager(this);
        searchRecyclerView.setLayoutManager(mLinearLayoutManager);
        mAdapter = new ResultAdapter(mList, this);
        searchRecyclerView.setAdapter(mAdapter);


        searchImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
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
        result = searchEt.getText().toString();
        BmobQuery<Society> query = new BmobQuery<>();
        query.addWhereEqualTo("societyname", result);
        query.findObjects(this, new FindListener<Society>() {
            @Override
            public void onSuccess(List<Society> list) {
                if (list.size() > 0) {
                    mAdapter.clear();
                    for (Society society : list) {
                        mList.add(society);
                    }
                    mAdapter.refresh(mList);
                    mAdapter.notifyDataSetChanged();
                } else {
                    toast("没有更多数据了");
                }
            }

            @Override
            public void onError(int i, String s) {
                toast("数据查询失败");
            }
        });
    }

    private void toast(String toast) {
        Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
    }
}
