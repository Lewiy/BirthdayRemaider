package com.romanenko.lew.birthdayremaider.DISystem.Modules;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.romanenko.lew.birthdayremaider.DISystem.Components.AppComponent;
import com.romanenko.lew.birthdayremaider.DISystem.Components.DataBaseSingleScope;
import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.AppDataBase;
import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.CelebrationPersonEntity;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class DataBaseModule {

    @DataBaseSingleScope
    @Provides
    public AppDataBase appDataBase(Context context) {
        return Room.databaseBuilder(context, AppDataBase.class, "database")
                .build();
    }

    @Provides
    public CelebrationPersonEntity provideCelebPerEnt() {
        return new CelebrationPersonEntity();
    }


}
