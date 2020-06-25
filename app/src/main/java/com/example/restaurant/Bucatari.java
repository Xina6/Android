package com.example.restaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Bucatari extends AppCompatActivity {
    private Button btnChef1, btnChef2, btnChef3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bucatari);
        btnChef1=(Button) findViewById(R.id.chef1);
        btnChef2=(Button) findViewById(R.id.chef2);
        btnChef3=(Button) findViewById(R.id.chef3);

        btnChef1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openChef1();
            }
        });

        btnChef2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openChef2();
            }
        });

        btnChef3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openChef3();
            }
        });
    }

    public void openChef1()
    {
        Intent intent= new Intent(this, Chef1.class);
        startActivity(intent);
    }

    public void openChef2()
    {
        Intent intent= new Intent(this, Chef2.class);
        startActivity(intent);
    }

    public void openChef3()
    {
        Intent intent= new Intent(this, Chef3.class);
        startActivity(intent);
    }
}
