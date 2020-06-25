package com.example.restaurant;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;


public class MenuSelected extends AppCompatActivity {
    private ListView lvProduct;
    String type;
    final DB myDB = new DB(this);

    public void showMessage(String title, String message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this) ;
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    protected void onCreate(Bundle savedInstanceState) {
        Bundle bundle=getIntent().getExtras();
        type=bundle.getString("model");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        DB handler = new DB(this);
        Cursor res = myDB.getAllDataFilter(type);
        if(res.getCount()==0) {
            showMessage("Eroare", "Nu exista date!");
            return;
        }
        final android.widget.ListView lvItems = (ListView) findViewById(R.id.listview_product);
        final MyCursorAdapter todoAdapter = new MyCursorAdapter(this, res);
        lvItems.setAdapter(todoAdapter);

        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

            }});
    }
}
