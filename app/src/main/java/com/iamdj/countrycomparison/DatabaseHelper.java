package com.iamdj.countrycomparison;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "country.db";
    public static final String TABLE_NAME = "country_table";
    public static final String TABLE_COL_1 = "id";
    public static final String TABLE_COL_2 = "name";
    public static final String TABLE_COL_3 = "continent";
    public static final String TABLE_COL_4 = "cities";
    public static final String TABLE_COL_5 = "park";
    public static final String TABLE_COL_6 = "airports";
    public static final String TABLE_COL_7 = "hotels";


    long startTime,execTime;







    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {



        sqLiteDatabase.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT ,NAME TEXT,Continent TEXT,Cities TEXT,Park TEXT, Airports TEXT,Hotels TEXT)");

        ;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {


        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);

    }

    // Performing Insert Operation on Database
    public boolean insertData(String name, String continent, String cities, String park, String airports,String hotels){


        startTime= System.currentTimeMillis();
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_COL_2, name);
        contentValues.put(TABLE_COL_3, continent);
        contentValues.put(TABLE_COL_4, cities);
        contentValues.put(TABLE_COL_5, park);
        contentValues.put(TABLE_COL_6, airports);
        contentValues.put(TABLE_COL_7, hotels);

        execTime = System.currentTimeMillis() - startTime;
        DemoClass.executionTime=execTime;
        Log.d("instimeh", String.valueOf(execTime));
        // Insert contents into database
        long success = db.insert(TABLE_NAME, null, contentValues);

        if(success == -1){ // when query not inserted into database
            return false;
        }else{
            return true;
        }

    }

    // Read all Data from Database using CURSOR to pick one by one row
    public Cursor getAllData(){
        startTime= System.currentTimeMillis();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery("select * from " +TABLE_NAME,null);
        execTime = System.currentTimeMillis() - startTime;
        DemoClass.executionTime=execTime;
        Log.d("readtimeh", String.valueOf(execTime));
        return cur;
    }


    // Update Data of Database table
    public boolean updateData(String id,String name,String continent,String cities,String parks,String airports,String hotels){
        startTime= System.currentTimeMillis();
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues(); // Accessing content for overwrite
        contentValues.put(TABLE_COL_1,id);
        contentValues.put(TABLE_COL_2,name);
        contentValues.put(TABLE_COL_3,continent);
        contentValues.put(TABLE_COL_4,cities);
        contentValues.put(TABLE_COL_5, parks);
        contentValues.put(TABLE_COL_6,airports);
        contentValues.put(TABLE_COL_7,hotels);



        db.update(TABLE_NAME,contentValues, "ID = ?", new String[]{id});
        execTime = System.currentTimeMillis() - startTime;
        DemoClass.executionTime=execTime;

        Log.d("uptimeh", String.valueOf(execTime));
        return true;
    }

    // Delete Data from Database table
    public Integer deleteData(String id){
        startTime= System.currentTimeMillis();
        SQLiteDatabase db=this.getWritableDatabase();
        execTime = System.currentTimeMillis() - startTime;
        DemoClass.executionTime=execTime;

        Log.d("deltimeh", String.valueOf(execTime));
        return db.delete(TABLE_NAME, "ID = ?", new String[]{id});

    }




}
