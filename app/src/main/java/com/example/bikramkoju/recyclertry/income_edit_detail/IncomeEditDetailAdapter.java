package com.example.bikramkoju.recyclertry.income_edit_detail;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bikramkoju.recyclertry.R;
import com.example.bikramkoju.recyclertry.income.IncomeDetail;

import java.util.ArrayList;

/**
 * Created by Bikramkoju on 6/2/2017.
 */

public class IncomeEditDetailAdapter extends RecyclerView.Adapter<IncomeEditDetailAdapter.MyHolder> {
    Context c;
    LayoutInflater layoutInflater;
    ArrayList<IncomeDetail> myDataedit;


    public IncomeEditDetailAdapter(Context context, ArrayList<IncomeDetail> mydata) {
        this.c = context;
        this.myDataedit = mydata;
         layoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.edit_income_detail, parent, false);
        return new MyHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.title.setText(String.valueOf(myDataedit.get(position).getName()));
        holder.price.setText(String.valueOf(myDataedit.get(position).getPrice()));
        holder.thumbnail.setImageResource(myDataedit.get(position).getThumbnail());

        Log.i( "TAG", String.valueOf(myDataedit.get(position).getThumbnail()));
    }

    @Override
    public int getItemCount() {
        return myDataedit.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        public TextView title, price;
        public ImageView thumbnail;

        public MyHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.edit_title);
            price = (TextView) itemView.findViewById(R.id.edit_price);
            thumbnail = (ImageView) itemView.findViewById(R.id.edit_thumbnail);
        }
    }
}
