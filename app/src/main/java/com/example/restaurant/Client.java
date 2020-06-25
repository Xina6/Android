package com.example.restaurant;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Client extends AppCompatActivity {
    private Button btnChef, btnBooking, btnReviews, btnMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_client);
        btnChef=(Button) findViewById(R.id.btnChef);
        btnBooking=(Button) findViewById(R.id.btnBooking);
        btnReviews=(Button) findViewById(R.id.btnReviews);
        btnMenu=(Button) findViewById(R.id.btnMenu);

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMenu();
            }
        });
        btnReviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openReview();
            }
        });

        btnChef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openChef();
            }
        });
        btnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBooking();
            }
        });
    }

    public void openMenu()
    {
        Intent intent= new Intent(this, MenuI.class);
        startActivity(intent);
    }
    public void openReview()
    {
        Intent intent= new Intent(this, Review.class);
        startActivity(intent);
    }

    public void openChef()
    {
        Intent intent= new Intent(this, Bucatari.class);
        startActivity(intent);
    }

    public void openBooking()
    {
        Intent intent= new Intent(this, Booking.class);
        startActivity(intent);
    }
}
