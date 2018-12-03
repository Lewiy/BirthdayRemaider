package com.romanenko.lew.birthdayremaider.AlarmingSystem;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.romanenko.lew.birthdayremaider.util.PreferencesManager;

import java.util.HashMap;

import static com.romanenko.lew.birthdayremaider.View.Fragments.FragmentSettings.APP_PREFERENCES;

public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        SharedPreferences settings = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        HashMap<Integer, Integer> listTime = PreferencesManager.getSettingsPrefTime(settings);

        CelebrAlarmManager celebrAlarmManager2 = new CelebrAlarmManager(context
                ,listTime.get(PreferencesManager.INDEX_1)
                ,listTime.get(PreferencesManager.INDEX_2));

        celebrAlarmManager2.setAlarmDateRepeatingDay();
    }
}
