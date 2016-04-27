package com.wzh.www.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.wzh.www.adapter.FindRecyclerAdapter;
import com.wzh.www.adapter.IndexRecyclerAdapter;
import com.wzh.www.adapter.RecyclerViewAdapter;
import com.wzh.www.bean.Events;
import com.wzh.www.bean.News;
import com.wzh.www.bean.User;
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
public class FindFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private LinearLayoutManager mLinearLayoutManager;
    private FindRecyclerAdapter mAdapter;

    private List<Events> mEventsList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.findfragment, container, false);
        setUpViews(rootView);
        return rootView;
    }

    public void setUpViews(View upViews) {
        mRecyclerView = (RecyclerView) upViews.findViewById(R.id.find_recyclerView);
        mSwipeRefreshLayout = (SwipeRefreshLayout) upViews.findViewById(R.id.find_swipeRefrashLayout);
        mRecyclerView.setHasFixedSize(true);
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mAdapter = new FindRecyclerAdapter(mEventsList, getActivity());
        mRecyclerView.setAdapter(mAdapter);

        //设置刷新组件的颜色
        mSwipeRefreshLayout.setColorSchemeResources(R.color.blue,
                R.color.green);
    }

    @Override
    public void onResume() {
        super.onResume();
        getEventsData();
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSwipeRefreshLayout.setRefreshing(true);
                        getEventsData();
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }, 1000);
            }
        });
    }

    public void getEventsData() {
        BmobQuery<Events> query = new BmobQuery<>();
        query.setLimit(30);
        query.order("createdAt");
        query.findObjects(getActivity(), new FindListener<Events>() {
            @Override
            public void onSuccess(List<Events> list) {
                if (list.size() > 0) {
                    mAdapter.clear();
                    for (Events events : list) {
                        mEventsList.add(events);
                    }
                    mAdapter.refresh(mEventsList);
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
        Toast.makeText(getActivity(), toast, Toast.LENGTH_SHORT).show();
    }

}
