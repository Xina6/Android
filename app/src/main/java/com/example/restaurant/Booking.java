package com.example.restaurant;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Booking extends AppCompatActivity {
    EditText editName, editNumber, editDescription, editSpecifications, editPhone;
    Button btnAdd;
    int id=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        final DBREZ myDB = new DBREZ(this);
        editName = (EditText) findViewById(R.id.txtName);
        editNumber = (EditText) findViewById(R.id.txtNumber);
        editDescription =(EditText) findViewById(R.id.txtDescription);
        editSpecifications =(EditText) findViewById(R.id.txtSpecifications);
        editPhone =(EditText) findViewById(R.id.txtPhone);
        btnAdd = (Button) findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id++;
                try {
                    String name = editName.getText().toString();
                    String description = editDescription.getText().toString();
                    String number = editNumber.getText().toString();
                    String specifications= editSpecifications.getText().toString();
                    String phone=editPhone.getText().toString();
                    boolean isInserted = myDB.insert(id, name, number, description, specifications, phone);
                    if (isInserted == true)
                        Toast.makeText(Booking.this, "Adaugarea s-a realizat cu succes!",
                                Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(Booking.this, "Eroare la adaugare!", Toast.LENGTH_LONG).
                                show();
                } catch (NumberFormatException e) {
                    Toast.makeText(Booking.this, "Nu ati introdus o valoare valida!",
                            Toast.LENGTH_LONG).show();
                }
                id++;
            }
        });

    }
}
