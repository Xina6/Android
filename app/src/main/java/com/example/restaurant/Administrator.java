package com.example.restaurant;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class Administrator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrator);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        Button btn_Add=(Button) findViewById(R.id.btn_Add);
        int width = getResources().getDisplayMetrics().widthPixels;
        int hei=getResources().getDisplayMetrics().heightPixels/3;
        btn_Add.setLayoutParams(new LinearLayout.LayoutParams(width,hei));

        Button btn_Reservations=(Button) findViewById(R.id.btn_Reservations);
        btn_Reservations.setLayoutParams(new LinearLayout.LayoutParams(width,hei));

        Button btn_Donate=(Button) findViewById(R.id.btn_Donate);
        btn_Donate.setLayoutParams(new LinearLayout.LayoutParams(width,hei));

        btn_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddFood();
            }
        });

        btn_Donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDonate();
            }
        });

        btn_Reservations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBooking();
            }
        });
    }

    public void openAddFood()
    {
        Intent intent= new Intent(this, AddF.class);
        startActivity(intent);
    }
    public void openDonate()
    {
        Intent intent= new Intent(this, Donate.class);
        startActivity(intent);
    }

    public void openBooking()
    {
        Intent intent= new Intent(this, Booking2.class);
        startActivity(intent);
    }
}
