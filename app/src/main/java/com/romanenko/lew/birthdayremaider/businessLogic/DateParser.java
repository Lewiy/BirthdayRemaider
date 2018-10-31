package com.romanenko.lew.birthdayremaider.businessLogic;

import com.romanenko.lew.birthdayremaider.AlarmingSystem.MyDate;

import java.text.ParseException;

public class DateParser {

    public static MyDate parseDate(String date){
        int day,month,year;
        String [] splitedDate = date.split("/");
        return new MyDate(Integer.parseInt(splitedDate[0])
                ,Integer.parseInt(splitedDate[1])
                ,Integer.parseInt(splitedDate[2]));
    }
}
