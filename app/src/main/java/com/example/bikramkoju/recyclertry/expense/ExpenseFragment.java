package com.example.bikramkoju.recyclertry.expense;

import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.example.bikramkoju.recyclertry.R;
import com.example.bikramkoju.recyclertry.database.DatabaseHelper;
import com.example.bikramkoju.recyclertry.expense_edit_detail.ExpenseEditFragment;
import com.example.bikramkoju.recyclertry.income.IncomeFragment;
import com.example.bikramkoju.recyclertry.result.NewResult;

import java.util.List;

/**
 * Created by Bikramkoju on 5/18/2017.
 */

public class ExpenseFragment extends Fragment {
    RecyclerView recyclerView;
    private ExpenseAdapter expenseAdapter;
    private List<ExpenseDetail> expenseDetailList;

    public static final int fid=1;

    TextView result;

    DatabaseHelper db;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.expenditure_fragment, container, false);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView) view.findViewById(R.id.expenseView);

        result = (TextView) view.findViewById(R.id.resultb);

        db = new DatabaseHelper(getActivity());
        expenseDetailList = db.getDataExpense();


        expenseAdapter = new ExpenseAdapter(getActivity(), expenseDetailList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 3);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(5), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(expenseAdapter);

        recyclerView.addOnItemTouchListener(new IncomeFragment.RecyclerTouchListener(getActivity(), recyclerView, new IncomeFragment.ClickListener() {
            @Override
            public void onClick(View view, int position) {

                ExpenseDetail expenseDetail = expenseDetailList.get(position);
                int price = expenseDetail.getPrice();
                result.setText(String.valueOf(price));

                String title=expenseDetail.getName();
                int image=expenseDetail.getThumbnail();
                db.insertResult(image,title,price,fid);
                Toast.makeText(getActivity(), "Expense Added " + "RS " +  expenseDetail.getPrice(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(getActivity(), "you long clicked " +expenseDetailList.get(position).getName(), Toast.LENGTH_SHORT).show();

            }
        }));



        prepareExpense();
    }


    private void prepareExpense() {
       /* int[] incomes=new int[]{
                R.drawable.album1,
                R.drawable.two,
                R.drawable.album3,
                R.drawable.album4,
                R.drawable.album5,
                R.drawable.album6,
                R.drawable.album7,
                R.drawable.album8,
                R.drawable.album9,
                R.drawable.album10,
                R.drawable.album11
        };

        ExpenseDetail a = new ExpenseDetail("कपाल काटेको", 100, incomes[0]);
        expenseDetailList.add(a);

        a = new ExpenseDetail("कपाल कालो गरेको", 200, incomes[1]);
        expenseDetailList.add(a);

        a = new ExpenseDetail("फेसवास गरेको", 50, incomes[2]);
        expenseDetailList.add(a);

        a = new ExpenseDetail("कपाल रातो गरेको", 120, incomes[3]);
        expenseDetailList.add(a);

        a = new ExpenseDetail("बच्चाको कपाल काटेको (१० बर्ष मुनिको )", 40, incomes[4]);
        expenseDetailList.add(a);

        a = new ExpenseDetail("फेसियल गरेको", 200, incomes[5]);
        expenseDetailList.add(a);

        a = new ExpenseDetail("फचे ब्लीच गरेको", 150, incomes[6]);
        expenseDetailList.add(a);

        a = new ExpenseDetail("दार्ही काटेको", 30, incomes[7]);
        expenseDetailList.add(a);

        a = new ExpenseDetail("सेम्पु गरेको", 60, incomes[8]);
        expenseDetailList.add(a);

        a = new ExpenseDetail("हेयर डराई गरेको", 25, incomes[9]);
        expenseDetailList.add(a);*/

        expenseAdapter.notifyDataSetChanged();
    }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.toolbar_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.edit:
               // Toast.makeText(getActivity(), "edit clicked of expense", Toast.LENGTH_SHORT).show();

                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.mainFrame, new ExpenseEditFragment()).addToBackStack(null).commit();
                ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("ExpenseEdit");


                break;
            case R.id.result:
                FragmentTransaction fragmentTransaction1=getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction1.replace(R.id.mainFrame, new NewResult()).addToBackStack(null).commit();
                ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("ResultsExp");

                break;
        }
        return super.onOptionsItemSelected(item);
    }
}


