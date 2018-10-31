package com.romanenko.lew.birthdayremaider.Model;

import android.content.Context;

import com.romanenko.lew.birthdayremaider.DISystem.Components.DaggerDataBaseComponent;
import com.romanenko.lew.birthdayremaider.DISystem.Components.DataBaseComponent;
import com.romanenko.lew.birthdayremaider.DISystem.Modules.DataBaseModule;
import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.AppDataBase;
import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.QueryObjects.NotifyDTO;
import com.romanenko.lew.birthdayremaider.MyApp;
import com.romanenko.lew.birthdayremaider.NotificationContract;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;

public class ModelNotif implements NotificationContract.ModelNotif{

    @Inject
    AppDataBase appDataBase;
    DataBaseComponent dataBaseComponent;

    @Override
    public void initLocalReposetory(Context context) {

        dataBaseComponent = DaggerDataBaseComponent.builder().appComponent(MyApp.get(context).component())
                .dataBaseModule(new DataBaseModule())
                //.contextModule(new ContextModule(context))
                .build();

        dataBaseComponent.inject(this);
    }

    @Override
    public Flowable<List<NotifyDTO>> loadCelebrs(int day,int month,int year) {
        return  appDataBase.celebrationPersonEntityDao().getCelebrsDate(day,month,year);
    }
}
