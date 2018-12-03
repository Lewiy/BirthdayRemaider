package com.romanenko.lew.birthdayremaider.AlarmingSystem;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.romanenko.lew.birthdayremaider.Notification.DataNotifReceiver;
import com.romanenko.lew.birthdayremaider.util.DateParser;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //Bundle extras = intent.getExtras();
      //  String dateStr = extras.getString(CelebrationAlarmManager.ID_ALARM);

        DataNotifReceiver dataNotifReceiver = new DataNotifReceiver(context);


    }
}
