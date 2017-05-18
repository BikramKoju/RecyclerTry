package com.example.bikramkoju.recyclertry;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.astuetz.PagerSlidingTabStrip;

public class MainActivity extends AppCompatActivity {
    PagerSlidingTabStrip pagerSlidingTabStrip;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pagerSlidingTabStrip=(PagerSlidingTabStrip) findViewById(R.id.pager_tabs);
        viewPager=(ViewPager)findViewById(R.id.viewpager);

        FragmentManager fragmentManager = getSupportFragmentManager();
        viewPager.setAdapter(new MyAdapter(this, fragmentManager));
        pagerSlidingTabStrip.setViewPager(viewPager);
    }

    private class MyAdapter extends FragmentStatePagerAdapter {
        Context c;
        public MyAdapter(FragmentActivity mainActivity, FragmentManager fragmentManager) {
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
