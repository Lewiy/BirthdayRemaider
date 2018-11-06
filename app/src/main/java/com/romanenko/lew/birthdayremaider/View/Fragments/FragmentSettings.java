package com.romanenko.lew.birthdayremaider.View.Fragments;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.romanenko.lew.birthdayremaider.R;
import com.romanenko.lew.birthdayremaider.SettingsContract;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FragmentSettings extends android.support.v4.app.Fragment implements SettingsContract.ViewSettings {


    @BindView(R.id.time_viewer_text_view)
    TextView timeShower;

    @BindView(R.id.day_1)
    CheckBox day1;

    @BindView(R.id.day_2)
    CheckBox day2;

    @BindView(R.id.day_7)
    CheckBox day7;

    private int myHour;
    private int myMinute;

    public static final String APP_PREFERENCES = "CelebrSettings";
    private SharedPreferences mSettings;
    public static final String APP_PREFERENCES_ALARM_DAY = "Alarm_day";
    public static final String APP_PREFERENCES_ALARM_TIME = "Alarm_time";

    public static final int INDEX_1 = 1, INDEX_2 = 2, INDEX_3 = 3;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, null);
        ButterKnife.bind(this, view);
        mSettings = getActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        if (!checkIfSettingsUp()) {
            upLoadSettingsFirst();
        } else {
            loadSettings();
        }
        return view;
    }

    @OnClick(R.id.time_picker_text_view)
    public void onClickTimePicker() {
        //Toast.makeText(getContext(), "lol", Toast.LENGTH_LONG).show();
        new TimePickerDialog(getContext(), myCallBack, myHour, myMinute, true).show();
    }

    TimePickerDialog.OnTimeSetListener myCallBack = new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            myHour = hourOfDay;
            myMinute = minute;
            timeShower.setText(myHour + ":" + myMinute);
        }
    };

    private void upLoadSettingsFirst() {

        firstSettingsSet();

        day1.setChecked(true);
        day2.setChecked(true);
        day7.setChecked(true);


        myHour = 8;
        myMinute = 00;

        timeShower.setText(myHour + ":" + myMinute);

        setNewSettings();

    }

    public void setNewSettings() {

        HashMap listChekBox = new HashMap<Integer, Boolean>();
        HashMap listTime = new HashMap<Integer, Integer>();


        listChekBox.put(INDEX_1, day1.isChecked());
        listChekBox.put(INDEX_2, day2.isChecked());
        listChekBox.put(INDEX_3, day7.isChecked());


        listTime.put(INDEX_1, myHour);
        listTime.put(INDEX_2, myMinute);

        setSettingsPref(listChekBox, listTime);
    }


    private void loadSettings() {

        HashMap<Integer, Boolean> listChekBox = getSettingsPrefDay();
        HashMap<Integer, Integer> listTime = getSettingsPrefTime();

        day1.setChecked(listChekBox.get(INDEX_1));
        day2.setChecked(listChekBox.get(INDEX_2));
        day7.setChecked(listChekBox.get(INDEX_3));

        myHour = listTime.get(INDEX_1);
        myMinute = listTime.get(INDEX_2);

        timeShower.setText(myHour + ":" + myMinute);


    }


    private void setSettingsPref(HashMap<Integer, Boolean> listChekBox, HashMap<Integer, Boolean> listTime) {

        SharedPreferences.Editor editor = mSettings.edit();

        Gson gson = new Gson();
        String jsonChekBox = gson.toJson(listChekBox);
        String jsonTime = gson.toJson(listTime);

        editor.putString(APP_PREFERENCES_ALARM_DAY, jsonChekBox);
        editor.putString(APP_PREFERENCES_ALARM_TIME, jsonTime);

        editor.apply();

    }

    private HashMap<Integer, Boolean> getSettingsPrefDay() {

        Gson gson = new Gson();

        String jsonChekBox = mSettings.getString(APP_PREFERENCES_ALARM_DAY, null);

        Type type = new TypeToken<HashMap<Integer, Boolean>>() {
        }.getType();
        return gson.fromJson(jsonChekBox, type);
    }

    private HashMap<Integer, Integer> getSettingsPrefTime() {

        Gson gson = new Gson();

        String jsonTime = mSettings.getString(APP_PREFERENCES_ALARM_TIME, null);

        Type type = new TypeToken<HashMap<Integer, Integer>>() {
        }.getType();
        return gson.fromJson(jsonTime, type);
    }

    private void firstSettingsSet() {
        SharedPreferences.Editor e = mSettings.edit();
        e.putBoolean("hasVisited", true);
        e.apply();
    }

    private boolean checkIfSettingsUp() {
        return mSettings.getBoolean("hasVisited", false);
    }


    @Override
    public void onPause() {
        setNewSettings();
        super.onPause();
    }
}
