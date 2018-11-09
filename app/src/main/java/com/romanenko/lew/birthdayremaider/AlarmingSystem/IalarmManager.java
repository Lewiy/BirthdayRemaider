package com.romanenko.lew.birthdayremaider.AlarmingSystem;

public interface IalarmManager {

    void setDateOnce(int id,MyDate date);
    void setDateOnce(MyDate date);
    void setDateRepeating(int id,MyDate date);
    void setDateRepeatingHalfDay(int id,MyDate date);
    void setDateRepeatingHour(int id,MyDate date);
    void setDateRepeatingDay(int id,MyDate date);
    void deleteDate(int id,MyDate date);
    void editDateAlarm(int type,int id,MyDate date);
}
