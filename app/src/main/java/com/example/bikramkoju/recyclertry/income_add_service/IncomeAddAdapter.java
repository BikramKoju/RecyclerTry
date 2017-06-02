package com.example.bikramkoju.recyclertry.income_add_service;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bikramkoju.recyclertry.R;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bikramkoju on 6/2/2017.
 */

public class IncomeAddAdapter extends RecyclerView.Adapter<IncomeAddAdapter.MyViewHolder> {

    private Context mContext;
    private ArrayList<AddImage> addImages;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView thumbnail;

        public MyViewHolder(View itemView) {
            super(itemView);
            thumbnail = (ImageView) itemView.findViewById(R.id.addthumbnail);

        }
    }


    public IncomeAddAdapter(Context context, ArrayList<AddImage> addImagess) {
        mContext=context;
        addImages=addImagess;

    }

    @Override
    public IncomeAddAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_income_detail, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(IncomeAddAdapter.MyViewHolder holder, int position) {
       // AddImage addImage=addImages.get(position);

      //  Glide.with(mContext).load(addImage.getAddimage()).into(holder.thumbnail);
        holder.thumbnail.setImageResource(addImages.get(position).getAddimage());

    }

    @Override
    public int getItemCount() {
        return addImages.size();
    }
}
