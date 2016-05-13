package com.wzh.www.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.wzh.www.activity.SearchActivity;
import com.wzh.www.activity.SocietyActivity;
import com.wzh.www.bean.News;
import com.wzh.www.bean.Society;
import com.wzh.www.graduationproject.R;
import com.wzh.www.hoder.ResultViewHolder;

import java.util.List;

/**
 * 项目名称：Graduation Project
 * 作者： Created by WWW on 2016/4/25.
 * Date: 2016-04-25
 * Time: 17:17
 * 功能：
 */
public class ResultAdapter extends RecyclerView.Adapter<ResultViewHolder> {
    private Context context;
    private List<Society> mList;

    public ResultAdapter(List<Society> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }

    @Override
    public ResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(context)
                .inflate(R.layout.searchresult_item, parent, false);
        ResultViewHolder rv = new ResultViewHolder(view);
        return rv;
    }

    @Override
    public void onBindViewHolder(ResultViewHolder holder, final int position) {
        Glide.with(context)
                .load(mList.get(position).getLogo())
                .fitCenter()
                .into(holder.imageView);
        holder.name_tv.setText(mList.get(position).getSocietyname());
        holder.school_tv.setText(mList.get(position).getSchoolname());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SocietyActivity.class);
                intent.putExtra("SocietyName", mList.get(position).getSocietyname());
//                intent.putExtra("QRCode", mList.get(position).getQrCode());
                intent.putExtra("SchoolName", mList.get(position).getSchoolname());
                intent.putExtra("SocietyInfo", mList.get(position).getInfo());
                intent.putExtra("Logo", mList.get(position).getLogo());
                intent.putExtra("Leader", mList.get(position).getSocietyLeader());
                intent.putExtra("Member", mList.get(position).getSocietyMember());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void refresh(List<Society> list) {
        mList = list;
        notifyDataSetChanged();
    }

    //清除数据
    public void clear() {
        mList.clear();
        notifyDataSetChanged();
    }
}
