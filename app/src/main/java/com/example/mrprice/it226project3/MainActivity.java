package com.example.mrprice.it226project3;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

public class MainActivity extends BaseActivity implements OnConnectionFailedListener, LocationListener {
//public class MainActivity extends BaseActivity {
    // additional implements ---> GoogleApiClient.ConnectionCallbacks

    public static Location currentLocation;
    LocationRequest locationRequest;
    private final int intervalMins = 30;

    public void setOneTime(View view) {
        //send user to one time alarm screen
        Intent intent = new Intent(this, CreateOneTimeAlarm.class);
        startActivity(intent);
    }

    public void setLocationAlarm(View view) {
        Intent intent = new Intent(this, CreateLocationAlarm.class);
        startActivity(intent);
    }

    public void setTimer(View view) {
        //send user to timer screen
        Intent intent = new Intent(this, CreateTimer.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationRequest = new LocationRequest();
        locationRequest.setInterval(intervalMins * 60000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setFastestInterval(intervalMins * 60000);
    }

    /**
     * onStart and onStop overriden in order to connect to Location Client
     */
//    @Override
//    protected void onStart() {
//        super.onStart();
////        gClient.connect();
//    }
//    @Override
//    protected void onStop() {
//        super.onStop();

//        LocationServices.FusedLocationApi.removeLocationUpdates(gClient, this);
//        gClient.disconnect();
//    }

    // necessary for LocationListener interface
    @Override
    public void onLocationChanged(Location location) {
        currentLocation = location;

//         ########################################## RESET the location alarm here
    }

//     necessary for OnConnectionFailedListener interface
    @Override
    public void onConnectionFailed(ConnectionResult conRes) {}

}
