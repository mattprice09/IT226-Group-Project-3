package com.example.mrprice.it226project3;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

public class AlarmReceiver extends BroadcastReceiver {
    public AlarmReceiver() {
    }

    @Override
    public void onReceive(Context context, 	Intent intent) {
        showNotification(context, intent);
    }

    /**
     * Show notification from input alarm.
     */
    private void showNotification(Context context, Intent intent) {

        // Get data from alarm
        String msg = intent.getStringExtra("msg");
        String location = intent.getStringExtra("location");
        String content = msg + "\nYou were here when you set this alarm: " + location;

        // Create notification
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context).setSmallIcon(R.mipmap.ic_launcher).setContentTitle(msg).setContentText(content);
        // bring user back to Alarm App if they click OK
        Intent newIntent = new Intent(context, MainActivity.class);
        PendingIntent pending = PendingIntent.getActivity(context, 0, new Intent(context, MainActivity.class), 0);
        mBuilder.setContentIntent(pending);

        mBuilder.setDefaults(Notification.DEFAULT_SOUND);
        mBuilder.setAutoCancel(true);
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(1, mBuilder.build());
    }
}
