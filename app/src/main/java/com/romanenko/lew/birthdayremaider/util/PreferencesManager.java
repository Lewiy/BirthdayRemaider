package com.romanenko.lew.birthdayremaider.util;

import android.content.Context;
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

    public static final int INDEX_1 = 1, INDEX_2 = 2, INDEX_3 = 7;


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

   /* public static  void saveArrayList(List<AlarmEntity> list, String key, SharedPreferences prefs){
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(key, json);
        editor.apply();
    }

    public static List<AlarmEntity> getArrayList(String key, SharedPreferences prefs){
        Gson gson = new Gson();
        String json = prefs.getString(key, null);
        Type type = new TypeToken<ArrayList<AlarmEntity>>() {}.getType();
        return gson.fromJson(json, type);
    }*/

    public static void clearPreference(SharedPreferences prefs){
        prefs.edit().clear().apply();
    }

    public static boolean isSetsAlarmSetUp(SharedPreferences mSettings) {
        return mSettings.getBoolean("hasAlarmSet", false);
    }

    public static void setFlagSetUpAlarm(SharedPreferences mSettings) {
        SharedPreferences.Editor e = mSettings.edit();
        e.putBoolean("hasAlarmSet", true);
        e.apply();
    }

    public static void setDefaultSettings(SharedPreferences mSettings){


            HashMap listChekBox = new HashMap<Integer, Boolean>();
            HashMap listTime = new HashMap<Integer, Integer>();


            listChekBox.put(INDEX_1, true);
            listChekBox.put(INDEX_2, true);
            listChekBox.put(INDEX_3, true);


            listTime.put(INDEX_1, 8);
            listTime.put(INDEX_2, 0);

            setSettingsPref(listChekBox, listTime, mSettings);


    }

    public static void setSettingsPref(HashMap<Integer, Boolean> listChekBox, HashMap<Integer, Boolean> listTime,SharedPreferences mSettings) {

        SharedPreferences.Editor editor = mSettings.edit();

        Gson gson = new Gson();
        String jsonChekBox = gson.toJson(listChekBox);
        String jsonTime = gson.toJson(listTime);

        editor.putString(APP_PREFERENCES_ALARM_DAY, jsonChekBox);
        editor.putString(APP_PREFERENCES_ALARM_TIME, jsonTime);

        editor.apply();

    }

}
