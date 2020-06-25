package com.example.restaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnAdministrator;
    private Button btnClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdministrator=findViewById(R.id.btnAdministrator);
        btnAdministrator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAdministrator();
            }
        });

        btnClient=findViewById(R.id.btnClient);
        btnClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openClient();
            }
        });

    }
    public void openAdministrator()
    {
        CustomLoginDialog customLoginDialog = new CustomLoginDialog();
        customLoginDialog.show(getSupportFragmentManager(), "login_dialog");

    }
    public void openClient()
    {
        Intent intent1= new Intent(this, Client.class);
        startActivity(intent1);
    }
}
