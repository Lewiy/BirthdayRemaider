package com.romanenko.lew.birthdayremaider.DISystem.Modules;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.romanenko.lew.birthdayremaider.DISystem.Components.DataBaseSingleScope;
import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.AppDataBase;
import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.Tables.CelebrationPersonEntity;

import dagger.Module;
import dagger.Provides;

@Module
public class DataBaseModule {

    @DataBaseSingleScope
    @Provides
    public AppDataBase appDataBase(Context context) {
        return Room.databaseBuilder(context, AppDataBase.class, "database.db")
                .build();
    }

    @Provides
    public CelebrationPersonEntity provideCelebPerEnt() {
        return new CelebrationPersonEntity();
    }


}
