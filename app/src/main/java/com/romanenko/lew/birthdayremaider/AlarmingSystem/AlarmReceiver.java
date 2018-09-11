package com.romanenko.lew.birthdayremaider.AlarmingSystem;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
       /* PowerManager pm=(PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl= pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,"YOUR TAG");
//Осуществляем блокировку
        wl.acquire();

//Здесь можно делать обработку.


        if(extras!=null&amp;&amp; extras.getBoolean(ONE_TIME, Boolean.FALSE)){
//проверяем параметр ONE_TIME, если это одиночный будильник,
//выводим соответствующее сообщение.
            msgStr.append("Одноразовый будильник: ");
        }
        Format formatter=new SimpleDateFormat("hh:mm:ss a");
        msgStr.append(formatter.format(newDate()));



//Разблокируем поток.
        wl.release();*/
        Bundle extras= intent.getExtras();
        StringBuilder msgStr=new StringBuilder();
        Toast.makeText(context, msgStr, Toast.LENGTH_LONG).show();

     /*   PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "");
        wl.acquire();

        // Put here YOUR code.
        Toast.makeText(context, "Alarm!!!!!!!!!!", Toast.LENGTH_LONG).show(); // For example

        wl.release();*/

    }
}
