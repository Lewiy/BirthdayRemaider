package com.romanenko.lew.birthdayremaider.AlarmingSystem;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Calendar;

public class CelebrationAlarmManager implements IalarmManager {

    public static final String ID_ALARM = "ID_ALARM";
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
    public void setDateOnce(int id, MyDate date) {
        // pendingBuilder(date.toString());
        //long ml = setCalendar(date).getTimeInMillis();
        am.set(android.app.AlarmManager.RTC_WAKEUP, setCalendar(date).getTimeInMillis(), pendingBuilder(id, date.toString()));
        ///    setAndAllowWhileIdle
    }

    @Override
    public void setDateRepeating(int id, MyDate date) {
        am.setRepeating(android.app.AlarmManager.RTC_WAKEUP, setCalendar(date).getTimeInMillis(), 10000, pendingBuilder(id, date.toString()));

    }

    @Override
    public void setDateRepeatingHalfDay(int id, MyDate date) {
        am.setRepeating(android.app.AlarmManager.RTC_WAKEUP, setCalendar(date).getTimeInMillis(), AlarmManager.INTERVAL_HALF_DAY, pendingBuilder(id, date.toString()));
    }

    @Override
    public void setDateRepeatingHour(int id, MyDate date) {
        am.setRepeating(android.app.AlarmManager.RTC_WAKEUP, setCalendar(date).getTimeInMillis(), AlarmManager.INTERVAL_HOUR, pendingBuilder(id, date.toString()));
    }

    @Override
    public void setDateRepeatingDay(int id, MyDate date) {
        am.setRepeating(android.app.AlarmManager.RTC_WAKEUP, setCalendar(date).getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingBuilder(id, date.toString()));
    }

    @Override
    public void deleteDate(int id, MyDate date) {
       /* Intent intent = new Intent(context, AlarmReceiver.class);
        PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);
        android.app.AlarmManager alarmManager = (android.app.AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(sender);//Отменяем будильник, связанный с интентом данного класса*/
        am.cancel(pendingBuilder(id, date.toString()));
    }

    @Override
    public void editDateAlarm(int type, int id, MyDate date) {
        am.cancel(pendingBuilder(id,date.toString()));
        if(type == -1)
            setDateOnce(id,date);
        else
            am.setRepeating(android.app.AlarmManager.RTC_WAKEUP, setCalendar(date).getTimeInMillis(), type, pendingBuilder(id, date.toString()));
    }


    private PendingIntent pendingBuilder(int id, String time) {
        Intent i = new Intent(context, AlarmReceiver.class);
        i.putExtra(ID_ALARM,  time);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, i, 0);
        return pi;
    }


    public Calendar setCalendar(MyDate myDate) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        calendar.clear();

        calendar.setTime(myDate.getDateTime());
        // calendar.set(2018,9,17,20,22);
        Log.e("tiiiiiiiiiiiiiime", calendar.getTime().toString());
        return calendar;
    }

}
