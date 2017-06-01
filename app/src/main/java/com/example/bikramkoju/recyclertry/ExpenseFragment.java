package com.example.bikramkoju.recyclertry;

import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bikramkoju.recyclertry.database.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bikramkoju on 5/18/2017.
 */

public class ExpenseFragment extends Fragment {
    RecyclerView recyclerView;
    private ExpenseAdapter expenseAdapter;
    private List<ExpenseDetail> expenseDetailList;

    TextView result;

    DatabaseHelper db;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.expenditure_fragment,container,false);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView=(RecyclerView)view.findViewById(R.id.expenseView);

        db=new DatabaseHelper(getActivity());
        db.insertData2("कपाल काटेको", 100);
        db.insertData2("कपाल कालो गरेको",200);
        db.insertData2("फेसवास गरेको",50);
        db.insertData2("कपाल रातो गरेको",120);
        db.insertData2("बच्चाको कपाल काटेको (१० बर्ष मुनिको",40);
        db.insertData2("फेसियल गरेको",200);
        db.insertData2("फचे ब्लीच गरेको",150);
        db.insertData2("दार्ही काटेको",30);
        db.insertData2("सेम्पु गरेको",60);
        db.insertData2("हेयर डराई गरेको",25);

        result=(TextView) view.findViewById(R.id.resultb);


        expenseDetailList=new ArrayList<>();
        expenseAdapter=new ExpenseAdapter(getActivity(),expenseDetailList);

        RecyclerView.LayoutManager mLayoutManager=new GridLayoutManager(getActivity(),3);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(5),true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(expenseAdapter);

        prepareIncome();
    }

    private void prepareIncome() {
        int[] incomes=new int[]{
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
        expenseDetailList.add(a);

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

}


