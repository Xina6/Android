package com.example.restaurant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBR extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="REV";
    public static final String TABLE_NAME="Review";
    public static final String col1="id";
    public static final String col2="name";
    public static final String col3="description";
    public static final String col4="calificative";

    public DBR(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("create table "+TABLE_NAME+" (id integer primary key, name text, description text, calificative integer) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean insert(int id, String name, String description, int calificative){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(col1,id);
        contentValues.put(col2,name);
        contentValues.put(col3,description);
        contentValues.put(col4,calificative);
        long result= db.insert(TABLE_NAME,null,contentValues);
        return result!=-1?true:false;
    }

    Cursor getAllData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select id as _id,name,description,calificative from "+TABLE_NAME,null);
        return res;
    }

}
