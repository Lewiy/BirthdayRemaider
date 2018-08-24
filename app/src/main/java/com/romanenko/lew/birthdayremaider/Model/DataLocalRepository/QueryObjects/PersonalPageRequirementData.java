package com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.QueryObjects;

import android.arch.persistence.room.ColumnInfo;

public class PersonalPageRequirementData {

    @ColumnInfo(name = "comment")
    public String comment;

    @ColumnInfo(name = "type_celebration")
    public String typeCelebration;
}
