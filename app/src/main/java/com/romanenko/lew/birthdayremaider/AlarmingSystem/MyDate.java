package com.romanenko.lew.birthdayremaider.AlarmingSystem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MyDate {

    private int year, month, mDate, hrs = 0, min = 0;
    private int reapiting;
    private int countReapiting;


    public void setReapiting(int reapiting) {
        this.reapiting = reapiting;
    }

    public void setCountReapiting(int countReapiting) {
        this.countReapiting = countReapiting;
    }


    public int getReapiting() {
        return reapiting;
    }

    public int getCountReapiting() {
        return countReapiting;
    }

    public MyDate(int year, int month, int mDate, int hrs, int min) {

        this.year = year;
        this.month = month;
        this.mDate = mDate;
        this.hrs = hrs;
        this.min = min;

    }

    public MyDate(int year, int month, int mDate) {

        this.year = year;
        this.month = month;
        this.mDate = mDate;

    }

    public Date getDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        String str = new String(mDate + "/" + month + "/" + year + " " + hrs + ":" + min + ":" + "00");
        Date dateStr = null;
        try {
            dateStr = formatter.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return dateStr;
        //  return
    }
}
