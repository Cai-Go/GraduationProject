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
 * Time: 15:44
 * 功能：
 */
public class ContentViewHolder  extends RecyclerView.ViewHolder{


    public CardView mCardView;
    public ImageView imageView;
    public  TextView title_tv;
    public TextView content_tv;


    public ContentViewHolder(View itemView) {
        super(itemView);
        mCardView = (CardView) itemView.findViewById(R.id.society_content_cardview);
        imageView = (ImageView) itemView.findViewById(R.id.society_content__imageview);
        title_tv = (TextView) itemView.findViewById(R.id.society_content_title_textview);
        content_tv = (TextView) itemView.findViewById(R.id.society_content__textview);
    }
}
