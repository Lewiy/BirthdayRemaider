package com.romanenko.lew.birthdayremaider.AlarmingSystem;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;
import android.widget.Toast;

import com.romanenko.lew.birthdayremaider.Notification.CelebrNotification;
import com.romanenko.lew.birthdayremaider.Notification.NotificationChannels;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle extras = intent.getExtras();
        StringBuilder msgStr = new StringBuilder();
        String date = extras.getString("Time");
       // Toast.makeText(context, date, Toast.LENGTH_LONG).show();
        CelebrNotification.notifyCelebr(context, "Test", NotificationChannels.ANDROID_CHANNEL_ID);

    }
}
