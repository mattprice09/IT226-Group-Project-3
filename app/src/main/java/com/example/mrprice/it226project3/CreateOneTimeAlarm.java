package com.example.mrprice.it226project3;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;

import java.util.Calendar;

public class CreateOneTimeAlarm extends BaseActivity {

    private static final String DLM = "QWERT";

    private void alertTest(String title, String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(CreateOneTimeAlarm.this).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    // This method is called when user clicks "Next" to continue=
    public void buttonSubmit(View view) {

        // Handle user Message input
        String messageText = ((EditText) findViewById(R.id.edit_message)).toString();
        alertTest("Message", messageText);

        // Handle user Date input
        DatePicker datePicker;
        datePicker = (DatePicker) findViewById(R.id.calendarViewOneTime);
        int month = datePicker.getMonth();
        int day = datePicker.getDayOfMonth();
        int year = datePicker.getYear();
        String date = month + "/" + day + "/" + year;
        alertTest("Date", date);

        // Handle user Time input
        String time = ((EditText) findViewById(R.id.enterTimeTxt)).toString();
        alertTest("Time", time);

        // Handle user Time Zone input
        String timeZone = ((EditText) findViewById(R.id.enterTimeTxt)).toString();
        alertTest("Time Zone", timeZone);

        // Handle recurrence user input
        RadioGroup rGroup = (RadioGroup) findViewById(R.id.recurOptions);
        int selectedID = rGroup.getCheckedRadioButtonId();
        RadioButton recurChoice = (RadioButton) findViewById(selectedID);
        String recurTxt = recurChoice.getText().toString();
        alertTest("Recurrence choice", recurTxt);

        // pack user input
        String data = messageText + DLM + date + DLM + time + DLM + timeZone + DLM + recurTxt;

        // super method
        this.CreateAlarm(data, DLM);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_one_time_alarm);
    }
}
