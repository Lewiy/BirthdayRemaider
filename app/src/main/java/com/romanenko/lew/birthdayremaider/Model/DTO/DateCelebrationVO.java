package com.romanenko.lew.birthdayremaider.Model.DTO;

public class DateCelebrationVO {

    private int year;
    private int month;
    private int day;

    public void setDateId(int dateId) {
        this.dateId = dateId;
    }

    private int dateId;
    public int getDateId() {
        return dateId;
    }



    public void setYear(int year) {
        this.year = year;
    }

    public void setMonth(int month) {
        this.month = month + 1;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }


}
