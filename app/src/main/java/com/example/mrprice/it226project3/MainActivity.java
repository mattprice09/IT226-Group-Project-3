package com.example.mrprice.it226project3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

public class MainActivity extends BaseActivity {

    public void setOneTime(View view) {
        //send user to one time alarm screen
        Intent intent = new Intent(this, CreateOneTimeAlarm.class);
        startActivity(intent);
    }

    public void setRecursive(View veiw)
    {

    }

    public void setLocation(View view)
    {

    }

    public void setTimer(View view)
    {
        //send user to timer screen
        Intent intent = new Intent(this, CreateTimer.class);
    }

    // Initialize any and all UI-related elements in the CreateOneTimeAlarm activity
    private void initializeOneTimeAlarm() {
        // Add time zone AutoComplete options in the Time Zone field
        AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.timeZoneTxt);
        String [] timeZones = getResources().getStringArray(R.array.time_zones_list);
        ArrayAdapter<String> tzAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, timeZones);
        textView.setAdapter(tzAdapter);

        // Create good-looking time picker
        EditText timeEdit = (EditText) findViewById(R.id.enterTimeTxt);
        timeEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerFragment frag = new TimePickerFragment();
                frag.showTruitonTimePickerDialog(v);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeOneTimeAlarm();
    }
}
