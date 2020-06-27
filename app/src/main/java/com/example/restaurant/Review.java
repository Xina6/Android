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
import android.widget.Toast;

public class Review extends AppCompatActivity {
    EditText Name, Description, Calificative;
    Button addReview;
    final DBR myDBR = new DBR(this);
    static int id=70;


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
        addReview=(Button)findViewById(R.id.btnAddRe);
        Name=(EditText) findViewById(R.id.txtNameC);
        Description=(EditText) findViewById(R.id.txtDescriptionR);
        Calificative=(EditText) findViewById(R.id.txtCalificativeR);
       Cursor res=myDBR.getAllData();
        if(res.getCount()==0) {
            showMessage("Eroare", "Nu exista date!");
            return;
        }


        addReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id++;
                System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
                String name=Name.getText().toString();
                String description=Description.getText().toString();
                int calificative=Integer.parseInt(Calificative.getText().toString());
                boolean isInserted= myDBR.insert(id,name,description,calificative);
                if (isInserted == true)
                    Toast.makeText(Review.this, "Adaugarea s-a realizat cu succes!",
                            Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(Review.this, "Eroare la adaugare!", Toast.LENGTH_LONG).
                            show();
            }
        });

        final android.widget.ListView lvItems = (ListView) findViewById(R.id.listview_review);
        final CursorAR todoAdapter = new CursorAR(this, res);
        lvItems.setAdapter(todoAdapter);
    }

    public void openReview()
    {
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
