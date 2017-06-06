package com.example.bikramkoju.recyclertry.income_add_service;

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
import com.example.bikramkoju.recyclertry.income.IncomeDetail;
import com.example.bikramkoju.recyclertry.income.IncomeFragment;
import com.example.bikramkoju.recyclertry.income_edit_detail.IncomeEditFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bikramkoju on 6/2/2017.
 */

public class IncomeAddFragment extends Fragment {

    ImageView imageView;
    EditText edname, edprice;
    DatabaseHelper db;

    String name, nameValue;
    int price, imgRes, priceValue, imageValue,idValue;

    RecyclerView recyclerView;
    IncomeAddAdapter incomeAddAdapter;
    ArrayList<AddImage> addImageslist = new ArrayList<>();

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
        View view = inflater.inflate(R.layout.add_income_service, container, false);
        return view;
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
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


        edname = (EditText) view.findViewById(R.id.add_name);
        edprice = (EditText) view.findViewById(R.id.add_price);
        imageView = (ImageView) view.findViewById(R.id.add_thumbnail);



        recyclerView = (RecyclerView) view.findViewById(R.id.addlist);


        incomeAddAdapter = new IncomeAddAdapter(getActivity(), addImageslist);

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));

        recyclerView.setAdapter(incomeAddAdapter);

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

      /*  AddImage a = new AddImage();
        for (int i = 0; i < images.length; i++) {
            a.setAddimage(images[i]);
            addImageslist.add(a);
        }*/

        AddImage a = new AddImage(images[0]);
        addImageslist.add(a);

        a = new AddImage(images[1]);
        addImageslist.add(a);
        a = new AddImage(images[2]);
        addImageslist.add(a);
        a = new AddImage(images[3]);
        addImageslist.add(a);
        a = new AddImage(images[4]);
        addImageslist.add(a);
        a = new AddImage(images[5]);
        addImageslist.add(a);
        a = new AddImage(images[6]);
        addImageslist.add(a);
        a = new AddImage(images[7]);
        addImageslist.add(a);
        a = new AddImage(images[8]);
        addImageslist.add(a);
        a = new AddImage(images[9]);
        addImageslist.add(a);
        a = new AddImage(images[10]);
        addImageslist.add(a);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.toolbar_save, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save:

                try {
                    price = Integer.parseInt(edprice.getText().toString());
                    name = edname.getText().toString();
                } catch (NumberFormatException e) {
                    Toast.makeText(getActivity(), "please fill in the info", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();

                }

                //System.out.println(edname.getText().toString());
                //System.out.println(edprice.getText().toString());
                //System.out.println(imageView.getTag());
                //  int image= imageView.getImageAlpha();

                db = new DatabaseHelper(getActivity());
                db.insertData(imgRes, name, price);

                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.mainFrame, new IncomeEditFragment()).addToBackStack(null).commit();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
