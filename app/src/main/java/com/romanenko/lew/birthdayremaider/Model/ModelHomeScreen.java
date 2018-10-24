package com.romanenko.lew.birthdayremaider.Model;

import android.content.Context;

import com.romanenko.lew.birthdayremaider.DISystem.Components.DaggerDataBaseComponent;
import com.romanenko.lew.birthdayremaider.DISystem.Components.DataBaseComponent;
import com.romanenko.lew.birthdayremaider.DISystem.Modules.DataBaseModule;
import com.romanenko.lew.birthdayremaider.HomeScreenContract;
import com.romanenko.lew.birthdayremaider.ListCelebrationContract;
import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.AppDataBase;
import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.QueryObjects.CelebrListNameDateFotoDTO;
import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.QueryObjects.DataCelebrationForListDTO;
import com.romanenko.lew.birthdayremaider.MyApp;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;

public class ModelHomeScreen implements HomeScreenContract.ModelCelebrations {

    @Inject
    AppDataBase appDataBase;
    DataBaseComponent dataBaseComponent;


    @Override
    public Flowable<List<CelebrListNameDateFotoDTO>> pullCelebrations() {
        return appDataBase.celebrationPersonEntityDao()
                .getListCelebrDateNameFoto();
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
