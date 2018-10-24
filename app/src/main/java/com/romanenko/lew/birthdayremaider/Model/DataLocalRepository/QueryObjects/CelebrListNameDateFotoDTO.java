package com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.QueryObjects;

import android.arch.persistence.room.ColumnInfo;

public class CelebrListNameDateFotoDTO {

    @ColumnInfo(name = "year")
    public int year;
    @ColumnInfo(name = "month")
    public int month;
    @ColumnInfo(name = "day")
    public int day;
    @ColumnInfo(name = "first_name")
    public String firstName;
    @ColumnInfo(name = "last_name")
    public String lastName;
    @ColumnInfo(name = "foto_path")
    public String fotoPath;
}
