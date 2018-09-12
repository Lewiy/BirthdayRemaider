package com.romanenko.lew.birthdayremaider.AlarmingSystem;

public interface IalarmManager {

    void setDateOnce(MyDate date);
    void setDateRepiting(MyDate date);
    void deleteDate(String date);
}
