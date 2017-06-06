package com.example.bikramkoju.recyclertry.expense_edit_detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.bikramkoju.recyclertry.R;
import com.example.bikramkoju.recyclertry.database.DatabaseHelper;
import com.example.bikramkoju.recyclertry.expense.ExpenseDetail;
import com.example.bikramkoju.recyclertry.expense_add_service.ExpenseAddFragment;
import com.example.bikramkoju.recyclertry.income.IncomeFragment;
import com.example.bikramkoju.recyclertry.income_add_service.IncomeAddFragment;

import java.util.ArrayList;

/**
 * Created by Bikramkoju on 6/4/2017.
 */

public class ExpenseEditFragment extends Fragment {
    ArrayList<ExpenseDetail> mydata=new ArrayList<>();
    DatabaseHelper db;
    RecyclerView recyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.edit_expense_fragment,container,false);
        return  view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView= (RecyclerView) view.findViewById(R.id.editlistexpense);

       /* recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        db=new DatabaseHelper(getActivity());
        mydata=db.getDataExpense();

        recyclerView.setAdapter(new ExpenseEditDetailAdapter(getContext(),mydata));*/

        recyclerView.addOnItemTouchListener(new IncomeFragment.RecyclerTouchListener(getActivity(), recyclerView, new IncomeFragment.ClickListener() {
            @Override
            public void onClick(View view, int position) {


                Toast.makeText(getActivity(), "addedonClick" + position, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onLongClick(View view, int position) {
                db.removeDataExpense(mydata.get(position).getId());
                System.out.println(mydata.get(position).getId());
                onResume();

                //Toast.makeText(getActivity(), "onLongCleck", Toast.LENGTH_SHORT).show();

            }
        }));

    }

    @Override
    public void onResume() {
        super.onResume();

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        db=new DatabaseHelper(getActivity());
        mydata=db.getDataExpense();

        recyclerView.setAdapter(new ExpenseEditDetailAdapter(getContext(),mydata));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
       menu.clear();
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.toolbar_menu_add,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       switch (item.getItemId()){
           case R.id.add:

               FragmentTransaction fragmentTransaction=getActivity().getSupportFragmentManager().beginTransaction();
               fragmentTransaction.replace(R.id.mainFrame, new ExpenseAddFragment()).addToBackStack(null).commit();
               ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("ADD Expenses");

               break;
       }
        return super.onOptionsItemSelected(item);
    }
}
