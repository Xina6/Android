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
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;

public class CursorAR extends CursorAdapter {
    public CursorAR(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_review, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tvNume = (TextView) view.findViewById(R.id.review_name);
        TextView tvDescriere = (TextView) view.findViewById(R.id.review_description);
        TextView tvCalificativ = (TextView) view.findViewById(R.id.review_calificative);
        String name = cursor.getString(cursor.getColumnIndexOrThrow(DBR.col2));
        String description = cursor.getString(cursor.getColumnIndexOrThrow(DBR.col3));
        String calificative = cursor.getString(cursor.getColumnIndexOrThrow(DBR.col4));
        tvNume.setText(name);
        tvDescriere.setText(description);
        tvCalificativ.setText(String.valueOf(calificative));

    }
}
