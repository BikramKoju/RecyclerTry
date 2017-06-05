package com.example.bikramkoju.recyclertry;


import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.bikramkoju.recyclertry.database.DatabaseHelper;


public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    FragmentTransaction ft;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar_id);
        setSupportActionBar(toolbar);

        ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.mainFrame, new BarbarAppFragment());
        ft.commit();
        getSupportActionBar().setTitle("BarberApp");

        saveDataLocally();

        saveDataExpense();

    }

    private void saveDataExpense() {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        if (!preferences.getBoolean("firstTimeExpense", false)) {

            db = new DatabaseHelper(getApplicationContext());
            db.insertData2(R.drawable.album1, "कपाल काटेको", 100);
            db.insertData2(R.drawable.album3, "कपाल कालो गरेको", 200);
            db.insertData2(R.drawable.album4, "फेसवास गरेको", 50);
            db.insertData2(R.drawable.album5, "कपाल रातो गरेको", 120);
            db.insertData2(R.drawable.album6, "बच्चाको कपाल काटेको (१० बर्ष मुनिको", 40);
            db.insertData2(R.drawable.album7, "फेसियल गरेको", 200);
            db.insertData2(R.drawable.album8, "फचे ब्लीच गरेको", 150);
            db.insertData2(R.drawable.album9, "दार्ही काटेको", 30);
            db.insertData2(R.drawable.album1, "सेम्पु गरेको", 60);
            db.insertData2(R.drawable.album10, "हेयर डराई गरेको", 25);

            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("firstTimeExpense", true);
            editor.commit();
        }
    }

    private void saveDataLocally() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        if (!preferences.getBoolean("firstTimeIncome", false)) {

            db = new DatabaseHelper(getApplicationContext());
            db.insertData(R.drawable.album1, "कपाल काटेको", 100);
            db.insertData(R.drawable.album3, "कपाल कालो गरेको", 200);
            db.insertData(R.drawable.album4, "फेसवास गरेको", 50);
            db.insertData(R.drawable.album5, "कपाल रातो गरेको", 120);
            db.insertData(R.drawable.album6, "बच्चाको कपाल काटेको (१० बर्ष मुनिको", 40);
            db.insertData(R.drawable.album7, "फेसियल गरेको", 200);
            db.insertData(R.drawable.album8, "फचे ब्लीच गरेको", 150);
            db.insertData(R.drawable.album9, "दार्ही काटेको", 30);
            db.insertData(R.drawable.album1, "सेम्पु गरेको", 60);
            db.insertData(R.drawable.album10, "हेयर डराई गरेको", 25);

            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("firstTimeIncome", true);
            editor.commit();

        }
    }

}
