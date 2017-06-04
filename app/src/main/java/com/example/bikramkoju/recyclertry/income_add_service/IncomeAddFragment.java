package com.example.bikramkoju.recyclertry.income_add_service;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bikramkoju.recyclertry.R;
import com.example.bikramkoju.recyclertry.database.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bikramkoju on 6/2/2017.
 */

public class IncomeAddFragment extends Fragment {

    RecyclerView recyclerView;
    IncomeAddAdapter incomeAddAdapter;
    ArrayList<AddImage> addImageslist;
    DatabaseHelper db;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.add_income_service,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView= (RecyclerView) view.findViewById(R.id.addlist);

        db=new DatabaseHelper(getActivity());
        addImageslist=db.getImage();

      incomeAddAdapter=new IncomeAddAdapter(getActivity(),addImageslist);

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));
        recyclerView.setAdapter(new IncomeAddAdapter(getActivity(),addImageslist));


    }


}
