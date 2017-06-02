package com.example.bikramkoju.recyclertry;


import android.support.v4.app.FragmentTransaction;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;


public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar= (Toolbar) findViewById(R.id.toolbar_id);
        setSupportActionBar(toolbar);


        ft=getSupportFragmentManager().beginTransaction();
        ft.add(R.id.mainFrame,new BarbarAppFragment());
        ft.commit();
        getSupportActionBar().setTitle("BarberApp");

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
