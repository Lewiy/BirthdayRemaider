package com.romanenko.lew.birthdayremaider.AlarmingSystem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyDate {

    private int year;
    private int month;
    private int day;
    private int hrs = 0;
    private int min = 0;

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public MyDate(int year, int month, int day, int hrs, int min) {

        this.year = year;
        this.month = month;
        this.day = day;
        this.hrs = hrs;
        this.min = min;

    }

    public MyDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public Date getDateTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        String str = new String(day + "/" + month + "/" + year + " " + hrs + ":" + min + ":" + "00");
        Date dateStr = null;
        try {
            dateStr = formatter.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return dateStr;

    }

    public Date getDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String str = new String(day + "/" + month + "/" + year);
        Date dateStr = null;
        try {
            dateStr = formatter.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return dateStr;

    }

    @Override
    public String toString() {
        return this.day+"/"+this.month+"/"+this.year +"/"+this.hrs+"/"+this.min;
    }
}
