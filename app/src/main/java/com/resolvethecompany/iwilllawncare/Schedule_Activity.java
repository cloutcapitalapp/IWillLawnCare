package com.resolvethecompany.iwilllawncare;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.Spinner;

public class Schedule_Activity extends AppCompatActivity {

    CalendarView calView;
    String[] scheduleTimes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedual);

        //init time array
        scheduleTimes = new String[]{"9:00am","10:00am","11:00am",
                "12:00am", "01:00am", "02:00am", "03:00am",
                "04:00am", "05:00am"};

        calView = findViewById(R.id.calendarView);
    }

    @Override
    protected void onStart(){
        super.onStart();

        onDateChangeListener_Method();
    }

    private void onDateChangeListener_Method(){
        calView.setOnDateChangeListener((calendarView, i, i1, i2) -> {
            AlertDialog.Builder alertDate = new AlertDialog.Builder(this);
            LinearLayout layout = new LinearLayout(this);
            layout.setOrientation(LinearLayout.VERTICAL);

            Spinner timeSpinner = new Spinner(this);

            // Create the instance of ArrayAdapter
            // having the list of courses
            ArrayAdapter ad
                    = new ArrayAdapter(
                    this,
                    R.layout.spinner_item,
                    R.id.spinner_item,
                    scheduleTimes);

            timeSpinner.setAdapter(ad);

            layout.addView(timeSpinner);
            alertDate.setView(layout);

            alertDate.setPositiveButton("OK", (dialogInterface, i3) -> {
                AlertDialog alertDateClose = alertDate.create();
                alertDateClose.dismiss();
            });
            alertDate.show();
        });
    }
}