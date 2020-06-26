package com.example.restaurant;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class Booking extends AppCompatActivity {
    EditText editName, editNumber, editDescription, editSpecifications, editPhone;
    Button btnAdd;
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private TextView mDisplayTime;
    private TimePickerDialog.OnTimeSetListener mTimeSetListener;
    private static final String TAG = "Rezervare";
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
        mDisplayDate = (TextView) findViewById(R.id.tvDate);
        mDisplayTime = (TextView) findViewById(R.id.tvTime);
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog dialog = new DatePickerDialog(
                        Booking.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();


            }
        });

        mDisplayTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int hour = cal.get(Calendar.HOUR);
                int minute = cal.get(Calendar.MINUTE);

                TimePickerDialog dialog2 = new TimePickerDialog(Booking.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mTimeSetListener, hour, minute, DateFormat.is24HourFormat(Booking.this));
                dialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog2.show();

            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                mDisplayDate.setText(date);
            }
        };

        mTimeSetListener =new TimePickerDialog.OnTimeSetListener()

        {
            @Override
            public void onTimeSet (TimePicker timePicker, int hour, int minute){
                hour = hour + 1;
                Log.d(TAG, "onDateSet : " + hour + ":" + minute);

                String time = hour + ":" + minute;
                mDisplayTime.setText(time);
            }

        };


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
