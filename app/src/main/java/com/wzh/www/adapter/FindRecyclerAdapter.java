package com.wzh.www.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.wzh.www.activity.EventsActivity;
import com.wzh.www.bean.Events;
import com.wzh.www.bean.News;
import com.wzh.www.graduationproject.R;
import com.wzh.www.hoder.FindFragmentViewHolder;

import java.util.List;

/**
 * 项目名称：Graduation Project
 * 作者： Created by WWW on 2016/4/21.
 * Date: 2016-04-21
 * Time: 10:38
 * 功能：
 */
public class FindRecyclerAdapter extends RecyclerView.Adapter<FindFragmentViewHolder> {
    private Context context;
    private List<Events> eventsList;

    public FindRecyclerAdapter(List<Events> eventsList, Context context) {
        this.eventsList = eventsList;
        this.context = context;
    }

    @Override
    public FindFragmentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.find_item_view, parent, false);
        FindFragmentViewHolder fv = new FindFragmentViewHolder(view);
        return fv;
    }

    @Override
    public void onBindViewHolder(FindFragmentViewHolder holder, final int position) {
        Glide.with(context)
                .load(eventsList.get(position).getLogo())
                .fitCenter()
                .into(holder.logo_imageview);

        holder.title_tv.setText(eventsList.get(position).getEventsName());
        holder.dateTiem_tv.setText(eventsList.get(position).getEventTime());
        holder.organizerName_tv.setText(eventsList.get(position).getEventOrganizer());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, EventsActivity.class);
                intent.putExtra("EventsName",eventsList.get(position).getEventsName());
                intent.putExtra("EventsTime",eventsList.get(position).getEventTime());
                intent.putExtra("EventsOrganizer",eventsList.get(position).getEventOrganizer());
                intent.putExtra("EventsLocation",eventsList.get(position).getEventLocation());
                intent.putExtra("EventsLimitMember",eventsList.get(position).getEventLimitMember());
                intent.putExtra("EventsStatus",eventsList.get(position).getEventStatus());
                intent.putExtra("EventsContent",eventsList.get(position).getEventContent());
                intent.putExtra("EventImage",eventsList.get(position).getEventImage());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return eventsList.size();
    }

    public void refresh(List<Events> mList) {
        eventsList = mList;
        notifyDataSetChanged();
    }

    //清除数据
    public void clear() {
        eventsList.clear();
        notifyDataSetChanged();
    }

}
