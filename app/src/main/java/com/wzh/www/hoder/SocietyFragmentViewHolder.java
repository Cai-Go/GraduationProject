package com.wzh.www.hoder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wzh.www.graduationproject.R;

/**
 * 项目名称：Graduation Project
 * 作者： Created by WWW on 2016/4/12.
 * Date: 2016-04-12
 * Time: 09:23
 * 功能：
 */
public class SocietyFragmentViewHolder extends RecyclerView.ViewHolder {
    public LinearLayout linearLayout;
    public CardView cardView;
    public ImageView imageView;
    public TextView textView;

    public SocietyFragmentViewHolder(View itemView) {
        super(itemView);
        cardView = (CardView) itemView.findViewById(R.id.society_cardview);
        imageView = (ImageView) itemView.findViewById(R.id.society_content_imageview);
        textView = (TextView) itemView.findViewById(R.id.society_title_textview);
        linearLayout = (LinearLayout) itemView.findViewById(R.id.lv);
    }

    public LinearLayout getLinearLayout() {
        return linearLayout;
    }

    public CardView getCardView() {
        return cardView;
    }


}
