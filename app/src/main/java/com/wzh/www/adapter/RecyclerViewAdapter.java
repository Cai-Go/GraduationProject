package com.wzh.www.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.wzh.www.bean.Content;
import com.wzh.www.hoder.ContentHolder;
import com.wzh.www.graduationproject.R;

import java.util.List;

/**
 * 项目名称：Graduation Project
 * 作者： Created by WWW on 2016/4/7.
 * Date: 2016-04-07
 * Time: 10:45
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<ContentHolder> {
    private List<Content> contents;
    private Context context;


    public RecyclerViewAdapter(List<Content> contents, Context context) {
        this.contents = contents;
        this.context = context;
    }


    @Override
    public ContentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.about_cardview, parent, false);
        ContentHolder ch = new ContentHolder(view);
        return ch;
    }

    @Override
    public void onBindViewHolder(ContentHolder holder, final int position) {
        holder.titleTextView.setText(contents.get(position).getTitle());
        holder.contentTextView.setText(contents.get(position).getContent());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "点我干嘛！！！！", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return contents.size();
    }
}
