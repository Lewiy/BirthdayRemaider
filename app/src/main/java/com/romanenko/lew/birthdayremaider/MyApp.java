package com.romanenko.lew.birthdayremaider;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.romanenko.lew.birthdayremaider.AlarmingSystem.CelebrAlarmManager;
import com.romanenko.lew.birthdayremaider.DISystem.Components.AppComponent;
import com.romanenko.lew.birthdayremaider.DISystem.Components.DaggerAppComponent;
import com.romanenko.lew.birthdayremaider.DISystem.Modules.AppModule;
import com.romanenko.lew.birthdayremaider.DISystem.Modules.ContextModule;
import com.romanenko.lew.birthdayremaider.util.PreferencesManager;

import java.util.HashMap;

import static com.romanenko.lew.birthdayremaider.View.Fragments.FragmentSettings.APP_PREFERENCES;

public class MyApp extends Application {


    private AppComponent component;

    public static MyApp get(Context context) {
        return (MyApp) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .contextModule(new ContextModule(this))
                .build();
        component();

       /* CelebrAlarmManager alarmManager2 = new CelebrAlarmManager(this);
        alarmManager2.cancelAlarm();
        alarmManager2.setAlarmDateRepeatingDay();

        SharedPreferences settings = this.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        PreferencesManager.setDefaultSettings(settings);*/


        SharedPreferences settings = this.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        if (!PreferencesManager.isSetsAlarmSetUp(settings)) {
            PreferencesManager.setDefaultSettings(settings);

            HashMap<Integer, Integer> listTime = PreferencesManager.getSettingsPrefTime(settings);

            CelebrAlarmManager celebrAlarmManager2 = new CelebrAlarmManager(this
                    ,listTime.get(PreferencesManager.INDEX_1)
                    ,listTime.get(PreferencesManager.INDEX_2));

            PreferencesManager.setFlagSetUpAlarm(settings);

            celebrAlarmManager2.setAlarmDateRepeatingDay();
        }
    }

    public AppComponent component() {
        return component;
    }
}
