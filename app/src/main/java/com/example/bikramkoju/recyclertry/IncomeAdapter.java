package com.example.bikramkoju.recyclertry;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Bikramkoju on 5/18/2017.
 */

class IncomeAdapter extends RecyclerView.Adapter<IncomeAdapter.MyViewHolder> {
    private Context mContext;
    private List<IncomeDetail> incomeDetailList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, count;
        public ImageView thumbnail;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            count = (TextView) itemView.findViewById(R.id.counti);
            thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);

        }
    }

    public IncomeAdapter(Context mContext, List<IncomeDetail> incomeDetailList) {
        this.mContext = mContext;
        this.incomeDetailList = incomeDetailList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.income_card, parent, false);
        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        IncomeDetail incomeDetail = incomeDetailList.get(position);
        holder.title.setText(incomeDetail.getName());
        holder.count.setText("RS " + incomeDetail.getPrice());

        //loading income cover using Glide library
        Glide.with(mContext).load(incomeDetail.getThumbnail()).into(holder.thumbnail);

        /*holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(mContext, "Item clicked at "+ position, Toast.LENGTH_SHORT).show();
            }
        });

        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Toast.makeText(mContext, "title is clicked", Toast.LENGTH_LONG).show();
            }
        });*/


    }


    @Override
    public int getItemCount() {
        return incomeDetailList.size();
    }
}
