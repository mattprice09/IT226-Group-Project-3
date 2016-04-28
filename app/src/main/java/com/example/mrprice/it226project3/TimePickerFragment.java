//package com.example.mrprice.it226project3;
//
//import android.app.DatePickerDialog;
//import android.app.Dialog;
//
//import android.app.TimePickerDialog;
//import android.os.Bundle;
//import android.support.v4.app.DialogFragment;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentActivity;
//import android.text.format.DateFormat;
//import android.view.View;
//import android.widget.DatePicker;
//import android.widget.EditText;
//import android.widget.TimePicker;
//
//import java.lang.reflect.Field;
//import java.util.Calendar;
//
///**
// * Created by Matt on 4/26/2016.
// */
//public class TimePickerFragment extends FragmentActivity {
//
//    private static EditText timeEdit;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        timeEdit = (EditText) findViewById(R.id.enterTimeTxt);
//    }
//
//    public void showTimePickerDialog(View v) {
//        DialogFragment newFragment = new TP_Fragment();
//        newFragment.show(getSupportFragmentManager(), "timePicker");
//    }
//
//    public void showDatePickerDialog(View v) {
//        DialogFragment newFragment = new DP_Fragment();
//        newFragment.show(getSupportFragmentManager(), "datePicker");
//    }
//
//    // Custom DialogFragment to pick time
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
//
//    // Custom DialogFragment to pick date
//    public static class DP_Fragment extends DialogFragment implements
//            DatePickerDialog.OnDateSetListener {
//
//        @Override
//        public Dialog onCreateDialog(Bundle savedInstanceState) {
//            // Use the current date as the default date in the picker
//            final Calendar c = Calendar.getInstance();
//            int year = c.get(Calendar.YEAR);
//            int month = c.get(Calendar.MONTH);
//            int day = c.get(Calendar.DAY_OF_MONTH);
//
//            // Create a new instance of DatePickerDialog and return it
//            return new DatePickerDialog(getActivity(), this, year, month, day);
//        }
//
//        public void onDateSet(DatePicker view, int year, int month, int day) {
//            // Do something with the date chosen by the user
//            timeEdit.setText(day + "/" + (month + 1) + "/" + year);
//        }
//    }
//}
