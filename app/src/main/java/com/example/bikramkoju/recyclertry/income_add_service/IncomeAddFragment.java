package com.example.bikramkoju.recyclertry.income_add_service;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.bikramkoju.recyclertry.R;
import com.example.bikramkoju.recyclertry.income.IncomeDetail;
import com.example.bikramkoju.recyclertry.income.IncomeFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bikramkoju on 6/2/2017.
 */

public class IncomeAddFragment extends Fragment {

    ImageView imageView;

    RecyclerView recyclerView;
    IncomeAddAdapter incomeAddAdapter;
    ArrayList<AddImage> addImageslist= new ArrayList<>();

    int[] images = new int[]{
            R.drawable.album1,
            R.drawable.album1,
            R.drawable.album1,
            R.drawable.album1,
            R.drawable.album1,
            R.drawable.album1,
            R.drawable.album1,
            R.drawable.album1,
            R.drawable.album1,
            R.drawable.album1,
            R.drawable.album1,
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_income_service, container, false);
        return view;
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imageView= (ImageView) view.findViewById(R.id.add_thumbnail);

        recyclerView = (RecyclerView) view.findViewById(R.id.addlist);

        AddImage a = new AddImage();
        for (int i = 0; i < images.length; i++) {
            a.setAddimage(images[i]);
            addImageslist.add(a);
        }

        incomeAddAdapter=new IncomeAddAdapter(getActivity(),addImageslist);

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));

        recyclerView.setAdapter(incomeAddAdapter);

        recyclerView.addOnItemTouchListener(new IncomeFragment.RecyclerTouchListener(getActivity(), recyclerView, new IncomeFragment.ClickListener() {
            @Override
            public void onClick(View view, int position) {

                imageView.setImageResource(images[position]);



                Toast.makeText(getActivity(), "addedonClick" + position, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(getActivity(), "onLongCleck", Toast.LENGTH_SHORT).show();

            }
        }));

    }


}
