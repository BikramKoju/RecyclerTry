package com.example.bikramkoju.recyclertry.expense_add_service;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.bikramkoju.recyclertry.R;
import com.example.bikramkoju.recyclertry.income_add_service.AddImage;

import java.util.ArrayList;

/**
 * Created by Bikramkoju on 6/4/2017.
 */

public class ExpenseAddAdapter extends RecyclerView.Adapter<ExpenseAddAdapter.MyViewHolder> {
    private Context mContext;
    private ArrayList<AddPic> addPics;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView thumbnail;
        public MyViewHolder(View itemView) {
            super(itemView);
            thumbnail= (ImageView) itemView.findViewById(R.id.addthumbnailexp);
        }
    }

    public ExpenseAddAdapter(Context mContext, ArrayList<AddPic> addPics) {
        this.mContext = mContext;
        this.addPics = addPics;
    }

    @Override
    public ExpenseAddAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_expense_detail, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ExpenseAddAdapter.MyViewHolder holder, final int position) {
       // holder.thumbnail.setImageResource(addImages.get(position).getAddimage());
        holder.thumbnail.setImageResource(addPics.get(position).getAddpic());
    }

    @Override
    public int getItemCount() {
        return addPics.size();
    }


}
