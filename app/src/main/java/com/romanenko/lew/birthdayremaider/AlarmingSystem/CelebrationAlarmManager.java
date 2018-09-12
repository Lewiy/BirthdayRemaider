package com.romanenko.lew.birthdayremaider.AlarmingSystem;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CelebrationAlarmManager implements IalarmManager {

    private Context context;
    final public static String ONE_TIME = "onetime";
    private android.app.AlarmManager am;


    public CelebrationAlarmManager(Context context) {
        this.context = context;
        am = (android.app.AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
    }


    public void onAlarm() {

    }


    @Override
    public void setDateOnce(MyDate date) {
        pendingBilder();
        am.set(android.app.AlarmManager.RTC_WAKEUP,setCalendar(date).getTimeInMillis(),pendingBilder());
    }

    @Override
    public void setDateRepiting(MyDate date) {
       // am.setRepeating(android.app.AlarmManager.RTC_WAKEUP, setCalendar(date).getTimeInMillis(),, pendingBilder());

    }

    @Override
    public void deleteDate(String date) {
        Intent intent = new Intent(context, AlarmReceiver.class);
        PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);
        android.app.AlarmManager alarmManager = (android.app.AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(sender);//Отменяем будильник, связанный с интентом данного класса
    }

    private PendingIntent pendingBilder() {
        Intent i = new Intent(context, AlarmReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, i, 0);
        return pi;
    }


    public Calendar setCalendar(MyDate myDate) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        calendar.clear();

       // calendar.setTime(myDate.getDate());
        calendar.set(2018,8,11,21,27);

        Log.e("tiiiiiiiiiiiiiime",calendar.getTime().toString());
        //Log.e("time 2",System.cu)
        return calendar;
    }

}
