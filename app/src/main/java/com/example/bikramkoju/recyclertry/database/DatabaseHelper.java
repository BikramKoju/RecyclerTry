package com.example.bikramkoju.recyclertry.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.bikramkoju.recyclertry.expense.ExpenseDetail;
import com.example.bikramkoju.recyclertry.income.IncomeDetail;
import com.example.bikramkoju.recyclertry.income_add_service.AddImage;

import java.util.ArrayList;

/**
 * Created by Bikramkoju on 6/1/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASENAME = "barberdatabase", TABLENAME = "income", TABLENAME2 = "expenditure", IMAGETABLE = "image";

    public DatabaseHelper(Context context) {
        super(context, DATABASENAME, null, 1);
    }

    ArrayList<IncomeDetail> myReadData = new ArrayList<>();

    ArrayList<ExpenseDetail> myReadDataExpense = new ArrayList<>();

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists " + TABLENAME + "(id integer primary key autoincrement," +
                "image INTEGER," +
                "title TEXT," +
                "price INTEGER)");

        db.execSQL("create table if not exists " + TABLENAME2 + "(id integer primary key autoincrement," +
                "image INTEGER," +
                "title TEXT," +
                "price INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLENAME);
        db.execSQL("drop table if exists " + TABLENAME2);
               onCreate(db);

    }

    public void insertData(int image, String title, int price) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("image", image);
        cv.put("title", title);
        cv.put("price", price);
        db.insert(TABLENAME, null, cv);
    }

    public void insertData2(int image, String title, int price) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("image", image);
        cv.put("title", title);
        cv.put("price", price);
        db.insert(TABLENAME2, null, cv);
    }


    public ArrayList<IncomeDetail> getData() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from " + TABLENAME, null);
        if (cursor.getCount() != 0) {
            if (cursor.moveToFirst()) {
                do {
                    IncomeDetail incomeDetail = new IncomeDetail();
                    incomeDetail.setName(cursor.getString(cursor.getColumnIndex("title")));
                    incomeDetail.setPrice(cursor.getInt(cursor.getColumnIndex("price")));
                    incomeDetail.setThumbnail(cursor.getInt(cursor.getColumnIndex("image")));

                    myReadData.add(incomeDetail);

                } while (cursor.moveToNext());
            }
        }
        cursor.close();
        db.close();
        return myReadData;
    }


    public ArrayList<ExpenseDetail> getDataExpense() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from " + TABLENAME2, null);
        if (cursor.getCount() != 0) {
            if (cursor.moveToFirst()) {
                do {
                    ExpenseDetail expenseDetail = new ExpenseDetail();
                    expenseDetail.setName(cursor.getString(cursor.getColumnIndex("title")));
                    expenseDetail.setPrice(cursor.getInt(cursor.getColumnIndex("price")));
                    expenseDetail.setThumbnail(cursor.getInt(cursor.getColumnIndex("image")));

                    myReadDataExpense.add(expenseDetail);

                } while (cursor.moveToNext());
            }
        }
        cursor.close();
        db.close();
        return myReadDataExpense;
    }


}
