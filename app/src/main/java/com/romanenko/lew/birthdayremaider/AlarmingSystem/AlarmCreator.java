package com.romanenko.lew.birthdayremaider.AlarmingSystem;

import android.content.Context;
import android.content.SharedPreferences;

import com.romanenko.lew.birthdayremaider.Model.DTO.DateCelebrationVO;
import com.romanenko.lew.birthdayremaider.util.PreferencesManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

import static com.romanenko.lew.birthdayremaider.View.Fragments.FragmentSettings.APP_PREFERENCES;
import static com.romanenko.lew.birthdayremaider.View.Fragments.FragmentSettings.INDEX_1;
import static com.romanenko.lew.birthdayremaider.View.Fragments.FragmentSettings.INDEX_2;
import static com.romanenko.lew.birthdayremaider.View.Fragments.FragmentSettings.INDEX_3;

public class AlarmCreator {

    private Context context;
    private SharedPreferences mSettings;

    public AlarmCreator(Context context) {
        this.context = context;
        mSettings = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
    }

    public void createAlarm(DateCelebrationVO dateCelebrationVO) {
        settingsFilter(dateCelebrationVO.getYear(), dateCelebrationVO.getMonth(), dateCelebrationVO.getDay());
    }


    private void settingsFilter(int year, int month, int day) {

        HashMap<Integer, Boolean> listChekBox = PreferencesManager.getSettingsPrefDay(mSettings);
        HashMap<Integer, Integer> listTime = PreferencesManager.getSettingsPrefTime(mSettings);

        int dayAlarming, monthAlarming, yearAlarming;

        if (listChekBox.get(INDEX_1)) {

            Calendar calendar = minesDays(year, month, day, 1);
            dayAlarming = calendar.get(Calendar.DAY_OF_MONTH);
            monthAlarming = calendar.get(Calendar.MONTH);
            yearAlarming = calendar.get(Calendar.YEAR);

            if (checkBeforeDate(yearAlarming, monthAlarming, dayAlarming, listTime.get(INDEX_1), listTime.get(INDEX_2)))
                setAlarm(yearAlarming, monthAlarming, dayAlarming, listTime.get(INDEX_1), listTime.get(INDEX_2));

        }

        if (listChekBox.get(INDEX_2)) {
            Calendar calendar = minesDays(year, month, day, 2);

            dayAlarming = calendar.get(Calendar.DAY_OF_MONTH);
            monthAlarming = calendar.get(Calendar.MONTH);
            yearAlarming = calendar.get(Calendar.YEAR);

            if (checkBeforeDate(yearAlarming, monthAlarming, dayAlarming, listTime.get(INDEX_1), listTime.get(INDEX_2)))
                setAlarm(yearAlarming, monthAlarming, dayAlarming, listTime.get(INDEX_1), listTime.get(INDEX_2));
        }

        if (listChekBox.get(INDEX_3)) {
            Calendar calendar = minesDays(year, month, day, 7);

            dayAlarming = calendar.get(Calendar.DAY_OF_MONTH);
            monthAlarming = calendar.get(Calendar.MONTH);
            yearAlarming = calendar.get(Calendar.YEAR);

            if (checkBeforeDate(yearAlarming, monthAlarming, dayAlarming, listTime.get(INDEX_1), listTime.get(INDEX_2)))
                setAlarm(yearAlarming, monthAlarming, dayAlarming, listTime.get(INDEX_1), listTime.get(INDEX_2));
        }

        setAlarm(year, month, day, listTime.get(INDEX_1), listTime.get(INDEX_2));

    }


    private void setAlarm(int year, int month, int day, int mHour, int mMin) {
        CelebrationAlarmManager celebrationAlarmManager = new CelebrationAlarmManager(context);
        MyDate myDate = new MyDate(year, month, day, mHour, mMin);
        celebrationAlarmManager.setDateOnce(myDate);
    }

    private Calendar minesDays(int year, int month, int day, int beforDays) {

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

}
