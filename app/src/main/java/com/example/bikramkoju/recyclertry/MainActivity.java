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

        saveImageData();

    }

    private void saveImageData() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        if (!preferences.getBoolean("firstTimeImage", false)) {

            db = new DatabaseHelper(getApplicationContext());
            db.insertImage(R.drawable.album1);
            db.insertImage(R.drawable.album1);
            db.insertImage(R.drawable.album1);
            db.insertImage(R.drawable.album1);
            db.insertImage(R.drawable.album1);
            db.insertImage(R.drawable.album1);
            db.insertImage(R.drawable.album1);
            db.insertImage(R.drawable.album1);
            db.insertImage(R.drawable.album1);
            db.insertImage(R.drawable.album1);

            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("firstTimeImage", true);
            editor.commit();
        }

    }

    private void saveDataExpense() {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        if (!preferences.getBoolean("firstTimeExpense", false)) {

            db = new DatabaseHelper(getApplicationContext());
            db.insertData2(R.drawable.album1, "कपाल काटेको", 100);
            db.insertData2(R.drawable.album1, "कपाल कालो गरेको", 200);
            db.insertData2(R.drawable.album1, "फेसवास गरेको", 50);
            db.insertData2(R.drawable.album3, "कपाल रातो गरेको", 120);
            db.insertData2(R.drawable.album3, "बच्चाको कपाल काटेको (१० बर्ष मुनिको", 40);
            db.insertData2(R.drawable.album3, "फेसियल गरेको", 200);
            db.insertData2(R.drawable.album3, "फचे ब्लीच गरेको", 150);
            db.insertData2(R.drawable.album3, "दार्ही काटेको", 30);
            db.insertData2(R.drawable.album3, "सेम्पु गरेको", 60);
            db.insertData2(R.drawable.album3, "हेयर डराई गरेको", 25);

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
            db.insertData(R.drawable.album1, "कपाल कालो गरेको", 200);
            db.insertData(R.drawable.album1, "फेसवास गरेको", 50);
            db.insertData(R.drawable.album1, "कपाल रातो गरेको", 120);
            db.insertData(R.drawable.album1, "बच्चाको कपाल काटेको (१० बर्ष मुनिको", 40);
            db.insertData(R.drawable.album1, "फेसियल गरेको", 200);
            db.insertData(R.drawable.album1, "फचे ब्लीच गरेको", 150);
            db.insertData(R.drawable.album1, "दार्ही काटेको", 30);
            db.insertData(R.drawable.album1, "सेम्पु गरेको", 60);
            db.insertData(R.drawable.album1, "हेयर डराई गरेको", 25);

            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("firstTimeIncome", true);
            editor.commit();

        }
    }


   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }*/

   /* @Override
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
    }*/
//
//   @Override
//   public boolean onOptionsItemSelected(MenuItem item) {
//       switch (item.getItemId()){
//           case R.id.edit:
//               ft=getSupportFragmentManager().beginTransaction();
//               ft.replace(R.id.mainFrame,new IncomeEditFragment()).commit();
//               /* Intent intent=new Intent(getActivity(), EditValue.class);
//                startActivity(intent);*/
//               break;
//           case R.id.result:
//               break;
//       }
//
//       return super.onOptionsItemSelected(item);
//   }
}
