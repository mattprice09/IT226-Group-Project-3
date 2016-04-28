package com.example.mrprice.it226project3;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by Matt on 4/26/2016.
 */
public class BaseActivity extends AppCompatActivity implements LocationListener{

    LocationManager lm;
    Location currentLocation;

    public void updateLocation() {
        int status = this.getPackageManager().checkPermission(Manifest.permission.ACCESS_FINE_LOCATION,
                this.getPackageName());

        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, this);
        if(lm != null)
            currentLocation = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
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

    /**
     * Create an alarm from the input data string.
     */
    public void CreateAlarm(String dataStr, String Delimiter) {
        String [] parts = dataStr.split(Delimiter);
        String msg = parts[0]; // the message
        String date = parts[1]; // MM/dd/YYYY
        String time = parts[2]; // hh:mm:ss
        String timeZone = parts[3]; // xST
        String recurStr = parts[4]; // "Yes" or "No"
        ArrayList<Integer> dateParts = getParts(date, "/");
        ArrayList<Integer> timeParts = getParts(time, ":");

        // Get location from MainActivity
//        Location loc = MainActivity.currentLocation;

        //where you see lm, i had a different location manager called manager
        updateLocation();
        String location = "Latitude: " + currentLocation.getLatitude() + "\nLongitude: " + currentLocation.getLongitude();

        Calendar cal = Calendar.getInstance();

        // Set time zone
        if (timeZone.trim().length() == 0) {
            cal.setTimeZone(TimeZone.getDefault());
        } else {
            cal.setTimeZone(TimeZone.getTimeZone(timeZone));
        }

        // Set date + time
        cal.set(dateParts.get(2), dateParts.get(0), dateParts.get(1), timeParts.get(0), timeParts.get(1), timeParts.get(2));

        // Create alarm with AlarmManager that calls upon a custom BroadCastReceiver to display a notification
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmReceiver.class);
        intent.putExtra("msg", msg);
        intent.putExtra("location", location);

        PendingIntent penIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

        if (recurStr.equals("Yes")) {
            // recurring
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), cal.get(Calendar.DAY_OF_WEEK), penIntent);
        } else {
            // one-time
            alarmManager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), penIntent);
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        if(location != null) {
            currentLocation = location;
            Log.e("Latitude: ", " " + location.getLatitude());
            Log.e("Longitude: ", " " + location.getLongitude());
        }
    }

    @Override
    public void onProviderEnabled(String provider)
    {

    }

    @Override
    public void onProviderDisabled(String provider)
    {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras)
    {

    }
}
