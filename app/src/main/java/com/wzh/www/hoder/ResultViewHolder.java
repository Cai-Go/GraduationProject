package com.wzh.www.hoder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wzh.www.graduationproject.R;

/**
 * 项目名称：Graduation Project
 * 作者： Created by WWW on 2016/4/25.
 * Date: 2016-04-25
 * Time: 17:18
 * 功能：
 */
public class ResultViewHolder extends RecyclerView.ViewHolder {

    public CardView cardView;
    public ImageView imageView;
    public TextView name_tv;
    public TextView school_tv;


    public ResultViewHolder(View itemView) {
        super(itemView);

        cardView = (CardView) itemView.findViewById(R.id.search_cardView);
        imageView = (ImageView) itemView.findViewById(R.id.result_image);
        name_tv = (TextView) itemView.findViewById(R.id.result_society_name);
        school_tv = (TextView) itemView.findViewById(R.id.result_society_school);
    }
}
