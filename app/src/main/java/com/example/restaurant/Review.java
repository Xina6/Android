package com.example.restaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class Review extends AppCompatActivity {
    EditText Name, Description, Calificative;
    Button addReview;
    final DBR myDBR = new DBR(this);
    int id=0;
    TextView tvNume, tvDescriere, tvCalificativ;

    public void showMessage(String title, String message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this) ;
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_review, parent, false);
    }
    View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        addReview=(Button)findViewById(R.id.btnAddR);
        Name=(EditText) findViewById(R.id.txtNameC);
        Description=(EditText) findViewById(R.id.txtDescription);
        Calificative=(EditText) findViewById(R.id.txtCalificative);
        Cursor res=myDBR.getAllData();
        if(res.getCount()==0) {
            showMessage("Eroare", "Nu exista date!");
            return;
        }
        final android.widget.ListView lvItems = (ListView) findViewById(R.id.listview_review);
        final CursorAR todoAdapter = new CursorAR(this, res);
        lvItems.setAdapter(todoAdapter);
        addReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openReview();
            }
        });
    }

    public void openReview()
    {   id++;
        String name=Name.getText().toString();
        String description=Description.getText().toString();
        int calificative=Integer.parseInt(Calificative.getText().toString());
        myDBR.insert(id,name,description,calificative);
        Cursor res=myDBR.getAllData();
        if(res.getCount()==0) {
            showMessage("Eroare", "Nu exista date!");
            return;
        }
        final android.widget.ListView lvItems = (ListView) findViewById(R.id.listview_review);
        final CursorAR todoAdapter = new CursorAR(this, res);
        lvItems.setAdapter(todoAdapter);
        id++;
    }
}
