package com.example.day1;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Wan2Adapter extends RecyclerView.Adapter<Wan2Adapter.ViewHolder> {
    private Context context;
    ArrayList<WanBean.DataBean.DatasBean> list;

    private boolean sh;
    private int i;
    private int num;
    private int i2;
    private int position;
    private int posi;
    private WanBean.DataBean.DatasBean datasBean;

    public Wan2Adapter(Context context, ArrayList<WanBean.DataBean.DatasBean> list) {
        this.context = context;
        this.list = list;
    }

    //显示或异常复选框
    public void showOrHint(boolean b) {
        sh = b;
    }

    //按全选键 调用的方法
    public void selectaAll() {
        for (int j = 0; j < list.size(); j++) {
            list.get(j).setChecks(true);
        }
    }

    //按删除键，调用的方法
    public void delete() {
        for (int j = 0; j < list.size(); j++) {
            boolean checks = list.get(j).isChecks();
            if (checks) {//checks  为true
                list.remove(list.get(j));
                j--;//删除一条数据，减一个索引，更新条目，从头循环
            }
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler2_main, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {


        datasBean = list.get(position);
        holder.mMainTvRecycler2.setText(datasBean.getTitle());
        Glide.with(context).load(datasBean.getEnvelopePic()).into(holder.mMainImgRecycler2);

        if (sh) {
            holder.mMainCheckboxRecycler2.setVisibility(View.VISIBLE);
        } else {
            holder.mMainCheckboxRecycler2.setVisibility(View.INVISIBLE);
        }

        //----------------------------------------（true）
        holder.mMainCheckboxRecycler2.setChecked(list.get(i).isChecks());


        holder.mMainCheckboxRecycler2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                list.get(i).setChecks(b);
            }
        });


      /*  if (num == 1) {
            holder.mMainCheckboxRecycler2.setVisibility(View.VISIBLE);
            holder.mMainCheckboxRecycler2.setChecked(false);
        } else if (num == 2) {
            holder.mMainCheckboxRecycler2.setVisibility(View.INVISIBLE);
        } else if (num == 3) {

            for (int j = 0; j < list.size(); j++) {

                datasBean.setChecks(true);
                // holder.mMainCheckboxRecycler2.setChecked(true);
            }
            if (list.get(position).isChecks()) {
                holder.mMainCheckboxRecycler2.setChecked(true);
            }else {
                holder.mMainCheckboxRecycler2.setChecked(false);
            }

        } else if (num == 4) {
//            holder.mMainCheckboxRecycler2.setChecked(true);
            datasBean.setChecks(true);
            Log.i("111", "onBindViewHolder: " + datasBean.isChecks());
//            posi=position;
      *//*      holder.mMainCheckboxRecycler2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    datasBean.setChecks(true);
                }
            });*//*
        }*/

 /*       else if (num == 3) {
            holder.mMainCheckboxRecycler2.setChecked(true);
        } else if (num == 4) {
            holder.mMainCheckboxRecycler2.setChecked(true);
        }*/
   /*     holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int posi = holder.getLayoutPosition();
                onClickItemLis.onClickItem(holder.itemView, posi);
                *//*if (mMainCheckboxRecycler2.isChecked()){
                    onClickItemLis.onClickItem(position);
                }*//*
            }
        });*/
    }



    public void setInt(int i) {
        this.i = i;
        for (int j = 0; j < list.size(); j++) {

        }
        if (i == 1) {
            num = 1;
        } else if (i == 2) {
            num = 2;
            // mMainCheckboxRecycler2.setVisibility(View.INVISIBLE);
        } else if (i == 3) {
            num = 3;
//            mMainCheckboxRecycler2.setChecked(true);
        } else if (i == 4) {
            num = 4;
//            mMainCheckboxRecycler2.setChecked(true);
        }

    }

    public void setIntItem(int i2, int position) {


        this.i2 = i2;
        this.position = position;
        if (i2 == 4) {
            num = 4;
        }

    }
/*    private OnClickItemLis onClickItemLis;

    public void setOnClickItemLis(OnClickItemLis onClickItemLis) {
        this.onClickItemLis = onClickItemLis;
    }

    interface OnClickItemLis {
        void onClickItem(View view, int position);
    }*/


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mMainImgRecycler2;
        private TextView mMainTvRecycler2;
        private CheckBox mMainCheckboxRecycler2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mMainImgRecycler2 = (ImageView) itemView.findViewById(R.id.recycler2_main_img);
            mMainTvRecycler2 = (TextView) itemView.findViewById(R.id.recycler2_main_tv);
            mMainCheckboxRecycler2 = (CheckBox) itemView.findViewById(R.id.recycler2_main_checkbox);
        }
    }
}
