package com.wzh.www.hoder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.wzh.www.graduationproject.R;

/**
 * 项目名称：Graduation Project
 * 作者： Created by WWW on 2016/4/27.
 * Date: 2016-04-27
 * Time: 10:51
 * 功能：
 */
public class CommentViewHolder extends RecyclerView.ViewHolder {
    public CardView commentCardView;
    public TextView commentTextView;
    public TextView commenUsername;

    public CommentViewHolder(View itemView) {
        super(itemView);
        commentCardView = (CardView) itemView.findViewById(R.id.comment_card);
        commentTextView = (TextView) itemView.findViewById(R.id.comment_textview);
        commenUsername = (TextView) itemView.findViewById(R.id.username);
    }
}
