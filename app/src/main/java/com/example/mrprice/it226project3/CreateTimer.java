package com.example.mrprice.it226project3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class CreateTimer extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private String[] selection = new String [99];
    private String hours;
    private String minutes;
    private ArrayAdapter<String> hourAdapter = new ArrayAdapter<String>(this,
            android.R.layout.simple_spinner_dropdown_item,selection);

    private ArrayAdapter<String> minuteAdapter = new ArrayAdapter<String>(this,
            android.R.layout.simple_spinner_dropdown_item,selection);

    public void createTimer(View view)
    {
        //Remember to send to a class tha builds the alarm
        Intent intent = new Intent(this, .class);
        String timer = hours + ":" + minutes + ":" + "00";
        intent.putExtra("timer", timer);
    }

    public void onNothingSelected(AdapterView parent){}


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_timer);

        Spinner hourSpinner = (Spinner) findViewById(R.id.hourSpinner);
        Spinner minuteSpinner = (Spinner) findViewById(R.id.minuteSpinner);
        String[] selection = new String [99];
        for(int i = 0; i < 100; i++)
        {
            selection[i] = "" + i;
        }
//        ArrayAdapter<String> hourAdapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_spinner_dropdown_item,selection);

        hourAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        hourSpinner.setAdapter(hourAdapter);
        hourSpinner.setOnItemSelectedListener(this);

//        ArrayAdapter<String> minuteAdapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_spinner_dropdown_item,selection);
        minuteSpinner.setAdapter(minuteAdapter);
        minuteSpinner.setOnItemSelectedListener(this);

    }
}
