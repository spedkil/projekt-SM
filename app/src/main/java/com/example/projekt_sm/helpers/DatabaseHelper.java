package com.example.projekt_sm.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public  class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Records.db";
    public static final String TABLE_NAME = "Record_table";
    public static final String COL_2 = "FILE_NAME";
    public static final String COL_3 = "RESULTS";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, FILE_NAME TEXT, RESULTS TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public boolean insertData(String file_name, String results){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,file_name);
        contentValues.put(COL_3,results );
        db.insert(TABLE_NAME,null, contentValues);
        long Iresult = db.insert(TABLE_NAME,null, contentValues);
        if (Iresult == -1 ){
            return false;
        }
        else{
            return true;
        }
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }
}
