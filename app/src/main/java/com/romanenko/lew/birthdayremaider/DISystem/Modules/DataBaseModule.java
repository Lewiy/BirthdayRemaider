package com.romanenko.lew.birthdayremaider.DISystem.Modules;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.romanenko.lew.birthdayremaider.DISystem.Components.DataBaseSingleScope;
import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.AppDataBase;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module(includes = ContextModule.class)
public class DataBaseModule {

    @DataBaseSingleScope
    @Provides
    public AppDataBase appDataBase ( Context context){
        return   Room.databaseBuilder( context,AppDataBase.class, "database")
                .build();
    }

}
