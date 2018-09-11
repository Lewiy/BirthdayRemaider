package com.romanenko.lew.birthdayremaider.AlarmingSystem;

import android.content.Context;

public class RemainderManager {

    private Context context;

   public  RemainderManager(Context context) {
        this.context = context;
    }

    private RemainderSetter rem = new RemainderSetter();

    public void onAlarm() {
        rem.SetAlarm(context);
    }

}
