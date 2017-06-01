package com.example.bikramkoju.recyclertry;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.astuetz.PagerSlidingTabStrip;
import com.example.bikramkoju.recyclertry.Edit.EditValue;

public class MainActivity extends AppCompatActivity {
    PagerSlidingTabStrip pagerSlidingTabStrip;
    ViewPager viewPager;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar= (Toolbar) findViewById(R.id.toolbar_id);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("BarberApp");

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.edit:
                Intent intent=new Intent(MainActivity.this, EditValue.class);
                startActivity(intent);
                break;
            case R.id.result:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
