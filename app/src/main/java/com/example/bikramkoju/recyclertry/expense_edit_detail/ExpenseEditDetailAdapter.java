package com.example.bikramkoju.recyclertry.expense_edit_detail;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bikramkoju.recyclertry.R;
import com.example.bikramkoju.recyclertry.Update.ExpenseUpdateFragment;
import com.example.bikramkoju.recyclertry.expense.ExpenseDetail;
import com.example.bikramkoju.recyclertry.expense_add_service.ExpenseAddFragment;

import java.util.ArrayList;

/**
 * Created by Bikramkoju on 6/4/2017.
 */

public class ExpenseEditDetailAdapter extends RecyclerView.Adapter<ExpenseEditDetailAdapter.MyHolder> {
    Context c;
    LayoutInflater layoutInflater;
    ArrayList<ExpenseDetail> myDataedit;

    public ExpenseEditDetailAdapter(Context context, ArrayList<ExpenseDetail> mydata) {
        this.c=context;
        this.myDataedit=mydata;
        layoutInflater=(LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.edit_expense_detail,parent,false);

        return new MyHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.title.setText(String.valueOf(myDataedit.get(position).getName()));
        holder.price.setText(String.valueOf(myDataedit.get(position).getPrice()));
        holder.thumbnail.setImageResource(myDataedit.get(position).getThumbnail());

    }

    @Override
    public int getItemCount() {
        return myDataedit.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        public TextView title,price;
        public ImageView thumbnail;
        public MyHolder(View itemView) {
            super(itemView);
            title= (TextView) itemView.findViewById(R.id.edit_title_expense);
            price= (TextView) itemView.findViewById(R.id.edit_price_expense);
            thumbnail= (ImageView) itemView.findViewById(R.id.edit_thumbnail_expense);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int position=getAdapterPosition();
                    ExpenseUpdateFragment expenseUpdateFragment=new ExpenseUpdateFragment();
                    Bundle args=new Bundle();
                    args.putInt("id",myDataedit.get(position).getId());
                    args.putString("name",myDataedit.get(position).getName());
                    args.putInt("price",myDataedit.get(position).getPrice());
                    args.putInt("imgs",myDataedit.get(position).getThumbnail());
                    expenseUpdateFragment.setArguments(args);

                    FragmentTransaction ft=((FragmentActivity)v.getContext()).getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.mainFrame,expenseUpdateFragment).addToBackStack(null).commit();

                    ((AppCompatActivity) v.getContext()).getSupportActionBar().setTitle("UpdateExpense");

                }
            });
        }
    }
}
