package com.example.mrprice.it226project3;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

public class SetTimeZone extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_time_zone);
    }

    // Convert a string to an ArrayList containing int values in order.
    private ArrayList<Integer> getParts(String dateStr, String regex) {
        String [] parts = dateStr.split(regex);

        ArrayList<Integer> output = new ArrayList<Integer>();
        for (String part : parts) {
            output.add(Integer.parseInt(part));
        }

        return output;
    }

    public void createAlarmCustom(View view)
    {
        Intent previousIntent = getIntent();
        //check if the data is being pulled from alarmData
        String dataStr = previousIntent.getStringExtra("alarmData");
        String [] parts = dataStr.split("QWERT");
        String msg = parts[0];
        String date = parts[1]; // MM/dd/YYYY
        String time = parts[2]; // hh:mm:ss
        ArrayList<Integer> dateParts = getParts(date, "/");
        ArrayList<Integer> timeParts = getParts(time, ":");

        EditText customZone = (EditText) findViewById(R.id.enter_time_zone);
        String timeZone = customZone.getText().toString();
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone(timeZone));
        cal.set(dateParts.get(2), dateParts.get(0), dateParts.get(1), timeParts.get(0), timeParts.get(1), timeParts.get(2));

        //set calendar time. Need to concatenate string from previousIntent first
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent newIntent = new Intent(this, AlarmDisplay.class);
        PendingIntent penIntent = PendingIntent.getBroadcast(this, 0, newIntent, 0);
        alarmManager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), penIntent);
    }

    public void createAlarmCurrent(View view)
    {
        //getIntent() returns data from the intent that caused this activity.
        Intent intent = getIntent();
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getDefault());
//missing some
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent newIntent = new Intent(this, AlarmDisplay.class);
        PendingIntent penIntent = PendingIntent.getBroadcast(this, 0, newIntent, 0);
        alarmManager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), penIntent);

    }
}
