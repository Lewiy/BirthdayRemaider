package com.romanenko.lew.birthdayremaider.util;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;

import static com.romanenko.lew.birthdayremaider.View.Fragments.FragmentSettings.APP_PREFERENCES_ALARM_DAY;
import static com.romanenko.lew.birthdayremaider.View.Fragments.FragmentSettings.APP_PREFERENCES_ALARM_TIME;

public class PreferencesManager {


    public static HashMap<Integer, Boolean> getSettingsPrefDay(SharedPreferences mSettings) {

        Gson gson = new Gson();

        String jsonChekBox = mSettings.getString(APP_PREFERENCES_ALARM_DAY, null);

        Type type = new TypeToken<HashMap<Integer, Boolean>>() {
        }.getType();
        return gson.fromJson(jsonChekBox, type);
    }

    public static HashMap<Integer, Integer> getSettingsPrefTime(SharedPreferences mSettings) {

        Gson gson = new Gson();

        String jsonTime = mSettings.getString(APP_PREFERENCES_ALARM_TIME, null);

        Type type = new TypeToken<HashMap<Integer, Integer>>() {
        }.getType();
        return gson.fromJson(jsonTime, type);
    }
}
