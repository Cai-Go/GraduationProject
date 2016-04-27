package com.wzh.www.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wzh.www.bean.Notifications;
import com.wzh.www.graduationproject.R;
import com.wzh.www.hoder.NotifiViewHolder;

import java.util.List;

/**
 * 项目名称：Graduation Project
 * 作者： Created by WWW on 2016/4/27.
 * Date: 2016-04-27
 * Time: 14:43
 * 功能：消息
 */
public class NotificationsAdapter extends RecyclerView.Adapter<NotifiViewHolder> {
    private Context context;
    private List<Notifications> mList;

    public NotificationsAdapter(List<Notifications> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }

    @Override
    public NotifiViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(context)
                .inflate(R.layout.message_item, parent, false);
        NotifiViewHolder nv = new NotifiViewHolder(view);
        return nv;
    }

    @Override
    public void onBindViewHolder(NotifiViewHolder holder, int position) {
        holder.mseeageTitle.setText(mList.get(position).getNotiTitle());
        holder.messageTime.setText(mList.get(position).getCreatedAt());
        holder.messageSender.setText(mList.get(position).getNotiSender());
        holder.messageContent.setText(mList.get(position).getNotiContent());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void refresh(List<Notifications> list) {
        mList = list;
        notifyDataSetChanged();
    }

    public void clear() {
        mList.clear();
        notifyDataSetChanged();
    }
}
