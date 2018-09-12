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

        Bundle extras= intent.getExtras();
        StringBuilder msgStr=new StringBuilder();
        Toast.makeText(context, msgStr, Toast.LENGTH_LONG).show();

    }
}
