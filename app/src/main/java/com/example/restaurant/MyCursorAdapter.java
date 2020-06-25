package com.example.restaurant;

import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class MyCursorAdapter extends CursorAdapter {
    public MyCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_listview, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // TextView tvId= (TextView) view.findViewById(R.id.tv_product_id);
        LinearLayout background = (LinearLayout) view.findViewById(R.id.tv_product_image);
        TextView tvName = (TextView) view.findViewById(R.id.tv_product_name);
        TextView tvPrice = (TextView) view.findViewById(R.id.tv_product_price);
        //ImageView tvImage= (ImageView) view.findViewById(R.id.imgView);
        //int id = cursor.getInt(cursor.getColumnIndexOrThrow(DB.col1 ));
        String name = cursor.getString(cursor.getColumnIndexOrThrow(DB.col2));
        String description = cursor.getString(cursor.getColumnIndexOrThrow(DB.col3));
        int quantity = cursor.getInt(cursor.getColumnIndexOrThrow(DB.col4));
        float price = cursor.getFloat(cursor.getColumnIndexOrThrow(DB.col5));
        String image = cursor.getString(cursor.getColumnIndexOrThrow(DB.col6));
        tvName.setText(name);
        tvPrice.setText(String.valueOf(price));
        File imgFile = new File(image);
        if (imgFile.exists()) {
            Bitmap bitmap = BitmapFactory.decodeFile(image);
            BitmapDrawable ob = new BitmapDrawable(Resources.getSystem(), bitmap);
            background.setBackground(ob);
        }
    }
}