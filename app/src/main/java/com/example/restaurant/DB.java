package com.example.restaurant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DB extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="RESTAURANT";
    public static final String TABLE_NAME="Menu";
    public static final String col1="id";
    public static final String col2="name";
    public static final String col3="description";
    public static final String col4="quantity";
    public static final String col5="price";
    public static final String col6="image";


    public DB(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("create table "+TABLE_NAME+" (id integer primary key, name text, description text, quantity integer, price float, image text) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean insert(int id, String name, String description, int quantity, float price, String image){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(col1,id);
        contentValues.put(col2,name);
        contentValues.put(col3,description);
        contentValues.put(col4,quantity);
        contentValues.put(col5,price);
        contentValues.put(col6,image);
        long result= db.insert(TABLE_NAME,null,contentValues);
        return result!=-1?true:false;
    }

    Cursor getAllData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    Cursor getAllDataFilter(String type){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select id as _id,name,description,quantity,price,image from "+TABLE_NAME+ " where description='"+type+"'" ,null);
        return res;
    }

    Cursor updateDonate(int id, int quantity){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("update "+TABLE_NAME+" set "+col4+"="+col4+"-"+quantity+" where "+col4+">="+quantity+" and id="+id,null);
        return res;
    }

    public boolean update(String id, String name, String description, int quantity, float price, String image){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(col2,name);
        contentValues.put(col3,description);
        contentValues.put(col4,quantity);
        contentValues.put(col5,price);
        contentValues.put(col5,image);
        long result= db.update(TABLE_NAME,contentValues," id=?",new String []{id});
        return result!=0?true:false;
    }

    public boolean delete(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        long result= db.delete(TABLE_NAME," id=?",new String []{id});
        return result!=0?true:false;
    }

}
