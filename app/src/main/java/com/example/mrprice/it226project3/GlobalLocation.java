package com.example.mrprice.it226project3;

import android.Manifest;
import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.location.LocationListener;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

/**
 * Created by Kevin Smithsonian on 4/26/2016.
 * this is essetnailly a location listener class
 */
public class GlobalLocation extends BaseActivity implements LocationListener{

    @Override
    public void onLocationChanged(Location location)
    {
        if(location != null)
        {
            MainActivity.currentLocation = location;
            Log.e("Lat: ", " " + location.getLatitude());
            Log.e("Long: ", " " + location.getLongitude());
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

