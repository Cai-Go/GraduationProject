package com.wzh.www.hoder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wzh.www.graduationproject.R;

/**
 * 项目名称：Graduation Project
 * 作者： Created by WWW on 2016/4/21.
 * Date: 2016-04-21
 * Time: 10:40
 * 功能：
 */
public class FindFragmentViewHolder extends RecyclerView.ViewHolder {
    public CardView cardView;
    public ImageView logo_imageview;
    public TextView title_tv;
    public TextView dateTiem_tv;
    public TextView organizerName_tv;

    public FindFragmentViewHolder(View itemView) {
        super(itemView);
        cardView = (CardView) itemView.findViewById(R.id.find_cardView);
        logo_imageview = (ImageView) itemView.findViewById(R.id.logo_imageview);
        title_tv = (TextView) itemView.findViewById(R.id.title_tv);
        dateTiem_tv = (TextView) itemView.findViewById(R.id.dateTiem_tv);
        organizerName_tv = (TextView) itemView.findViewById(R.id.organizerName_tv);
    }
}
