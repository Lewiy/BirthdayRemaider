package com.romanenko.lew.birthdayremaider.util;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.romanenko.lew.birthdayremaider.AlarmingSystem.AlarmEntity;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    public static  void saveArrayList(List<AlarmEntity> list, String key, SharedPreferences prefs){
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(key, json);
        editor.apply();     // This line is IMPORTANT !!!
    }

    public static List<AlarmEntity> getArrayList(String key, SharedPreferences prefs){
        Gson gson = new Gson();
        String json = prefs.getString(key, null);
        Type type = new TypeToken<ArrayList<AlarmEntity>>() {}.getType();
        return gson.fromJson(json, type);
    }

    public static void clearPreference(SharedPreferences prefs){
        prefs.edit().clear();
    }

}
