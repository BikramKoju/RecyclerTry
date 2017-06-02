package com.example.bikramkoju.recyclertry.expense;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.bikramkoju.recyclertry.R;

import java.util.List;

/**
 * Created by Bikramkoju on 5/18/2017.
 */

public class ExpenseAdapter  extends RecyclerView.Adapter<ExpenseAdapter.MyViewHolder> {
    private Context mContext;
    private List<ExpenseDetail> expenseDetailList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, count;
        public ImageView thumbnail;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            count = (TextView) itemView.findViewById(R.id.count);
            thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);

        }
    }

    public ExpenseAdapter(Context mContext, List<ExpenseDetail> expenseDetailList) {
        this.mContext = mContext;
        this.expenseDetailList = expenseDetailList;
    }

    @Override
    public ExpenseAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.expense_card, parent, false);
        return new ExpenseAdapter.MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(final ExpenseAdapter.MyViewHolder holder, final int position) {
        ExpenseDetail expenseDetail=expenseDetailList.get(position);
        holder.title.setText(expenseDetail.getName());
        holder.count.setText(expenseDetail.getPrice() + " rupees");

        //loading income cover using Glide library
        Glide.with(mContext).load(expenseDetail.getThumbnail()).into(holder.thumbnail);


        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
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
        });

    }
    @Override
    public int getItemCount() {
        return expenseDetailList.size();
    }
}


