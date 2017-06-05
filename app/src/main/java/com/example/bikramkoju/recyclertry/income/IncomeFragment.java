package com.example.bikramkoju.recyclertry.income;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.example.bikramkoju.recyclertry.R;
import com.example.bikramkoju.recyclertry.database.DatabaseHelper;
import com.example.bikramkoju.recyclertry.income_edit_detail.IncomeEditFragment;
import com.example.bikramkoju.recyclertry.result.NewResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bikramkoju on 5/18/2017.
 */

public class IncomeFragment extends Fragment {

    DatabaseHelper db;
    RecyclerView recyclerView;
    private IncomeAdapter incomeAdapter;
    private List<IncomeDetail> incomeList;
    public static final int fid=2;

    private TextView result;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.income_fragment, container, false);

        return v;
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        recyclerView = (RecyclerView) view.findViewById(R.id.incomeView);

        result = (TextView) view.findViewById(R.id.resulta);

        db = new DatabaseHelper(getActivity());
        incomeList = db.getData();

        incomeAdapter = new IncomeAdapter(getActivity(), incomeList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 3);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(5), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(incomeAdapter);


        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {

                IncomeDetail incomeDetail = incomeList.get(position);
                int price = incomeDetail.getPrice();
                result.setText(String.valueOf(price));

              String title=incomeDetail.getName();
                int image=incomeDetail.getThumbnail();

                db.insertResult(image,title,price,fid);

                Toast.makeText(getActivity(), "onClick" + position, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(getActivity(), "You long clicked "+incomeList.get(position).getName(), Toast.LENGTH_SHORT).show();

            }
        }));

        prepareIncome();
    }

    public static interface ClickListener {
        public void onClick(View view, int position);

        public void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildAdapterPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }

    private void prepareIncome() {

        /*int[] incomes = new int[]{
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

        IncomeDetail a = new IncomeDetail("कपाल काटेको", 100, incomes[0]);
        incomeList.add(a);

        a = new IncomeDetail("कपाल कालो गरेको", 200, incomes[1]);
        incomeList.add(a);

        a = new IncomeDetail("फेसवास गरेको", 50, incomes[2]);
        incomeList.add(a);

        a = new IncomeDetail("कपाल रातो गरेको", 120, incomes[3]);
        incomeList.add(a);

        a = new IncomeDetail("बच्चाको कपाल काटेको (१० बर्ष मुनिको )", 40, incomes[4]);
        incomeList.add(a);

        a = new IncomeDetail("फेसियल गरेको", 200, incomes[5]);
        incomeList.add(a);

        a = new IncomeDetail("फचे ब्लीच गरेको", 150, incomes[6]);
        incomeList.add(a);

        a = new IncomeDetail("दार्ही काटेको", 30, incomes[7]);
        incomeList.add(a);

        a = new IncomeDetail("सेम्पु गरेको", 60, incomes[8]);
        incomeList.add(a);

        a = new IncomeDetail("हेयर डराई गरेको", 25, incomes[9]);
        incomeList.add(a);*/

        incomeAdapter.notifyDataSetChanged();
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
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.mainFrame, new IncomeEditFragment()).addToBackStack(null).commit();
                ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("IncomeEdit");

                break;
            case R.id.result:
                FragmentTransaction fragmentTransaction1=getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction1.replace(R.id.mainFrame, new NewResult()).addToBackStack(null).commit();
                ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Results");
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
