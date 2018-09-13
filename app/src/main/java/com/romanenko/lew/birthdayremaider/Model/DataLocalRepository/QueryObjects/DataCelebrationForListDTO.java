package com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.QueryObjects;

import android.arch.persistence.room.ColumnInfo;

public class DataCelebrationForListDTO {

    @ColumnInfo(name = "_id")
    public long userId;

    @ColumnInfo(name = "first_name")
    public String firstName;

    @ColumnInfo(name = "last_name")
    public String lastName;

    @ColumnInfo(name = "date")
    public String date;

    @ColumnInfo(name = "foto_path")
    public String fotoPath;

    @ColumnInfo(name = "year")
    public String year;

}
