package com.example.day1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class WanAdapter extends RecyclerView.Adapter<WanAdapter.ViewHolder> {
    private Context context;
    ArrayList<WanBean.DataBean.DatasBean> list;


    public WanAdapter(Context context, ArrayList<WanBean.DataBean.DatasBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_main, null);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WanBean.DataBean.DatasBean datasBean = list.get(position);
        holder.mMainTvRecycler.setText(datasBean.getTitle());
        Glide.with(context).load(datasBean.getEnvelopePic()).into(holder.mMainImgRecycler);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mMainImgRecycler;
        private TextView mMainTvRecycler;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mMainImgRecycler = (ImageView) itemView.findViewById(R.id.recycler_main_img);
            mMainTvRecycler = (TextView) itemView.findViewById(R.id.recycler_main_tv);
        }
    }
}
