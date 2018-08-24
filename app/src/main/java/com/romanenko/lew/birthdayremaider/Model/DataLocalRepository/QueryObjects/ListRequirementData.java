package com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.QueryObjects;

import android.arch.persistence.room.ColumnInfo;

public class ListRequirementData {

    @ColumnInfo(name = "first_name")
    public String firstName;

    @ColumnInfo(name = "last_name")
    public String lastName;

    @ColumnInfo(name = "date")
    public String date;

    @ColumnInfo(name = "foto_path")
    public String fotoPath;


}
