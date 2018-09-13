package com.romanenko.lew.birthdayremaider.Model.DataLocalRepository;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {CelebrationPersonEntity.class, DateEntity.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {

    public abstract CelebrationPersonDao celebrationPersonEntityDao();

}
