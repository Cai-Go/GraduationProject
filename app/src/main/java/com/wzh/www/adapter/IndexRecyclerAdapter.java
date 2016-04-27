package com.wzh.www.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.wzh.www.activity.NewsContentActivity;
import com.wzh.www.bean.News;
import com.wzh.www.fragment.IndexFragment;
import com.wzh.www.graduationproject.R;
import com.wzh.www.hoder.IndexFragmentViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：Graduation Project
 * 作者： Created by WWW on 2016/4/7.
 * Date: 2016-04-07
 * Time: 17:28
 * 功能：
 */
public class IndexRecyclerAdapter extends RecyclerView.Adapter<IndexFragmentViewHolder> {
    private Context context;
    private List<News> news;


    public IndexRecyclerAdapter(List<News> news, Context context) {
        this.news = news;
        this.context = context;
    }

    @Override
    public IndexFragmentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(context)
                .inflate(R.layout.indexcardview_fragment, parent, false);
        IndexFragmentViewHolder ifv = new IndexFragmentViewHolder(view);
        return ifv;
    }

    @Override
    public void onBindViewHolder(IndexFragmentViewHolder holder, final int position) {

        Glide.with(context)
                .load(news.get(position).getImage())
                .fitCenter()
                .into(holder.imageView);
        holder.content_tv.setText(news.get(position).getSimplecontent());
        holder.title_tv.setText(news.get(position).getTitle());


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NewsContentActivity.class);
                intent.putExtra("NewsTitle", news.get(position).getTitle());
                intent.putExtra("Content", news.get(position).getContent());
                intent.putExtra("NewsTime", news.get(position).getCreatedAt());
                intent.putExtra("Author", news.get(position).getAuthor());
                intent.putExtra("Image", news.get(position).getImage());
                context.startActivity(intent);
            }
        });
//        holder.imageView1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                toast("1");
//            }
//        });
//        holder.imageView2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                toast("2");
//            }
//        });
//        holder.imageView3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                toast("3");
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    public void refresh(List<News> mList) {
        news = mList;
        notifyDataSetChanged();
    }

    //清除数据
    public void clear() {
        news.clear();
        notifyDataSetChanged();
    }

    private void toast(String toast) {
        Toast.makeText(context, toast, Toast.LENGTH_SHORT).show();
    }
}
