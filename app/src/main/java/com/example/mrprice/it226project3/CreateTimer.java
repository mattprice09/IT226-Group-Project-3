package com.example.mrprice.it226project3;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

public class CreateTimer extends BaseActivity {

    NumberPicker np2;
    TextView tv1, tv2;
    NumberPicker np3;
    int uniqueID;//id used for pending intents

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_timer);

        // Handle "days" input
        np2 = (NumberPicker) findViewById(R.id.numberPicker2);
        tv1 = (TextView) findViewById(R.id.textView2);

        np2.setMinValue(0);
        np2.setMaxValue(10);
        np2.setWrapSelectorWheel(false);

        np2.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

            @Override
            public void onValueChange(NumberPicker picker, int zeros, int days) {
                String numDays = "Days : ";

                tv1.setText(numDays.concat(String.valueOf(days)));
            }
        });

        // Handle "minutes" input
        np3 = (NumberPicker) findViewById(R.id.numberPicker3);
        tv2 = (TextView) findViewById(R.id.textView3);

        np3.setMinValue(0);
        np3.setMaxValue(60);
        np3.setWrapSelectorWheel(false);

        np3.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

            @Override
            public void onValueChange(NumberPicker picker, int zero, int min) {
                String numDays = "Minutes : ";
                tv2.setText(numDays.concat(String.valueOf(min)));
            }
        });
    }

    public void createTimer(View v)
    {
        long timeLimit;

        //Check for overriding
        int days = np2.getValue();
        timeLimit = ((long)days) * 86400000;

        int minutes = np3.getValue();
        timeLimit += (((long)minutes) * 60000);

        String message = ((EditText) findViewById(R.id.enterTxt)).getText().toString();

        //set alarm

        // Get location from MainActivity
        this.updateLocation();
        String location = "Latitude: " + this.currentLocation.getLatitude() + "\nLongitude: " + this.currentLocation.getLongitude();

        uniqueID = (int) System.currentTimeMillis();//we need a unique id so that multiple alarms can be handled at once.

        //need to find location and put it into a string
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmReceiver.class);
        intent.putExtra("msg", message);
        intent.putExtra("location", location);
//        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + timeLimit,
//                PendingIntent.getBroadcast(this, uniqueID, intent, PendingIntent.FLAG_UPDATE_CURRENT) );
        PendingIntent penIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        alarmManager.set(AlarmManager.RTC_WAKEUP, timeLimit, penIntent);

        //Will give button press confirmation
        Toast.makeText(getApplicationContext(), "Alarm Set", Toast.LENGTH_LONG).show();

    }
}
