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
import android.widget.Toast;

import com.wzh.www.adapter.NotificationsAdapter;
import com.wzh.www.bean.Notifications;
import com.wzh.www.graduationproject.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * 项目名称：Graduation Project
 * 作者： Created by WWW on 2016/4/7.
 * Date: 2016-04-07
 * Time: 17:14
 * 功能：消息界面
 */
public class MessageFragment extends Fragment {
    @Bind(R.id.message_recycler)
    RecyclerView messageRecycler;
    @Bind(R.id.message_swipeRefresh)
    SwipeRefreshLayout messageSwipeRefresh;

    private LinearLayoutManager mLinearLayoutManager;
    private NotificationsAdapter mAdapter;

    private List<Notifications> mNotifiList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.message_fragment, container, false);
        ButterKnife.bind(this, rootView);
        setUpView();
        return rootView;
    }

    private void setUpView() {
        messageRecycler.setHasFixedSize(true);
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        messageRecycler.setLayoutManager(mLinearLayoutManager);
        mAdapter = new NotificationsAdapter(mNotifiList, getActivity());
        messageRecycler.setAdapter(mAdapter);
        getMessage();
        messageSwipeRefresh.setColorSchemeColors(R.color.blue, R.color.green);
        messageSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        messageSwipeRefresh.setRefreshing(true);
                        getMessage();
                        messageSwipeRefresh.setRefreshing(false);
                    }
                }, 1000);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public void getMessage() {
        BmobQuery<Notifications> query = new BmobQuery<>();
        query.setLimit(10);
        query.order("-createdAt");
        query.findObjects(getActivity(), new FindListener<Notifications>() {
            @Override
            public void onSuccess(List<Notifications> list) {
                if (list.size() > 0) {
                    mAdapter.clear();
                    for (Notifications noti : list) {
                        mNotifiList.add(noti);
                    }
                    mAdapter.refresh(mNotifiList);
                    mAdapter.notifyDataSetChanged();
                } else {
                    toast("就这几个通知了-_-!!!");
                }
            }

            @Override
            public void onError(int i, String s) {
                toast("暂时没有通知");
            }
        });
    }

    private void toast(String toast) {
        Toast.makeText(getActivity(), toast, Toast.LENGTH_SHORT).show();
    }
}
