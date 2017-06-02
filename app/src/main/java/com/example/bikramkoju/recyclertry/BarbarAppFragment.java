package com.example.bikramkoju.recyclertry;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;
import com.example.bikramkoju.recyclertry.expense.ExpenseFragment;
import com.example.bikramkoju.recyclertry.income.IncomeFragment;

/**
 * Created by Bikramkoju on 6/2/2017.
 */

public class BarbarAppFragment extends Fragment {
    PagerSlidingTabStrip pagerSlidingTabStrip;
    ViewPager viewPager;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.barber_layout,container,false);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pagerSlidingTabStrip=(PagerSlidingTabStrip) view.findViewById(R.id.pager_tabs);
        viewPager=(ViewPager)view.findViewById(R.id.viewpager);

        FragmentManager fragmentManager=getChildFragmentManager();
        viewPager.setAdapter(new MyAdapter(getActivity(),fragmentManager));
        pagerSlidingTabStrip.setViewPager(viewPager);
    }

    private class MyAdapter extends FragmentStatePagerAdapter {
        Context c;
        public MyAdapter(Context mainActivity, FragmentManager fragmentManager) {
            super(fragmentManager);
            c=mainActivity;
        }


        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return new IncomeFragment();
                case 1:
                    return new ExpenseFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0:
                    return "Income";
                case 1:
                    return "Expenses";
            }
            return null;
        }
    }

}
