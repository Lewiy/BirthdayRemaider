package com.romanenko.lew.birthdayremaider.Notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

import com.romanenko.lew.birthdayremaider.AlarmingSystem.IalarmManager;
import com.romanenko.lew.birthdayremaider.EditProfileCelebration;
import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.QueryObjects.NotifyDTO;
import com.romanenko.lew.birthdayremaider.R;
import com.romanenko.lew.birthdayremaider.View.Activities.MainActivity;

import java.io.File;
import java.util.List;

import static android.content.Context.ALARM_SERVICE;
import static android.content.Context.NOTIFICATION_SERVICE;

public class CelebrNotificationManager {

    public static void notifyCelebr(Context context, String subject, String channel, List<NotifyDTO> notifDatas) {
        int idNotif = 0;

        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);


        for (NotifyDTO notif : notifDatas) {
            idNotif++;

            Bitmap birthdayGirlImage = setPhoto(notif, context);

            Notification notification = null;


            Bundle bundle = new Bundle();
            bundle.putInt("idUser", (int) notif.userId);
            bundle.putString("FragmentType", "EditFragment");


            Intent resultIntent = new Intent(context, MainActivity.class);
            //  resultIntent.putExtra("idUser",notif.userId);
            //  resultIntent.putExtra("FragmentType","EditFragment");

            resultIntent.putExtras(bundle);

            PendingIntent resultPendingIntent = PendingIntent.getActivity(context, 0, resultIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT);

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                notification = new Notification.Builder(context, channel)
                        .setContentTitle(notif.typeCelebr)
                        .setLargeIcon(birthdayGirlImage)
                        .setContentText(notif.firstName + " " + notif.lastName)
                        .setSmallIcon(R.drawable.ic_cake_black_24dp)
                        .setContentIntent(resultPendingIntent)
                        .build();

                NotificationChannel mChannel = new NotificationChannel(NotificationChannels.ANDROID_CHANNEL_ID, "ssddf", NotificationManager.IMPORTANCE_HIGH);
                notificationManager.createNotificationChannel(mChannel);
            } else {
                NotificationCompat.Builder builder =
                        new NotificationCompat.Builder(context, NotificationChannels.ANDROID_CHANNEL_ID)
                                .setSmallIcon(R.drawable.ic_cake_black_24dp)
                                .setLargeIcon(birthdayGirlImage)
                                .setContentTitle(notif.typeCelebr)
                                .setContentText(notif.firstName + notif.lastName)
                                .setContentIntent(resultPendingIntent);

                notification = builder.build();
            }
            notificationManager.notify(idNotif, notification);
        }
    }


    private static Bitmap setPhoto(NotifyDTO notif, Context context) {
        Bitmap birthdayGirlImage;

        if (notif.fotoPath != null) {
            File image = new File(notif.fotoPath);
             birthdayGirlImage = BitmapFactory.decodeFile(image.getAbsolutePath());
        } else
            birthdayGirlImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.balloonred);
        return birthdayGirlImage;
    }
}
