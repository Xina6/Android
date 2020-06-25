package com.example.restaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Donate extends AppCompatActivity {
    Button btnDonate;
    EditText editId,editQuantity;
    TextView Food;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate);
        final DB myDB = new DB(this);
        btnDonate=(Button) findViewById(R.id.btnDonate);
        editId=(EditText) findViewById(R.id.txtId);
        editQuantity=(EditText) findViewById(R.id.txtQuantity);
        Food=(TextView) findViewById(R.id.txtFood);
        Cursor res=myDB.getAllData();
        if (res.getCount() == 0) {
            Toast.makeText(Donate.this, "Nu exista informatii care corespund criteriilor!",
                    Toast.LENGTH_LONG).show();
            Food.setText(null);
            return;
        } else {
            StringBuffer buffer = new StringBuffer();
            while (res.moveToNext()) {
                buffer.append(res.getString(0) + ". " + res.getString(1) + ", " + res.getString(3) + "\n");
            }
            Food.setText(buffer.toString());
        }
        btnDonate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int id = Integer.parseInt(editId.getText().toString());
                    int quantity= Integer.parseInt(editQuantity.getText().toString());
                    Cursor res=myDB.updateDonate(id,quantity);
                    if (res.getCount() == 0) {
                       // Toast.makeText(Donate.this, "Nu exista informatii care corespund criteriilor!",
                              //  Toast.LENGTH_LONG).show();
                        //Food.setText(null);
                       // return;
                    } else {
                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append(res.getString(0) + ". " + res.getString(1) + ", " + res.getString(3) + "\n");
                        }
                        Food.setText(buffer.toString());
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(Donate.this, "Nu ati introdus o valoare valida!",
                            Toast.LENGTH_LONG).show();
                }
                Cursor res=myDB.getAllData();
                if (res.getCount() == 0) {
                    Toast.makeText(Donate.this, "Nu exista informatii care corespund criteriilor!",
                            Toast.LENGTH_LONG).show();
                    Food.setText(null);
                    return;
                } else {
                    StringBuffer buffer = new StringBuffer();
                    while (res.moveToNext()) {
                        buffer.append(res.getString(0) + ", " + res.getString(1) + ", " + res.getString(3) + "\n");
                    }
                    Food.setText(buffer.toString());
                }
            }
        });
    }
}
