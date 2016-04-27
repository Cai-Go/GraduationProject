package com.wzh.www.hoder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.wzh.www.graduationproject.R;

/**
 * 项目名称：Graduation Project
 * 作者： Created by WWW on 2016/4/7.
 * Date: 2016-04-07
 * Time: 10:46
 */
public class ContentHolder extends RecyclerView.ViewHolder {
    public CardView cardView;
    public TextView titleTextView;
    public TextView contentTextView;

    public ContentHolder(final View itemView) {
        super(itemView);
        cardView  = (CardView) itemView.findViewById(R.id.about_card);
        titleTextView  = (TextView) itemView.findViewById(R.id.about_title);
        contentTextView = (TextView) itemView.findViewById(R.id.about_content);

    }
}
