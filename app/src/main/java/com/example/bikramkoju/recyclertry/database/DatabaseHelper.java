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

    ArrayList<AddImage> myImageData = new ArrayList<>();


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

       /* db.execSQL("create table if not exixts " + IMAGETABLE + "(id integer primary key autoincrement," +
                "image INTEGER)");*/

        // db.execSQL("insert into " +TABLENAME +" values(null,abc,12)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLENAME);
        db.execSQL("drop table if exists " + TABLENAME2);
       // db.execSQL("drop table if exists " + IMAGETABLE);

        onCreate(db);

    }

    /*public void insertImage(int image){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("image",image);
        db.insert(IMAGETABLE,null,contentValues);
    }*/

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

   /* public ArrayList<AddImage> getImage(){
        SQLiteDatabase db =this.getReadableDatabase();
        Cursor cursor=db.rawQuery("Select * from " + IMAGETABLE, null);
        if (cursor.getCount() !=0){
            if (cursor.moveToFirst()){
                do {
                    AddImage addImage = new AddImage();
                    addImage.setAddimage(cursor.getInt(cursor.getColumnIndex("image")));

                    myImageData.add(addImage);
                } while (cursor.moveToNext());
            }
        }
        cursor.close();
        db.close();
        return myImageData;
    }
*/
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
