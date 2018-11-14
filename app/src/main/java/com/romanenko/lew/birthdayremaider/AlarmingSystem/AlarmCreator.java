package com.romanenko.lew.birthdayremaider.AlarmingSystem;

import android.content.Context;
import android.content.SharedPreferences;

import com.romanenko.lew.birthdayremaider.util.PreferencesManager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import static com.romanenko.lew.birthdayremaider.View.Fragments.FragmentSettings.APP_PREFERENCES;
import static com.romanenko.lew.birthdayremaider.View.Fragments.FragmentSettings.INDEX_1;
import static com.romanenko.lew.birthdayremaider.View.Fragments.FragmentSettings.INDEX_2;
import static com.romanenko.lew.birthdayremaider.View.Fragments.FragmentSettings.INDEX_3;

public class AlarmCreator {

    private Context context;
    private SharedPreferences mSettings;
    private SharedPreferences mAlarmsPref;
    private List<AlarmEntity> mAlarms = new ArrayList();
    private List<AlarmEntity> mAlarmsResetAlarms;
    public static final String APP_PREFERENCES_ALARMS = "ALARMS";
    public static final String APP_PREFERENCE_ALARM_KEY = "ALARM_KEY";
    public static final int NUMBER_SETTING_1 = 1, NUMBER_SETTING_2 = 2, NUMBER_SETTING_9 = 9, NUMBER_SETTING_7 = 7;
    private CelebrationAlarmManager celebrationAlarmManager;

    public AlarmCreator(Context context) {
        this.context = context;
        mSettings = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        mAlarmsPref = context.getSharedPreferences(APP_PREFERENCES_ALARMS, Context.MODE_PRIVATE);
        celebrationAlarmManager = new CelebrationAlarmManager(context);
    }

    public void createAlarm(int idCelebration, MyDate date) {
        settingsFilterSetAlarm(idCelebration, date);
        setAlarmsToPref(mAlarms);
    }


    private void settingsFilterSetAlarm(int idCelebration, MyDate date) {

        HashMap<Integer, Boolean> listChekBox = PreferencesManager.getSettingsPrefDay(mSettings);
        HashMap<Integer, Integer> listTime = PreferencesManager.getSettingsPrefTime(mSettings);

        int dayAlarming, monthAlarming, yearAlarming;

        if (listChekBox.get(INDEX_1)) {

            Calendar calendar = minesDays(date.getMonth(), date.getDay(), NUMBER_SETTING_1);

            dayAlarming = calendar.get(Calendar.DAY_OF_MONTH);
            monthAlarming = calendar.get(Calendar.MONTH);
            yearAlarming = calendar.get(Calendar.YEAR);

            if (checkBeforeDate(yearAlarming, monthAlarming, dayAlarming, listTime.get(INDEX_1), listTime.get(INDEX_2))) {
                int idAlarm = makeAlarmId(idCelebration, NUMBER_SETTING_1, monthAlarming, dayAlarming);
                MyDate dateWithSettings = new MyDate(yearAlarming, monthAlarming, dayAlarming, listTime.get(INDEX_1), listTime.get(INDEX_2));
                addAlarms(idAlarm, idCelebration, dateWithSettings);
                setAlarm(dateWithSettings, idAlarm);

            }


        }

        if (listChekBox.get(INDEX_2)) {

            Calendar calendar = minesDays(date.getMonth(), date.getDay(), NUMBER_SETTING_2);

            dayAlarming = calendar.get(Calendar.DAY_OF_MONTH);
            monthAlarming = calendar.get(Calendar.MONTH);
            yearAlarming = calendar.get(Calendar.YEAR);

            if (checkBeforeDate(yearAlarming, monthAlarming, dayAlarming, listTime.get(INDEX_1), listTime.get(INDEX_2))) {
                int idAlarm = makeAlarmId(idCelebration, NUMBER_SETTING_2, monthAlarming, dayAlarming);
                MyDate dateWithSettings = new MyDate(yearAlarming, monthAlarming, dayAlarming, listTime.get(INDEX_1), listTime.get(INDEX_2));
                addAlarms(idAlarm, idCelebration, dateWithSettings);
                setAlarm(dateWithSettings, idAlarm);
            }

        }

        if (listChekBox.get(INDEX_3)) {
            Calendar calendar = minesDays(date.getMonth(), date.getDay(), NUMBER_SETTING_7);

            dayAlarming = calendar.get(Calendar.DAY_OF_MONTH);
            monthAlarming = calendar.get(Calendar.MONTH);
            yearAlarming = calendar.get(Calendar.YEAR);

            if (checkBeforeDate(yearAlarming, monthAlarming, dayAlarming, listTime.get(INDEX_1), listTime.get(INDEX_2))) {
                int idAlarm = makeAlarmId(idCelebration, NUMBER_SETTING_7, monthAlarming, dayAlarming);
                MyDate dateWithSettings = new MyDate(yearAlarming, monthAlarming, dayAlarming, listTime.get(INDEX_1), listTime.get(INDEX_2));
                addAlarms(idAlarm, idCelebration, dateWithSettings);
                setAlarm(dateWithSettings, idAlarm);
            }

        }

        int idAlarm = makeAlarmId(idCelebration, NUMBER_SETTING_9, date.getMonth(), date.getDay());
        MyDate dateWithSettings = new MyDate(date.getYear(), date.getMonth(), date.getDay(), listTime.get(INDEX_1), listTime.get(INDEX_2));
        addAlarms(idAlarm, idCelebration, dateWithSettings);
        setAlarm(dateWithSettings, idAlarm);
    }

    private void setAlarm(MyDate date, int id) {
        celebrationAlarmManager.setDateOnce(id, date);
    }

    private Calendar minesDays(int month, int day, int beforDays) {

        Calendar calendar = Calendar.getInstance();

        int currentMonth = calendar.get(Calendar.MONTH) + 1;
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        if (currentMonth <= month && currentDay <= day) {
            calendar = new GregorianCalendar(calendar.get(Calendar.YEAR), month, day);
            calendar.add(Calendar.DATE, -beforDays);
        } else {
            calendar.add(Calendar.YEAR, 1);
            calendar = new GregorianCalendar(calendar.get(Calendar.YEAR), month, day);
            calendar.add(Calendar.DATE, -beforDays);
        }


        return calendar;
    }

    private boolean checkBeforeDate(int year, int month, int day, int hour, int min) {
        GregorianCalendar dateAlarmCalendar = new GregorianCalendar(year, month - 1, day, hour, min);

        Date nowDate = new Date();
        Date dateAlarmDate = dateAlarmCalendar.getTime();

        return nowDate.before(dateAlarmDate);
    }

    private void getAlarmsFromPref() {
        mAlarmsResetAlarms = PreferencesManager.getArrayList(APP_PREFERENCE_ALARM_KEY, mAlarmsPref);
    }

    private void setAlarmsToPref(List<AlarmEntity> alarmsToPref) {
        if (alarmsToPref.size() != 0)
            PreferencesManager.saveArrayList(alarmsToPref, APP_PREFERENCE_ALARM_KEY, mAlarmsPref);
    }


    private void restoreAlarmsRebooting() {
        getAlarmsFromPref();
        for (AlarmEntity alarmEntity : mAlarmsResetAlarms) {
            settingsFilterSetAlarm(alarmEntity.getId(), alarmEntity.getDate());
        }
    }

    public void resetAlarmsSettings() {
        getAlarmsFromPref();
        mAlarms.clear();
        if (mAlarmsResetAlarms != null) {
            for (AlarmEntity alarmEntity : mAlarmsResetAlarms) {
                celebrationAlarmManager.deleteDate(alarmEntity.getId(), alarmEntity.getDate());
            }

            PreferencesManager.clearPreference(mAlarmsPref);

            for (AlarmEntity alarmEntity : mAlarmsResetAlarms) {
                if (isMainAlarm(alarmEntity.getId()))
                    settingsFilterSetAlarm(alarmEntity.getIdGroup(), alarmEntity.getDate());
            }

            setAlarmsToPref(mAlarms);
        }
    }

    public void deleteAccaunt(int id) {
        getAlarmsFromPref();
        for (AlarmEntity alarmEntity : mAlarmsResetAlarms) {
            if(id == alarmEntity.getIdGroup())
            celebrationAlarmManager.deleteDate(alarmEntity.getId(), alarmEntity.getDate());
        }

        for (AlarmEntity alarmEntity : mAlarmsResetAlarms) {
            if(id == alarmEntity.getIdGroup())
                mAlarmsResetAlarms.remove(alarmEntity);
        }

        setAlarmsToPref(mAlarmsResetAlarms);
    }


    private boolean isMainAlarm(int id) {
        String number = String.valueOf(id);
        char[] digits1 = number.toCharArray();
        int i = Integer.parseInt(String.valueOf(digits1[0]));
        if (i == NUMBER_SETTING_9) {
            return true;
        } else return false;
    }

    private void addAlarms(int id, int idGroup, MyDate date) {
        mAlarms.add(new AlarmEntity(id, idGroup, date));
    }


    private int makeAlarmId(int idCelebration, int NumberSetting, int month, int day) {
        return Integer.valueOf(String.valueOf(idCelebration)
                + String.valueOf(NumberSetting)
                + String.valueOf(month)
                + String.valueOf(day));
    }
}
