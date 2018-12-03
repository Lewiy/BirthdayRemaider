package com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.Tables;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.Tables.CelebrationPersonEntity;

@Entity(tableName = "date_entity", foreignKeys = @ForeignKey(entity = CelebrationPersonEntity.class, parentColumns = "_id", childColumns = "dateId"))
public class DateEntity {
    @PrimaryKey(autoGenerate = true)
    public long dateId;

    @ColumnInfo(name = "year")
    public int year;

    @ColumnInfo(name = "month")
    public int month;

    @ColumnInfo(name = "day")
    public int day;

}
