package com.wzh.www.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.wzh.www.activity.NewsContentActivity;
import com.wzh.www.bean.News;
import com.wzh.www.graduationproject.R;
import com.wzh.www.hoder.ContentViewHolder;

import java.util.List;

/**
 * 项目名称：Graduation Project
 * 作者： Created by WWW on 2016/4/25.
 * Date: 2016-04-25
 * Time: 15:40
 * 功能：
 */
public class ContentAdapter extends RecyclerView.Adapter<ContentViewHolder> {
    private Context context;
    private List<News> mNews;

    public ContentAdapter(List<News> mNews, Context context) {
        this.mNews = mNews;
        this.context = context;
    }

    @Override
    public ContentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(context)
                .inflate(R.layout.societycontentcardview, parent, false);
        ContentViewHolder cv = new ContentViewHolder(view);
        return cv;
    }

    @Override
    public void onBindViewHolder(ContentViewHolder holder, final int position) {
        Glide.with(context)
                .load(mNews.get(position).getImage())
                .fitCenter()
                .into(holder.imageView);
        holder.content_tv.setText(mNews.get(position).getSimplecontent());
        holder.title_tv.setText(mNews.get(position).getTitle());

        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NewsContentActivity.class);
                intent.putExtra("NewsTitle", mNews.get(position).getTitle());
                intent.putExtra("Content", mNews.get(position).getContent());
                intent.putExtra("NewsTime", mNews.get(position).getCreatedAt());
                intent.putExtra("Author", mNews.get(position).getAuthor());
                intent.putExtra("Image", mNews.get(position).getImage());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mNews.size();
    }

    public void refresh(List<News> mList) {
        mNews = mList;
        notifyDataSetChanged();
    }

    //清除数据
    public void clear() {
        mNews.clear();
        notifyDataSetChanged();
    }
}
