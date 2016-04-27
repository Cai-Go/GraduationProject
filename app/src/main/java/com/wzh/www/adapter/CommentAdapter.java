package com.wzh.www.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wzh.www.bean.Comment;
import com.wzh.www.graduationproject.R;
import com.wzh.www.hoder.CommentViewHolder;

import java.util.List;

/**
 * 项目名称：Graduation Project
 * 作者： Created by WWW on 2016/4/27.
 * Date: 2016-04-27
 * Time: 10:50
 * 功能：评论列表
 */
public class CommentAdapter extends RecyclerView.Adapter<CommentViewHolder> {

    private Context context;
    private List<Comment> mComments;

    public CommentAdapter(List<Comment> mComments, Context context) {
        this.mComments = mComments;
        this.context = context;
    }

    @Override
    public CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(context)
                .inflate(R.layout.comment_list, parent, false);
        CommentViewHolder cv = new CommentViewHolder(view);
        return cv;
    }

    @Override
    public void onBindViewHolder(CommentViewHolder holder, int position) {
        holder.commentTextView.setText(mComments.get(position).getCommentContent());
        holder.commenUsername.setText(mComments.get(position).getUsersName());
    }

    @Override
    public int getItemCount() {
        return mComments.size();
    }

    public void refresh(List<Comment> mList){
        mComments = mList;
        notifyDataSetChanged();
    }
    public void clear(){
        mComments.clear();
        notifyDataSetChanged();
    }

}
