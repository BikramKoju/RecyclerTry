package com.example.bikramkoju.recyclertry.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.bikramkoju.recyclertry.expense.ExpenseDetail;
import com.example.bikramkoju.recyclertry.income.IncomeDetail;
import com.example.bikramkoju.recyclertry.income_add_service.AddImage;
import com.example.bikramkoju.recyclertry.result.Result;

import java.util.ArrayList;

/**
 * Created by Bikramkoju on 6/1/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASENAME = "barberdatabase", TABLENAME = "income", TABLENAME2 = "expenditure", RESULT = "result";

    public DatabaseHelper(Context context) {
        super(context, DATABASENAME, null, 1);
    }

    ArrayList<IncomeDetail> myReadData = new ArrayList<>();

    ArrayList<ExpenseDetail> myReadDataExpense = new ArrayList<>();
    ArrayList<Result> myReadResult = new ArrayList<>();

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

        db.execSQL("create table if not exists " + RESULT + "(id integer primary key autoincrement," +
                "image INTEGER," +
                "title TEXT," +
                "price INTEGER," +
                "fid INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLENAME);
        db.execSQL("drop table if exists " + TABLENAME2);
        db.execSQL("drop table if exists " + RESULT);

        onCreate(db);
    }


    public void updateData(String title, int img, int price, int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("image", img);
        cv.put("title", title);
        cv.put("price", price);
        cv.put("id", id);
        db.update(TABLENAME, cv, "id=" + id, null);
    }

    public void updateDataExpense(String title, int img, int price, int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("image", img);
        cv.put("title", title);
        cv.put("price", price);
        cv.put("id", id);
        db.update(TABLENAME2, cv, "id=" + id, null);
    }

    public void insertResult(int image, String title, int price, int fid) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("image", image);
        cv.put("title", title);
        cv.put("price", price);
        cv.put("fid", fid);
        db.insert(RESULT, null, cv);
    }

    public int getTotal(String mode) {
        SQLiteDatabase db = this.getReadableDatabase();
        int fid;
        int total = 0;
        int itotal = 0;
        int etotal = 0;
        Cursor cursor = db.rawQuery("select * from " + RESULT, null);
        if (cursor.getCount() != 0) {
            if (cursor.moveToFirst()) {
                do {
                    fid = cursor.getInt(cursor.getColumnIndex("fid"));
                    if (fid == 2) {
                        itotal = itotal + cursor.getInt(cursor.getColumnIndex("price"));
                    } else if (fid == 1) {
                        etotal = etotal + cursor.getInt(cursor.getColumnIndex("price"));
                    }

                } while (cursor.moveToNext());
            }
        }
        if (mode.equals("income"))
            total = itotal;
        else if (mode.equals("exp")) {
            total = etotal;
        }
        return total;
    }

    public ArrayList<Result> getResult() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from " + RESULT, null);
        if (cursor.getCount() != 0) {
            if (cursor.moveToFirst()) {
                do {
                    Result result = new Result();
                    result.setFid(cursor.getInt(cursor.getColumnIndex("fid")));
                    result.setTitle(cursor.getString(cursor.getColumnIndex("title")));
                    result.setPrice(cursor.getInt(cursor.getColumnIndex("price")));
                    result.setImage(cursor.getInt(cursor.getColumnIndex("image")));

                    myReadResult.add(result);

                } while (cursor.moveToNext());
            }
        }
        cursor.close();
        db.close();
        return myReadResult;
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
                    incomeDetail.setId(cursor.getInt(cursor.getColumnIndex("id")));
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

    public void removeDataIncome(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + TABLENAME + " where id=" + id);
        db.close();
    }

    public void removeDataExpense(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + TABLENAME2 + " where id=" + id);
        db.close();
    }


    public ArrayList<ExpenseDetail> getDataExpense() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from " + TABLENAME2, null);
        if (cursor.getCount() != 0) {
            if (cursor.moveToFirst()) {
                do {
                    ExpenseDetail expenseDetail = new ExpenseDetail();
                    expenseDetail.setId(cursor.getInt(cursor.getColumnIndex("id")));
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
