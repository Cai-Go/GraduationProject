package com.wzh.www.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.wzh.www.activity.MainActivity;
import com.wzh.www.activity.NewsContentActivity;
import com.wzh.www.activity.SocietyActivity;
import com.wzh.www.bean.News;
import com.wzh.www.bean.Society;
import com.wzh.www.graduationproject.R;
import com.wzh.www.hoder.IndexFragmentViewHolder;
import com.wzh.www.hoder.SocietyFragmentViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：Graduation Project
 * 作者： Created by WWW on 2016/4/12.
 * Date: 2016-04-12
 * Time: 09:20
 * 功能：社团
 */
public class SocietyRecyclerAdapter extends RecyclerView.Adapter<SocietyFragmentViewHolder> {
    private Context context;
    private List<Society> societyList;

    public SocietyRecyclerAdapter(List<Society> list, Context context) {
        this.context = context;
        this.societyList = list;
    }


    @Override
    public SocietyFragmentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(context)
                .inflate(R.layout.societycardview_fragment, parent, false);
        SocietyFragmentViewHolder sfv = new SocietyFragmentViewHolder(view);
        return sfv;
    }

    @Override
    public void onBindViewHolder(SocietyFragmentViewHolder holder, final int position) {


        Glide.with(context)
                .load(societyList.get(position).getLogo())
                .fitCenter()
                .into(holder.imageView);
        holder.textView.setText(societyList.get(position).getSocietyname());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SocietyActivity.class);
                intent.putExtra("SocietyName", societyList.get(position).getSocietyname());
                intent.putExtra("SchoolName", societyList.get(position).getSchoolname());
                intent.putExtra("SocietyInfo", societyList.get(position).getInfo());
                intent.putExtra("Logo", societyList.get(position).getLogo());
                intent.putExtra("Leader", societyList.get(position).getSocietyLeader());
                intent.putExtra("Member", societyList.get(position).getSocietyMember());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return societyList.size();
    }

    public void refresh(List<Society> mList) {
        societyList = mList;
        notifyDataSetChanged();
    }

    //清除数据
    public void clear() {
        societyList.clear();
        notifyDataSetChanged();
    }

    private void toast(String toast) {
        Toast.makeText(context, toast, Toast.LENGTH_SHORT).show();
    }
}
