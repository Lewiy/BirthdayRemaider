package com.romanenko.lew.birthdayremaider.View.Fragments;

import android.app.AlarmManager;
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

import com.romanenko.lew.birthdayremaider.AlarmingSystem.CelebrAlarmManager;
import com.romanenko.lew.birthdayremaider.R;
import com.romanenko.lew.birthdayremaider.SettingsContract;
import com.romanenko.lew.birthdayremaider.util.PreferencesManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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

    private static final int INDEX_1 = 1, INDEX_2 = 2, INDEX_3 = 7;

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

    @OnClick(R.id.time_viewer_text_view)
    public void onClickTimePicker() {

        new TimePickerDialog(getContext(), R.style.DialogTheme, myCallBack, myHour, myMinute, true).show();
    }
    @OnClick(R.id.time_picker_text_view)
    public void onClickTimePickerT() {

        new TimePickerDialog(getContext(), R.style.DialogTheme, myCallBack, myHour, myMinute, true).show();
    }

    TimePickerDialog.OnTimeSetListener myCallBack = new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            myHour = hourOfDay;
            myMinute = minute;
            timeShower.setText(correctTimeForShow(myHour, myMinute));
        }
    };

    private void upLoadSettingsFirst() {

        firstSettingsSet();

        day1.setChecked(true);
        day2.setChecked(true);
        day7.setChecked(true);


        myHour = 8;
        myMinute = 00;

        timeShower.setText(correctTimeForShow(myHour, myMinute));

        //   setNewSettings();

    }

    public void setNewSettings() {

        HashMap listChekBox = new HashMap<Integer, Boolean>();
        HashMap listTime = new HashMap<Integer, Integer>();


        listChekBox.put(INDEX_1, day1.isChecked());
        listChekBox.put(INDEX_2, day2.isChecked());
        listChekBox.put(INDEX_3, day7.isChecked());


        listTime.put(INDEX_1, myHour);
        listTime.put(INDEX_2, myMinute);

        // setSettingsPref(listChekBox, listTime);
        PreferencesManager.setSettingsPref(listChekBox, listTime, mSettings);


    }


    private void loadSettings() {

        HashMap<Integer, Boolean> listChekBox = PreferencesManager.getSettingsPrefDay(mSettings);
        HashMap<Integer, Integer> listTime = PreferencesManager.getSettingsPrefTime(mSettings);

        day1.setChecked(listChekBox.get(INDEX_1));
        day2.setChecked(listChekBox.get(INDEX_2));
        day7.setChecked(listChekBox.get(INDEX_3));

        myHour = listTime.get(INDEX_1);
        myMinute = listTime.get(INDEX_2);

        timeShower.setText(correctTimeForShow(myHour, myMinute));
        // correctTimeForShow(myHour,myMinute);

    }


    private void firstSettingsSet() {
        SharedPreferences.Editor e = mSettings.edit();
        e.putBoolean("hasVisited", true);
        e.apply();
    }

    private boolean checkIfSettingsUp() {
        return mSettings.getBoolean("hasVisited", false);
    }

    private String correctTimeForShow(int myHour, int myMinute) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, myHour);
        calendar.set(Calendar.MINUTE, myMinute);
        //  System.out.println("Date : " + sdf.format(calendar.getTime()));
        return sdf.format(calendar.getTime());
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        setNewSettings();
        SharedPreferences settings = getActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        HashMap<Integer, Integer> listTime = PreferencesManager.getSettingsPrefTime(settings);
        CelebrAlarmManager celebrAlarmManager = new CelebrAlarmManager(getContext()
                , listTime.get(PreferencesManager.INDEX_1)
                , listTime.get(PreferencesManager.INDEX_2));
        celebrAlarmManager.resetAlarmRepeatingDay();
        super.onStop();
    }

    @Override
    public void showView(String error) {

    }
}
