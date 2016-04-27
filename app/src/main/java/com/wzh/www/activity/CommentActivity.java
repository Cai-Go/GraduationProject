package com.wzh.www.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.wzh.www.Utils.BaseActivity;
import com.wzh.www.Utils.Util;
import com.wzh.www.adapter.CommentAdapter;
import com.wzh.www.bean.Comment;
import com.wzh.www.bean.User;
import com.wzh.www.graduationproject.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * 项目名称：Graduation Project
 * 作者： Created by WWW on 2016/4/27.
 * Date: 2016-04-27
 * Time: 09:47
 * 功能：
 */
public class CommentActivity extends BaseActivity {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.comment_text)
    EditText commentText;
    @Bind(R.id.comment_recyclerView)
    RecyclerView commentRecyclerView;
    @Bind(R.id.comment_swipeRefresh)
    SwipeRefreshLayout commentSwipeRefresh;

    private String newsTitle;
    private String comment;
    private String curUser;

    private LinearLayoutManager mLinearLayoutManager;
    private CommentAdapter mAdapter;

    private List<Comment> mCommentList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comment_activity);
        ButterKnife.bind(this);
        getData();
        initView();

    }

    private void initView() {
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("评论");
        actionBar.setDisplayHomeAsUpEnabled(true);

        commentRecyclerView.setHasFixedSize(true);
        mLinearLayoutManager = new LinearLayoutManager(this);
        commentRecyclerView.setLayoutManager(mLinearLayoutManager);
        mAdapter = new CommentAdapter(mCommentList, this);
        commentRecyclerView.setAdapter(mAdapter);
        getComment();
    }

    @Override
    protected void onResume() {
        super.onResume();
        commentSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        commentSwipeRefresh.setRefreshing(true);
                        getComment();
                        commentSwipeRefresh.setRefreshing(false);
                    }
                }, 1000);
            }
        });
    }

    public void getData() {
        newsTitle = getIntent().getStringExtra("Title");
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
                sendComment();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void sendComment() {
        comment = commentText.getText().toString();

        if (comment.equals("")) {
            toast("不能提交空信息");
        } else {
            BmobUser bmobUser = BmobUser.getCurrentUser(this);
            curUser = bmobUser.getUsername();
            Comment cm = new Comment();
            cm.setCommentContent(comment);
            cm.setUsersName(curUser);
            cm.setNewsTitle(newsTitle);
            cm.save(this, new SaveListener() {
                @Override
                public void onSuccess() {
                    toast("评论成功");
                    getComment();
                }

                @Override
                public void onFailure(int i, String s) {
                    toast("评论成功");
                }
            });
        }
    }

    public void getComment() {
        BmobQuery<Comment> query = new BmobQuery<>();
        query.addWhereEqualTo("newsTitle", newsTitle);
        query.setLimit(30);
        query.order("-createdAt");
        query.findObjects(this, new FindListener<Comment>() {
            @Override
            public void onSuccess(List<Comment> list) {
                if (list.size() > 0) {
                    mAdapter.clear();
                    for (Comment comment : list) {
                        mCommentList.add(comment);
                    }
                    mAdapter.refresh(mCommentList);
                    mAdapter.notifyDataSetChanged();
                } else {
                    toast("没有更多数据了");
                }
            }

            @Override
            public void onError(int i, String s) {
                toast("查找失败");
            }
        });
    }

    private void toast(String toast) {
        Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
    }
}
