package com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.QueryObjects;

import android.arch.persistence.room.ColumnInfo;

public class PersonalPageAllInformation {

    @ColumnInfo(name = "_id")
    public long userId;

    @ColumnInfo(name = "dateId")
    public long dateId;

    @ColumnInfo(name = "first_name")
    public String firstName;

    @ColumnInfo(name = "last_name")
    public String lastName;

    @ColumnInfo(name = "foto_path")
    public String fotoPath;

    @ColumnInfo(name = "year")
    public String year;

    @ColumnInfo(name = "month")
    public String month;

    @ColumnInfo(name = "day")
    public String day;

    @ColumnInfo(name = "comment")
    public String comment;

    @ColumnInfo(name = "type_celebration")
    public String typeCelebration;
}
