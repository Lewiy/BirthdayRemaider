package com.romanenko.lew.birthdayremaider.Model;

import android.content.Context;

import com.romanenko.lew.birthdayremaider.DISystem.Components.DaggerDataBaseComponent;
import com.romanenko.lew.birthdayremaider.DISystem.Components.DataBaseComponent;
import com.romanenko.lew.birthdayremaider.DISystem.Modules.DataBaseModule;
import com.romanenko.lew.birthdayremaider.EditProfileCelebration;
import com.romanenko.lew.birthdayremaider.Model.DTO.CelebrationMapper;
import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.AppDataBase;
import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.CelebrationPersonEntity;
import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.DateEntity;
import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.QueryObjects.DataCelebrationForListDTO;
import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.QueryObjects.PersonalPageAllInformation;
import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.QueryObjects.PersonalPageRequirementDataDTO;
import com.romanenko.lew.birthdayremaider.MyApp;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.functions.Action;

public class ModelEditCelebration implements EditProfileCelebration.ModelEditCelebration {

    @Inject
    AppDataBase appDataBase;
    DataBaseComponent dataBaseComponent;



    @Override
    public void initLocalRepository(Context context) {
        dataBaseComponent = DaggerDataBaseComponent.builder().appComponent(MyApp.get(context).component())
                .dataBaseModule(new DataBaseModule())
                //.contextModule(new ContextModule(context))
                .build();
        dataBaseComponent.inject(this);
    }




    @Override
    public Flowable<PersonalPageAllInformation> pullPersonalPage(String id) {
        return appDataBase.celebrationPersonEntityDao()
                .getPersonalPageAll(id);
    }

    @Override
    public Completable deleteCelebration(int userId, int dateId) {
        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                appDataBase.celebrationPersonEntityDao().deleteCelebrationAndDate(userId,dateId);
            }
        });
    }

}
