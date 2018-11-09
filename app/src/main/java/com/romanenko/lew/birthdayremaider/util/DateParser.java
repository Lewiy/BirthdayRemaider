package com.romanenko.lew.birthdayremaider.util;

import com.romanenko.lew.birthdayremaider.AlarmingSystem.MyDate;

public class DateParser {

    public static MyDate parseDate(String date){
        int day,month,year;
        String [] splitedDate = date.split("/");
        return new MyDate(Integer.parseInt(splitedDate[0])
                ,Integer.parseInt(splitedDate[1])
                ,Integer.parseInt(splitedDate[2]));
    }
}
