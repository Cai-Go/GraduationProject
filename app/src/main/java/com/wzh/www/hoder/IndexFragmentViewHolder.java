package com.wzh.www.hoder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wzh.www.graduationproject.R;

/**
 * 项目名称：Graduation Project
 * 作者： Created by WWW on 2016/4/7.
 * Date: 2016-04-07
 * Time: 17:29
 * 功能：
 */
public class IndexFragmentViewHolder extends RecyclerView.ViewHolder {
    public CardView cardView;
    public ImageView imageView;
//    public ImageView imageView1;
//    public ImageView imageView2;
//    public ImageView imageView3;
    public TextView title_tv;
    public TextView content_tv;

    public IndexFragmentViewHolder(View itemView) {
        super(itemView);
        cardView = (CardView) itemView.findViewById(R.id.cardview);
        imageView = (ImageView) itemView.findViewById(R.id.index_content_imageview);
        title_tv = (TextView) itemView.findViewById(R.id.index_title_textview);
        content_tv = (TextView) itemView.findViewById(R.id.index_content_textview);
//
//        imageView1 = (ImageView) itemView.findViewById(R.id.imageView1);
//        imageView2 = (ImageView) itemView.findViewById(R.id.imageView2);
//        imageView3 = (ImageView) itemView.findViewById(R.id.imageView3);


    }
}
