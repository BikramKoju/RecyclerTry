package com.example.bikramkoju.recyclertry.result;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bikramkoju.recyclertry.R;
import com.example.bikramkoju.recyclertry.database.DatabaseHelper;

import java.util.ArrayList;

/**
 * Created by Bikramkoju on 6/5/2017.
 */

public class NewResult extends Fragment {
    ArrayList<Result> results=new ArrayList<>();
    DatabaseHelper db;
    RecyclerView recyclerView;
    TextView incomeResult,expenssResult;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.result_layout,container,false);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        incomeResult= (TextView) view.findViewById(R.id.totalIncome);
        expenssResult= (TextView) view.findViewById(R.id.totalExpences);
        recyclerView= (RecyclerView) view.findViewById(R.id.result_recycle);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        db=new DatabaseHelper(getActivity());
        results=db.getResult();
        recyclerView.setAdapter(new ResultAdapter(getActivity(),results));

        int income=db.getTotal("income");
        int expensee=db.getTotal("exp");

        System.out.println(income);
        System.out.println(expensee);

        incomeResult.setText(String.valueOf(income));
        expenssResult.setText(String.valueOf(expensee));
        expenssResult.setTextColor(Color.parseColor("#f43809"));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
    }
}
