package com.romanenko.lew.birthdayremaider.Model;

import android.content.Context;

import com.romanenko.lew.birthdayremaider.AddCelebrationContract;
import com.romanenko.lew.birthdayremaider.DISystem.Components.DaggerDataBaseComponent;
import com.romanenko.lew.birthdayremaider.DISystem.Components.DataBaseComponent;
import com.romanenko.lew.birthdayremaider.DISystem.Modules.DataBaseModule;
import com.romanenko.lew.birthdayremaider.Model.DTO.CelebrationVO;
import com.romanenko.lew.birthdayremaider.Model.DTO.DateCelebrationVO;
import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.AppDataBase;
import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.CelebrationPersonEntity;
import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.DateEntity;
import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.QueryObjects.PersonalPageAllInformation;
import com.romanenko.lew.birthdayremaider.MyApp;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.functions.Action;

public class ModelAddRemainder implements AddCelebrationContract.ModelAddRemainder {

    @Inject
    AppDataBase appDataBase;
    DataBaseComponent dataBaseComponent;




    @Override
    public Completable addCelebration(CelebrationPersonEntity celebrationPersonEntity, DateEntity dateEntity) {

        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                appDataBase.celebrationPersonEntityDao().insertPersonAndDate(celebrationPersonEntity,dateEntity);
            }
        });
    }

    @Override
    public Completable upDateCelebration(CelebrationPersonEntity celebrationPersonEntity, DateEntity dateEntity) {
        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                appDataBase.celebrationPersonEntityDao().updateCelebrationAndDate(celebrationPersonEntity,dateEntity);
            }
        });
    }

    @Override
    public Flowable<PersonalPageAllInformation> pullPersonalPage(String id) {
        return appDataBase.celebrationPersonEntityDao()
                .getPersonalPageAll(id);
    }


   /* @Override
    public Flowable<PersonalPageAllInformation> upDateCelebrationAndShow(CelebrationPersonEntity celebrationPersonEntity, DateEntity dateEntity) {
        return appDataBase.celebrationPersonEntityDao().updateAndShowCelebrationAndDate(celebrationPersonEntity,dateEntity);
    }*/


    @Override
    public Flowable<Integer> getNumberOfRows() {
        return appDataBase.celebrationPersonEntityDao().getNumberOfRows();
    }

    @Override
    public void initLocalReposetory(Context context) {

        dataBaseComponent = DaggerDataBaseComponent.builder().appComponent(MyApp.get(context).component())
                .dataBaseModule(new DataBaseModule())
                //.contextModule(new ContextModule(context))
                .build();
        dataBaseComponent.inject(this);
    }
}
