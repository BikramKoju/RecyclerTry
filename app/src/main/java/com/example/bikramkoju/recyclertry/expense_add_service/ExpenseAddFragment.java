package com.example.bikramkoju.recyclertry.expense_add_service;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.bikramkoju.recyclertry.R;
import com.example.bikramkoju.recyclertry.database.DatabaseHelper;
import com.example.bikramkoju.recyclertry.expense_edit_detail.ExpenseEditFragment;
import com.example.bikramkoju.recyclertry.income.IncomeFragment;
import com.example.bikramkoju.recyclertry.income_add_service.AddImage;

import java.util.ArrayList;

/**
 * Created by Bikramkoju on 6/5/2017.
 */

public class ExpenseAddFragment extends Fragment {

    ImageView imageView;
    EditText edname, edprice;
    DatabaseHelper db;

    String name, nameValue;
    int price, imgRes, priceValue, imageValue,idValue;

    RecyclerView recyclerView;
    ExpenseAddAdapter expenseAddAdapter;
    ArrayList<AddPic> addImageArrayList = new ArrayList<>();

    int[] images = new int[]{
            R.drawable.album1,
            R.drawable.album3,
            R.drawable.album4,
            R.drawable.album5,
            R.drawable.album6,
            R.drawable.album7,
            R.drawable.album8,
            R.drawable.album9,
            R.drawable.album1,
            R.drawable.album10,
            R.drawable.album10,
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_expense_service, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        try {
            nameValue= (String) getArguments().get("name");
            priceValue= (int) getArguments().get("price");
            imageValue= (int) getArguments().get("imgs");
            idValue= (int) getArguments().get("id");

            edname.setText(nameValue);
            edprice.setText(priceValue);
            imageView.setImageResource(imageValue);
        } catch (Exception e) {
            e.printStackTrace();
        }

        edname = (EditText) view.findViewById(R.id.add_namexp);
        edprice = (EditText) view.findViewById(R.id.add_pricexp);
        imageView = (ImageView) view.findViewById(R.id.add_thumbnailexp);

        recyclerView = (RecyclerView) view.findViewById(R.id.addlistexp);


        expenseAddAdapter = new ExpenseAddAdapter(getActivity(), addImageArrayList);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        recyclerView.setAdapter(expenseAddAdapter);

        recyclerView.addOnItemTouchListener(new IncomeFragment.RecyclerTouchListener(getActivity(), recyclerView, new IncomeFragment.ClickListener() {
            @Override
            public void onClick(View view, int position) {

                imageView.setTag(images[position]);
                imageView.setImageResource(images[position]);
//                System.out.println(imageView.getTag());

                imgRes = (int) imageView.getTag();
                Toast.makeText(getActivity(), "addedonClick" + position, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(getActivity(), "onLongCleck", Toast.LENGTH_SHORT).show();

            }
        }));

        /*AddPic b=new AddPic();
        for (int i=0;i<images.length;i++){
            b.setAddpic(images[i]);
            addImageArrayList.add(b);
        }*/

        AddPic b = new AddPic(images[0]);
        addImageArrayList.add(b);

        b = new AddPic(images[1]);
        addImageArrayList.add(b);
        b = new AddPic(images[2]);
        addImageArrayList.add(b);
        b = new AddPic(images[3]);
        addImageArrayList.add(b);
        b = new AddPic(images[4]);
        addImageArrayList.add(b);
        b = new AddPic(images[5]);
        addImageArrayList.add(b);
        b = new AddPic(images[6]);
        addImageArrayList.add(b);
        b = new AddPic(images[7]);
        addImageArrayList.add(b);
        b = new AddPic(images[8]);
        addImageArrayList.add(b);
        b = new AddPic(images[9]);
        addImageArrayList.add(b);
        b = new AddPic(images[10]);
        addImageArrayList.add(b);


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.toolbar_save2, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.savexp:

                try {
                    name = edname.getText().toString();
                    price = Integer.parseInt(edprice.getText().toString());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }

                db = new DatabaseHelper(getActivity());
                db.insertData2(imgRes, name, price);

                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.mainFrame, new ExpenseEditFragment()).addToBackStack(null).commit();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
