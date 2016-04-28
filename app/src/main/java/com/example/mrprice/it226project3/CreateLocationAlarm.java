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
import android.util.Log;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

public class CreateLocationAlarm extends AppCompatActivity implements LocationListener {

    private LocationManager lm;
    private Location currentLocation;
    LocationListener locationListener;
    String locationProvider = LocationManager.GPS_PROVIDER;
    Context context = this;
    Intent intent;
    PendingIntent pendingIntent;
    AlarmManager alarmManager;
    String message = "Get up and walk!";
    double longitude=0;
    double latitude=0;
    String stringLocation;
    boolean buttonPressed = false;
    long timeLimit;//this will hold the length of time the alarm will be sent for
    NumberPicker np;
    TextView tv1, tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_location_alarm);

//        defaultAlarmBut = (Button) findViewById(R.id.defaultAlarmBut);
//        locationAlarmBut = (Button) findViewById(R.id.locationAlarmBut);

        //we need the next line or the sdk will not let us check gps location.
        //this is not legitimately checking for permission. but it works.
        int status = this.getPackageManager().checkPermission(Manifest.permission.ACCESS_FINE_LOCATION,
                this.getPackageName());

        //moved declaration of location manager "lm" to the top of the class
        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, this);
        alarmManager = (AlarmManager) this.getSystemService(this.ALARM_SERVICE);

        //===================================================================
        np = (NumberPicker) findViewById(R.id.numberPicker1);
        tv1 = (TextView) findViewById(R.id.textView1);
        tv2 = (TextView) findViewById(R.id.textView3);

        np.setMinValue(0);
        np.setMaxValue(10);
        np.setWrapSelectorWheel(false);

        np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                // TODO Auto-generated method stub

                String Old = "Old Value : ";
                String New = "New Value : ";

                tv1.setText(Old.concat(String.valueOf(oldVal)));
                tv2.setText(New.concat(String.valueOf(newVal)));
            }
        });
        //===================================================================
    }

    public void createDefaultAlarm(View v)
    {
        timeLimit = 120000;//this should be two minutes
        int status = this.getPackageManager().checkPermission(Manifest.permission.ACCESS_COARSE_LOCATION,
                this.getPackageName());

        //where you see lm, i had a different location manager called manager
        if(lm != null)
            currentLocation = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if(currentLocation != null) {
            longitude = currentLocation.getLongitude();
            latitude = currentLocation.getLatitude();
        }

        buttonPressed = true;

        intent = new Intent(this, AlarmReceiver.class);

        stringLocation = "Latitude: " + latitude + "\nLongitude: " + longitude;
        intent.putExtra("msg", message);
        intent.putExtra("location",stringLocation);

        pendingIntent = PendingIntent.getBroadcast(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + timeLimit,
                pendingIntent);

        //Will give button press confirmation
        Toast.makeText(getApplicationContext(), "Location Alarm Set", Toast.LENGTH_LONG).show();

    }

    public void createLocationAlarm(View v)
    {
        //get info from
        int minutes = np.getValue();
        timeLimit = (long) minutes;
        //i need to convert from minutes to milliseconds
        timeLimit = timeLimit * 60000;
        buttonPressed = true;

        //set alarm
        int status = this.getPackageManager().checkPermission(Manifest.permission.ACCESS_COARSE_LOCATION,
                this.getPackageName());

        //where you see lm, i had a different location manager called manager
        if(lm != null)
            currentLocation = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if(currentLocation != null) {
            longitude = currentLocation.getLongitude();
            latitude = currentLocation.getLatitude();
        }


        intent = new Intent(this, AlarmReceiver.class);

        stringLocation = "Latitude: " + latitude + "\nLongitude: " + longitude;
        intent.putExtra("msg", message);
        intent.putExtra("location",stringLocation);
        // pendingIntent = PendingIntent.getBroadcast(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + timeLimit,
                PendingIntent.getBroadcast(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT) );

        //Will give button press confirmation
        Toast.makeText(getApplicationContext(), "Location Alarm Set", Toast.LENGTH_LONG).show();
    }


    @Override
    public void onLocationChanged(Location location)
    {

        if(location != null)
        {
            Log.e("Latitude: ", " " + location.getLatitude());
            Log.e("Longitude: ", " " + location.getLongitude());

        }
        if(buttonPressed)
        {
            alarmManager.cancel(pendingIntent);
            pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
            alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + timeLimit,
                    PendingIntent.getBroadcast(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT) );
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
