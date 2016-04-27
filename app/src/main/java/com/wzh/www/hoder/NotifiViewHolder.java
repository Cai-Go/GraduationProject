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
 * Time: 14:44
 * 功能：
 */
public class NotifiViewHolder extends RecyclerView.ViewHolder {
    public CardView cardView;
    public TextView mseeageTitle;
    public TextView messageTime;
    public TextView messageSender;
    public TextView messageContent;

    public NotifiViewHolder(View itemView) {
        super(itemView);
        cardView = (CardView) itemView.findViewById(R.id.messagecard);
        mseeageTitle = (TextView) itemView.findViewById(R.id.message_title);
        messageTime = (TextView) itemView.findViewById(R.id.message_time);
        messageSender = (TextView) itemView.findViewById(R.id.message_sender);
        messageContent = (TextView) itemView.findViewById(R.id.message_content);
    }

}
