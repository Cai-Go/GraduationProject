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
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.wzh.www.activity.MainActivity;
import com.wzh.www.adapter.IndexRecyclerAdapter;
import com.wzh.www.adapter.RecyclerViewAdapter;
import com.wzh.www.adapter.SocietyRecyclerAdapter;
import com.wzh.www.bean.Society;
import com.wzh.www.graduationproject.R;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * 项目名称：Graduation Project
 * 作者： Created by WWW on 2016/4/7.
 * Date: 2016-04-07
 * Time: 16:05
 */
public class SocietyFragment extends Fragment {


    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private SocietyRecyclerAdapter mAdapter;
    private LinearLayoutManager mLinearLayoutManager;

    private List<Society> listSociety = new ArrayList<>();

    public SocietyFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.society_fragment, container, false);
        setUpView(rootView);
        return rootView;
    }


    private void setUpView(View view) {
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.society_swiprefresh);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.society_recyclerView);
        mRecyclerView.setHasFixedSize(true);

        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        mAdapter = new SocietyRecyclerAdapter(listSociety, getActivity());
        mRecyclerView.setAdapter(mAdapter);
        getSocietyData();
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
                        getSocietyData();
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }, 1000);
            }
        });
    }


    public void getSocietyData() {
        BmobQuery<Society> query = new BmobQuery<>();
        query.setLimit(30);
        query.order("objectId");
        query.findObjects(getActivity(), new FindListener<Society>() {
            @Override
            public void onSuccess(List<Society> list) {
                listSociety = list;
                mAdapter.refresh(listSociety);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(int i, String s) {
                toast("无数据");
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    private void toast(String toast) {
        Toast.makeText(getActivity(), toast, Toast.LENGTH_SHORT).show();
    }
}