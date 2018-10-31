package com.romanenko.lew.birthdayremaider.Model.DataLocalRepository;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "celebration_person")
public class CelebrationPersonEntity {
    @PrimaryKey(autoGenerate = true)
    public long _id;

    @ColumnInfo(name = "first_name")
    public String firstName;

    @ColumnInfo(name = "last_name")
    public String lastName;

    @ColumnInfo(name = "type_celebration")
    public String typeCelebration;

    @ColumnInfo(name = "comment")
    public String comment;

    @ColumnInfo(name = "foto_path")
    public String fotoPath;
}
