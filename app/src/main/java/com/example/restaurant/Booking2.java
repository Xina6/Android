package com.example.restaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Booking2 extends AppCompatActivity {
    Button btnDelete;
    EditText editId;
    TextView Booking;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking2);
        final DBREZ myDB = new DBREZ(this);
        btnDelete=(Button) findViewById(R.id.btnDel);
        editId=(EditText) findViewById(R.id.txtId);
        Booking=(TextView) findViewById(R.id.txtRez);
        Cursor res=myDB.getAllData();
        if (res.getCount() == 0) {
            Toast.makeText(Booking2.this, "Nu exista informatii care corespund criteriilor!",
                    Toast.LENGTH_LONG).show();
            Booking.setText(null);
            return;
        } else {
            StringBuffer buffer = new StringBuffer();
            while (res.moveToNext()) {
                buffer.append(res.getString(0) + ". " + res.getString(1) + ", " + res.getString(2) + ", " + res.getString(3)+", " + res.getString(4) +"\n");
            }
            Booking.setText(buffer.toString());
        }
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String id = editId.getText().toString();
                    boolean isInserted = myDB.delete(id);
                    if (isInserted == true)
                        Toast.makeText(Booking2.this, "Eliminarea s-a realizat cu succes!",
                                Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(Booking2.this, "Nu exista element cu acest ID!", Toast.LENGTH_LONG).
                                show();

                } catch (NumberFormatException e) {
                    Toast.makeText(Booking2.this, "Nu ati introdus o valoare valida!",
                            Toast.LENGTH_LONG).show();
                }
                Cursor res=myDB.getAllData();
                if (res.getCount() == 0) {
                    Toast.makeText(Booking2.this, "Nu exista informatii care corespund criteriilor!",
                            Toast.LENGTH_LONG).show();
                    Booking.setText(null);
                    return;
                } else {
                    StringBuffer buffer = new StringBuffer();
                    while (res.moveToNext()) {
                        buffer.append(res.getString(0) + ", " + res.getString(1) + ", " + res.getString(3) + "\n");
                    }
                    Booking.setText(buffer.toString());
                }
            }
        });
    }
}
