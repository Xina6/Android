package com.example.restaurant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DBREZ extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="RESTAURAN";
    public static final String TABLE_NAME="Booling";
    public static final String col1="id";
    public static final String col2="name";
    public static final String col3="number";
    public static final String col4="description";
    public static final String col5="specifications";
    public static final String col6="phone";
    int i=5;

    public DBREZ(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("create table "+TABLE_NAME+" (id integer primary key, name text, number, description text, specifications  text, phone) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean insert(int id, String name, String number, String description, String specifications, String phone){
        id++;
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(col1,id);
        contentValues.put(col2,name);
        contentValues.put(col3,number);
        contentValues.put(col4,description);
        contentValues.put(col5,specifications);
        contentValues.put(col6,phone);
        long result= db.insert(TABLE_NAME,null,contentValues);
        return result!=-1?true:false;
    }

    Cursor getAllData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    public boolean delete(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        long result= db.delete(TABLE_NAME," id=?",new String []{id});
        return result!=0?true:false;
    }

}
