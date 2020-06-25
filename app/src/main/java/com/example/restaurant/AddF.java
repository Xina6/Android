package com.example.restaurant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddF extends AppCompatActivity {
    EditText editId, editName, editDescription, editQuantity, editPrice;
    ImageView imageView;
    Button btnAdd, btnShow, btnUpdate, btnDelete, btnChoose;
    String path;
    final int REQUEST_CODE_GALLERY = 999;
    Uri uri;

    public void showMessage(String title, String message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this) ;
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_f);

        final DB myDB = new DB(this);
        editId = (EditText) findViewById(R.id.txtId);
        editName = (EditText) findViewById(R.id.txtName);
        editPrice = (EditText) findViewById(R.id.txtPrice);
        editQuantity =(EditText) findViewById(R.id.txtQuantity);
        editDescription =(EditText) findViewById(R.id.txtDescription);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnShow = (Button) findViewById(R.id.btnShow);
        btnUpdate= (Button) findViewById(R.id.btnUpdate);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnChoose= (Button) findViewById(R.id.btnChoose);
        imageView= (ImageView) findViewById(R.id.imgView);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                int id = Integer.parseInt(editId.getText().toString());
                String name = editName.getText().toString();
                String description = editDescription.getText().toString();
                float price = Float.parseFloat(editPrice.getText().toString());
                int quantity= Integer.parseInt(editQuantity.getText().toString());
                boolean isInserted = myDB.insert(id, name, description, quantity, price, path);
                if (isInserted == true)
                    Toast.makeText(AddF.this, "Adaugarea s-a realizat cu succes!",
                            Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(AddF.this, "Eroare la adaugare!", Toast.LENGTH_LONG).
                            show();
                } catch (NumberFormatException e) {
                    Toast.makeText(AddF.this, "Nu ati introdus o valoare valida!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(
                        AddF.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY
                );
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res=myDB.getAllData();
                if(res.getCount()==0){
                    showMessage("Eroare","Nu exista date!");
                    return;
                }
                else{
                    StringBuffer buffer=new StringBuffer();
                    while(res.moveToNext()){
                        buffer.append(res.getString(0)+", "+res.getString(1)+", "+res.getString(3)+"\n");
                    }
                    showMessage("Datele introduse sunt: ",buffer.toString());
                }
            }
        });


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = editId.getText().toString();
                boolean isInserted = myDB.delete(id);
                if (isInserted == true)
                    Toast.makeText(AddF.this, "Eliminarea s-a realizat cu succes!",
                            Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(AddF.this, "Nu exista element cu acest ID!", Toast.LENGTH_LONG).
                            show();
            }
        });


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                String id = editId.getText().toString();
                String name = editName.getText().toString();
                String description = editDescription.getText().toString();
                float price = Float.parseFloat(editPrice.getText().toString());
                int quantity= Integer.parseInt(editQuantity.getText().toString());
                boolean update = myDB.update(id, name, description, quantity, price, path);
                if (update == true)
                    Toast.makeText(AddF.this, "Actualizarea s-a realizat cu succes!",
                            Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(AddF.this, "Nu exista element cu Id-ul introdus!", Toast.LENGTH_LONG).
                            show();
                } catch (NumberFormatException e) {
                    Toast.makeText(AddF.this, "Nu ati introdus o valoare valida!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == REQUEST_CODE_GALLERY){
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);
            }
            else {
                Toast.makeText(getApplicationContext(), "You don't have permission to access file location!", Toast.LENGTH_SHORT).show();
            }
            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null) {
            uri = data.getData();
            path= convertMediaUriToPath(uri);
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    public String convertMediaUriToPath(Uri uri) {
        String [] proj={MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, proj,  null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String path = cursor.getString(column_index);
        cursor.close();
        return path;
    }
}
