package com.example.mrprice.it226project3;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;

import java.util.Calendar;

public class CreateOneTimeAlarm extends BaseActivity {

    private Calendar cal;
    private static final String DLM = "QWERT";

    // This method is called when user clicks "Next" to continue=
    public void buttonSubmit(View view) {

        // Handle user Message input
        String messageText = ((EditText) findViewById(R.id.edit_message)).getText().toString();
        System.out.println("Message: " + messageText);

        // Handle user Date input
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int year = cal.get(Calendar.YEAR);
        String date = month + "/" + day + "/" + year;
        System.out.println("Date: " + date);

        // Handle user Time input
        String time = ((EditText) findViewById(R.id.enterTimeTxt)).getText().toString();
        System.out.println("Time: " + time);

        // Handle user Time Zone input
        String timeZone = ((EditText) findViewById(R.id.enterTimeTxt)).getText().toString();
        System.out.println("Time Zone: " + timeZone);

        // Handle recurrence user input
        RadioGroup rGroup = (RadioGroup) findViewById(R.id.recurOptions);
        int selectedID = rGroup.getCheckedRadioButtonId();
        RadioButton recurChoice = (RadioButton) findViewById(selectedID);
        String recurTxt = recurChoice.getText().toString();
        System.out.println("Recurrence choice: " + recurTxt);

        // pack user input
        String data = messageText + DLM + date + DLM + time + DLM + timeZone + DLM + recurTxt;

        System.out.println("Data string: " + data);
        // super method
        this.CreateAlarm(data, DLM);
    }

    // Initialize any and all UI-related elements in the CreateOneTimeAlarm activity
    private void initialize() {

        // Add time zone AutoComplete options in the Time Zone field
        AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.timeZoneTxt);
        String [] timeZones = getResources().getStringArray(R.array.time_zones_list);
        ArrayAdapter<String> tzAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, timeZones);
        textView.setThreshold(1);
        textView.setAdapter(tzAdapter);

        cal = Calendar.getInstance();
        CalendarView calendarView = (CalendarView) findViewById(R.id.calendarViewOneTime);
        calendarView.setOnDateChangeListener( new CalendarView.OnDateChangeListener() {
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                cal.set(year, month, dayOfMonth);
            }
        });

        // Create good-looking time picker
//        EditText timeEdit = (EditText) findViewById(R.id.enterTimeTxt);
//        timeEdit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                DialogFragment newFragment = new TP_Fragment();
//                newFragment.show(getSupportFragmentManager(), "timePicker");
//
////                TimePickerFragment frag = new TimePickerFragment();
////                frag.showTimePickerDialog(v);
////                Intent i = new Intent(v.getContext(), TimePickerFragment.class);
////                i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
////                startActivity(i);
//            }
//        });
    }

    // Custom DialogFragment to pick time
//    public class TP_Fragment extends DialogFragment implements
//            TimePickerDialog.OnTimeSetListener {
//
//        @Override
//        public void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//        }
//
//        @Override
//        public Dialog onCreateDialog(Bundle savedInstanceState) {
//            // Use the current time as the default values for the picker
//            final Calendar c = Calendar.getInstance();
//            int hour = c.get(Calendar.HOUR_OF_DAY);
//            int minute = c.get(Calendar.MINUTE);
//
//            // Create a new instance of TimePickerDialog and return it
//            return new TimePickerDialog(getActivity(), this, hour, minute,
//                    DateFormat.is24HourFormat(getActivity()));
//        }
//
//        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//            // Do something with the time chosen by the user
//            timeEdit.setText(timeEdit.getText() + " -" + hourOfDay + ":" + minute);
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_one_time_alarm);

        initialize();
    }
}
