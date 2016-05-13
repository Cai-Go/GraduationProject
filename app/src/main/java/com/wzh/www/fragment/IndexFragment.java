package com.wzh.www.fragment;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.wzh.www.activity.MainActivity;
import com.wzh.www.adapter.IndexRecyclerAdapter;
import com.wzh.www.bean.News;
import com.wzh.www.graduationproject.R;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * 项目名称：Graduation Project
 * 作者： Created by WWW on 2016/4/7.
 * Date: 2016-04-07
 * Time: 16:04
 */
public class IndexFragment extends Fragment {

    private Context context;

    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private IndexRecyclerAdapter mAdapter;
    private LinearLayoutManager mLinearLayoutManager;

    private List<News> mList = new ArrayList<>();


    public IndexFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.idnex_fragment, container, false);
        setUpViews(rootView);
        return rootView;
    }

    public void setUpViews(View upViews) {
        mSwipeRefreshLayout = (SwipeRefreshLayout) upViews.findViewById(R.id.index_swipeRefresh);
        mRecyclerView = (RecyclerView) upViews.findViewById(R.id.index_recyclerView);
        //使RecyclerView保持固定的大小，该信息被用于自身的优化
        mRecyclerView.setHasFixedSize(true);
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mAdapter = new IndexRecyclerAdapter(mList, getActivity());
        mRecyclerView.setAdapter(mAdapter);
        getNewData();
        //设置刷新组件的颜色
        mSwipeRefreshLayout.setColorSchemeResources(R.color.blue,
                R.color.green);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSwipeRefreshLayout.setRefreshing(true);
                        getNewData();
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }, 1000);
            }
        });
    }

    public void getNewData() {
        BmobQuery<News> query = new BmobQuery();
        query.setLimit(30);
        query.order("-createdAt");
        query.findObjects(getActivity(), new FindListener<News>() {
            @Override
            public void onSuccess(List<News> list) {
                if (list.size() > 0) {
                    mAdapter.clear();
                    for (News news : list) {
                        mList.add(news);
                    }
                    mAdapter.refresh(mList);
                    mAdapter.notifyDataSetChanged();
                } else {
                    toast("没有更多数据了");
                }
//                mList = list;
//                mAdapter.refresh(mList);
//                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(int i, String s) {
                toast("数据查询失败");
            }
        });
    }


    private void toast(String toast) {
        Toast.makeText(getActivity(), toast, Toast.LENGTH_SHORT).show();
    }
}
