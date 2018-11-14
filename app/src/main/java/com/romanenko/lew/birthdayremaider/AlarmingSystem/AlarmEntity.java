package com.romanenko.lew.birthdayremaider.AlarmingSystem;

public class AlarmEntity {
    private int id;
    private MyDate date;
    private int idGroup;

    public int getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(int idGroup) {
        this.idGroup = idGroup;
    }


    public AlarmEntity(int id, int idGroup, MyDate date) {
        this.id = id;
        this.date = date;
        this.idGroup = idGroup;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MyDate getDate() {
        return date;
    }

    public void setDate(MyDate date) {
        this.date = date;
    }


}
