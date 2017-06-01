package com.example.bikramkoju.recyclertry.Edit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.bikramkoju.recyclertry.IncomeDetail;
import com.example.bikramkoju.recyclertry.R;
import com.example.bikramkoju.recyclertry.database.DatabaseHelper;

/**
 * Created by Bikramkoju on 6/1/2017.
 */

public class EditValue extends AppCompatActivity {
    EditListAdapter editListAdapter;

    ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editvalue_database);

        listView= (ListView) findViewById(R.id.editlist);

        //listView.setAdapter(new EditListAdapter(this));
       editListAdapter=new EditListAdapter(this);
        listView.setAdapter(editListAdapter);

        editListAdapter.notifyDataSetChanged();


    }
}
