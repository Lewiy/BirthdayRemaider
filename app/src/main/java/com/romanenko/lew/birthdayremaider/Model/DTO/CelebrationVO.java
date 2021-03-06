package com.romanenko.lew.birthdayremaider.Model.DTO;


import android.graphics.Bitmap;

import java.util.Date;

public class CelebrationVO {

    private String firstName;
    private String lastName;

    private String fotoPath;

    private int idAlarm;

    private long idUser;
    private String typeCelebration;
    private int day, month, year;
    private Bitmap image;

    public int getIdAlarm() {
        return idAlarm;
    }

    public void setIdAlarm(int idAlarm) {
        this.idAlarm = idAlarm;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }


    public int getDay() {
        return day;
    }

    public int getIdTemporary() {
        return idAlarm;
    }

    public void setIdTemporary(int idAlarm) {
        this.idAlarm = idAlarm;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }


    public String getTypeCelebration() {
        return typeCelebration;
    }

    public void setTypeCelebration(String typeCelebration) {
        this.typeCelebration = typeCelebration;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    private String comment;

    public long getIdUser() {
        return idUser;
    }


    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public void setFotoPath(String fotoPath) {
        this.fotoPath = fotoPath;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }


    public String getFotoPath() {
        return fotoPath;
    }

}
