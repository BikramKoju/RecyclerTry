package com.example.bikramkoju.recyclertry.result;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bikramkoju.recyclertry.R;

import java.util.ArrayList;

/**
 * Created by Bikramkoju on 6/5/2017.
 */

class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.MyViewHolder> {
    Context ctx;
    ArrayList<Result> mydata;
    LayoutInflater layoutInflater;
    public ResultAdapter(FragmentActivity activity, ArrayList<Result> results) {
        ctx=activity;
        mydata=results;
        layoutInflater= (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public ResultAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=layoutInflater.inflate(R.layout.edit_expense_detail,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ResultAdapter.MyViewHolder holder, int position) {
        int fid=mydata.get(position).getFid();
        System.out.println(fid);
        holder.title.setText(String.valueOf(mydata.get(position).getTitle()));
        holder.price.setText(String.valueOf(mydata.get(position).getPrice()));
        holder.thumbnail.setImageResource(mydata.get(position).getImage());
        if (fid==1){
            holder.thumbnail.setColorFilter(Color.parseColor("#f43809"));
            holder.title.setTextColor(Color.parseColor("#f43809"));
            holder.price.setTextColor(Color.parseColor("#f43809"));
        }

    }

    @Override
    public int getItemCount() {
        return mydata.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title,price;
        public ImageView thumbnail;
        public MyViewHolder(View itemView) {
            super(itemView);
            title= (TextView) itemView.findViewById(R.id.edit_title_expense);
            price= (TextView) itemView.findViewById(R.id.edit_price_expense);
            thumbnail= (ImageView) itemView.findViewById(R.id.edit_thumbnail_expense);
        }
    }
}
