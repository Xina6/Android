package com.example.restaurant;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuI extends AppCompatActivity {
    private Button btnSoup, btnPasta, btnVegetables, btnDessert, btnDrinks, btnPizza;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_i);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        btnSoup=(Button) findViewById(R.id.btnSoup);
        btnPasta=(Button) findViewById(R.id.btnPasta);
        btnVegetables=(Button) findViewById(R.id.btnVegetables);
        btnDessert=(Button) findViewById(R.id.btnDessert);
        btnDrinks=(Button) findViewById(R.id.btnDrinks);
        btnPizza=(Button) findViewById(R.id.btnPizza);

        btnSoup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMenu("soup");
            }
        });

        btnPasta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMenu("pasta");
            }
        });

        btnVegetables.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMenu("salad");
            }
        });

        btnDessert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMenu("dessert");
            }
        });

        btnDrinks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMenu("drink");
            }
        });

        btnPizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMenu("pizza");
            }
        });

    }

    public void openMenu(String type)
    {
        Intent intent= new Intent(this, MenuSelected.class);
        intent.putExtra("model",type);
        startActivity(intent);
    }
}
