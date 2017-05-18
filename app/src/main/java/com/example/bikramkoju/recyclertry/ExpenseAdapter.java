package com.example.bikramkoju.recyclertry;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Bikramkoju on 5/18/2017.
 */

public class ExpenseAdapter  extends RecyclerView.Adapter<ExpenseAdapter.MyViewHolder> {
    private Context mContext;
    private List<ExpenseDetail> expenseDetailList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, count;
        public ImageView thumbnail, overflow;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            count = (TextView) itemView.findViewById(R.id.count);
            thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);
            overflow = (ImageView) itemView.findViewById(R.id.overflow);
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
    public void onBindViewHolder(final ExpenseAdapter.MyViewHolder holder, int position) {
        ExpenseDetail expenseDetail=expenseDetailList.get(position);
        holder.title.setText(expenseDetail.getName());
        holder.count.setText(expenseDetail.getPrice() + " rupees");

        //loading income cover using Glide library
        Glide.with(mContext).load(expenseDetail.getThumbnail()).into(holder.thumbnail);

       /* holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupMenu(holder.overflow);
            }
        });*/

    }

   /* private void showPopupMenu(ImageView overflow) {
        PopupMenu popup=new PopupMenu(mContext,overflow);
        MenuInflater inflater=popup.getMenuInflater();
        inflater.inflate(R.menu.menu_Album,popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
        popup.show();
    }

    *//**
     * Click listener for popup menu items
     *//*
    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        public MyMenuItemClickListener() {
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_add_favourite:
                    Toast.makeText(mContext, "Add to favourite", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.action_play_next:
                    Toast.makeText(mContext, "Play next", Toast.LENGTH_SHORT).show();
                    return true;
                default:
            }
            return false;
        }
    }*/


    @Override
    public int getItemCount() {
        return expenseDetailList.size();
    }
}


