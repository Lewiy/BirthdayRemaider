package com.romanenko.lew.birthdayremaider.Model;

import android.content.Context;

import com.romanenko.lew.birthdayremaider.DISystem.Components.DaggerDataBaseComponent;
import com.romanenko.lew.birthdayremaider.DISystem.Components.DataBaseComponent;
import com.romanenko.lew.birthdayremaider.DISystem.Modules.DataBaseModule;
import com.romanenko.lew.birthdayremaider.ListCelebrationContract;
import com.romanenko.lew.birthdayremaider.Model.DTO.CelebrationMapper;
import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.AppDataBase;
import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.CelebrationPersonEntity;
import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.QueryObjects.DataCelebrationForListDTO;
import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.QueryObjects.PersonalPageRequirementDataDTO;
import com.romanenko.lew.birthdayremaider.MyApp;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;

import io.reactivex.Flowable;

import io.reactivex.functions.Action;

public class ModelListCelebration implements ListCelebrationContract.ModelListBirthday {

    @Inject
    AppDataBase appDataBase;
    DataBaseComponent dataBaseComponent;


    @Override
    public void initLocalReposetory(Context context) {
        //DataBaseComponent dataBaseComponent =
        /*DaggerDataBaseComponent
                .builder().appComponent(MyApp.get(context).component())
                .dataBaseModule(new DataBaseModule())
                //.contextModule(new ContextModule(context))
                .build().inject(this);*/


        dataBaseComponent = DaggerDataBaseComponent.builder().appComponent(MyApp.get(context).component())
                .dataBaseModule(new DataBaseModule())
                //.contextModule(new ContextModule(context))
                .build();

        dataBaseComponent.inject(this);

    }



    @Override
    public Completable deleteCelebration(DataCelebrationForListDTO dataCelebrationForListDTO) {
        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                appDataBase.celebrationPersonEntityDao().birthdayPersonInsert(new CelebrationMapper().constructEntity(dataCelebrationForListDTO));
            }
        });
    }

    @Override
    public Completable upDateCelebration(DataCelebrationForListDTO dataCelebrationForListDTO) {
        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                appDataBase.celebrationPersonEntityDao().birthdayPersonInsert(new CelebrationMapper().constructEntity(dataCelebrationForListDTO));
            }
        });
    }


    @Override
    public Flowable<List<DataCelebrationForListDTO>> pullListCelebration() {
        return appDataBase.celebrationPersonEntityDao()
                .getListCelebration();

    }

    @Override
    public Flowable<PersonalPageRequirementDataDTO> pullPersonalPage(String id) {
        return appDataBase.celebrationPersonEntityDao()
                .getPersonalPage(id);
    }


}
