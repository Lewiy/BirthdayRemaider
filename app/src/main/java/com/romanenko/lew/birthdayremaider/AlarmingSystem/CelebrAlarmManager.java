package com.romanenko.lew.birthdayremaider.AlarmingSystem;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.LocalDate;

public class CelebrAlarmManager {
    private android.app.AlarmManager am;
    private Context context;
    private static final int CELEBRATION_ALARM_CODE = 0;
    private int mHourAlarm,mMinuteAlarm;

    public CelebrAlarmManager(Context context, int hour, int minutes) {
        this.context = context;
        am = (android.app.AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        mHourAlarm = hour;
        mMinuteAlarm = minutes;
    }

    public void setAlarmDateRepeatingDay() {
        am.setRepeating(android.app.AlarmManager.RTC_WAKEUP, setTimeAlarming(), android.app.AlarmManager.INTERVAL_DAY, pendingBuilder(CELEBRATION_ALARM_CODE));
    }

    public void resetAlarmRepeatingDay() {
        am.cancel(pendingBuilder(CELEBRATION_ALARM_CODE));
        am.setRepeating(android.app.AlarmManager.RTC_WAKEUP, setTimeAlarming(), android.app.AlarmManager.INTERVAL_DAY, pendingBuilder(CELEBRATION_ALARM_CODE));
    }

    private long setTimeAlarming(){
        DateTime dateTime = new DateTime();
        dateTime = dateTime.plusDays(1);
        dateTime = dateTime.hourOfDay().setCopy(mHourAlarm);
        dateTime = dateTime.minuteOfHour().setCopy(mMinuteAlarm);
      return   dateTime.getMillis();
    }

    private PendingIntent pendingBuilder(int id) {
        Intent i = new Intent(context, AlarmReceiver.class);
        //  i.putExtra(ID_ALARM, time);
        PendingIntent pi = PendingIntent.getBroadcast(context, id, i, 0);
        return pi;
    }
}
