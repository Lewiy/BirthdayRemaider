package com.romanenko.lew.birthdayremaider.Notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;

import com.romanenko.lew.birthdayremaider.R;

import static android.content.Context.NOTIFICATION_SERVICE;

public class CelebrNotification {

    public static void notifyCelebr(Context context, String subject, String channel) {

        Notification notification = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            notification = new Notification.Builder(context, channel)
                    .setContentTitle("New mail from " + channel)
                    .setContentText(subject)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .build();
        } else {
            NotificationCompat.Builder builder =
                    new NotificationCompat.Builder(context)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setContentTitle("Title")
                            .setContentText("Notification text");

            notification = builder.build();
        }

        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification);


    }
}
