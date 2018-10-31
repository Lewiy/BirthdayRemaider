package com.romanenko.lew.birthdayremaider.AlarmingSystem;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

import com.romanenko.lew.birthdayremaider.Notification.CelebrNotificationManager;
import com.romanenko.lew.birthdayremaider.Notification.DataNotifReceiver;
import com.romanenko.lew.birthdayremaider.Notification.NotificationChannels;
import com.romanenko.lew.birthdayremaider.R;
import com.romanenko.lew.birthdayremaider.businessLogic.DateParser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.content.Context.NOTIFICATION_SERVICE;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        String dateStr = extras.getString(CelebrationAlarmManager.ID_ALARM);

        DataNotifReceiver dataNotifReceiver = new DataNotifReceiver(context, DateParser.parseDate(dateStr));
       // Toast.makeText(context, date, Toast.LENGTH_LONG).show();
       // 0 Time2018 10 26 15 40
       /* NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context,"com.remainder.romanenko")
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Title")
                        .setContentText("Notification text");

        Notification notification = builder.build();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager notificationManager =
                    (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
            NotificationChannel mChannel = new NotificationChannel("com.remainder.romanenko","ssddf",NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(mChannel);
            notificationManager.notify(1, notification);
        }*/
    }
}
