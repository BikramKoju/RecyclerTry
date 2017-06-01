package com.example.bikramkoju.recyclertry.Edit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.bikramkoju.recyclertry.IncomeDetail;
import com.example.bikramkoju.recyclertry.R;
import com.example.bikramkoju.recyclertry.database.DatabaseHelper;

import java.util.ArrayList;

/**
 * Created by Bikramkoju on 6/1/2017.
 */

class EditListAdapter extends BaseAdapter {

    ArrayList<IncomeDetail> myData = new ArrayList<>();
    Context c;
    LayoutInflater inflator;
    DatabaseHelper db;


    public EditListAdapter(EditValue editValue) {
        c = editValue;
        inflator = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        db = new DatabaseHelper(c);
        myData = db.getData();

    }

    @Override
    public int getCount() {
        return myData.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NewViewHolder holder;
        if (convertView == null) {
            convertView = inflator.inflate(R.layout.editlist_values, null);
            holder = new NewViewHolder();
            holder.showTitle = (TextView) convertView.findViewById(R.id.etitle);
            holder.showPrice = (TextView) convertView.findViewById(R.id.eprice);

            convertView.setTag(holder);

        } else holder = (NewViewHolder) convertView.getTag();
        holder.showTitle.setText(myData.get(position).getName());
        holder.showPrice.setText(String.valueOf(myData.get(position).getPrice()));
        return convertView;
    }

    private class NewViewHolder {
        TextView showTitle, showPrice;

    }
}
