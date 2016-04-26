package com.example.mrprice.it226project3;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

public class CreateOneTimeAlarm extends AppCompatActivity {

    //will pull data from text views for one time alarm. Will bring user
    //to screen to set time zone.
    @TargetApi(Build.VERSION_CODES.M)
    public void setTimeZone(View view)
    {
        //will store time that is picked into a string.
        TimePicker timePicker;
        timePicker = (TimePicker) findViewById(R.id.timePickerOneTime);
        int hour = timePicker.getHour();//will return in military time
        int min = timePicker.getMinute();
        String time = hour + ":" + min + ":" + "00";

        DatePicker datePicker;
        datePicker = (DatePicker) findViewById(R.id.calendarViewOneTime);
        int month = datePicker.getMonth();
        int day = datePicker.getDayOfMonth();
        int year = datePicker.getYear();
        //will store date mm/dd/year
        String date = month + "/" + day + "/" + year;

        Intent intent = new Intent(this, SetTimeZone.class);
        EditText messageText = (EditText) findViewById(R.id.edit_message);
        String data = messageText.getText().toString() +
                "QWERT" + date + "QWERT" + time;
        intent.putExtra("alarmData", data);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_one_time_alarm);
    }
}
