package com.romanenko.lew.birthdayremaider;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.romanenko.lew.birthdayremaider.View.Activities.MainActivity;

/**
 * Created by Lev- on 20.03.2018.
 */

public class MainClass extends Activity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
