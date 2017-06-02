package com.example.bikramkoju.recyclertry.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.bikramkoju.recyclertry.income.IncomeDetail;

import java.util.ArrayList;

/**
 * Created by Bikramkoju on 6/1/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASENAME = "barberdatabase", TABLENAME = "income", TABLENAME2 = "expenditure";

    public DatabaseHelper(Context context) {
        super(context, DATABASENAME, null, 1);
    }

    ArrayList<IncomeDetail> myReadData= new ArrayList<>();


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists " + TABLENAME + "(id integer primary key autoincrement," +
                "image BLOP," +
                "title TEXT," +
                "price INTEGER)");

        db.execSQL("create table if not exists " + TABLENAME2 + "(id integer primary key autoincrement," +
                "image BLOP," +
                "title TEXT," +
                "price INTEGER)");

       // db.execSQL("insert into " +TABLENAME +" values(null,abc,12)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLENAME);
        db.execSQL("drop table if exists " + TABLENAME2);

        onCreate(db);

    }

    public void insertData(String title, int price){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv = new ContentValues();
       // cv.put("image",image);
        cv.put("title",title);
        cv.put("price",price);
        db.insert(TABLENAME,null,cv);
    }

    public void insertData2(String title, int price){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("title",title);
        cv.put("price",price);
        db.insert(TABLENAME2,null,cv);
    }

    public ArrayList<IncomeDetail> getData(){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("Select * from " + TABLENAME,null);
        if (cursor.getCount()!=0){
            if (cursor.moveToFirst()){
                do {
                    IncomeDetail incomeDetail=new IncomeDetail();
                    incomeDetail.setName(cursor.getString(cursor.getColumnIndex("title")));
                    incomeDetail.setPrice(cursor.getInt(cursor.getColumnIndex("price")));

                    myReadData.add(incomeDetail);

                }while (cursor.moveToNext());
            }
        }
          cursor.close();
        db.close();
        return myReadData;
    }
}
