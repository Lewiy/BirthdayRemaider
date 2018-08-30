package com.romanenko.lew.birthdayremaider.Model;

import android.content.Context;

import com.romanenko.lew.birthdayremaider.DISystem.Components.DaggerDataBaseComponent;
import com.romanenko.lew.birthdayremaider.DISystem.Modules.DataBaseModule;
import com.romanenko.lew.birthdayremaider.ListCelebrationContract;
import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.AppDataBase;
import com.romanenko.lew.birthdayremaider.MyApp;
import com.romanenko.lew.birthdayremaider.Presenter.Presenter;
import com.romanenko.lew.birthdayremaider.Presenter.PresenterListCelebration;

import javax.inject.Inject;

public class ModelListCelebration implements ListCelebrationContract.ModelListBirthday {

    @Inject
    AppDataBase appDataBase;
    //@Inject
    PresenterListCelebration presenter;

    /*public ModelListCelebration(Presenter presenter) {
        this.presenter = presenter;
    }*/


    @Override
    public void initLocalReposetory(Context context) {
        //DataBaseComponent dataBaseComponent =
        DaggerDataBaseComponent
                .builder().appComponent(MyApp.get(context).component())
                .dataBaseModule(new DataBaseModule())
                //.contextModule(new ContextModule(context))
                .build().inject(this);
        //presenter.attachModel(this);
      /*  DaggerMVPComponent.builder()
                .mVPModule(new MVPModule(this,new PresenterListCelebration(context
                )))
                .build()
                .inject(this);
        presenter.attachModel(this);*/

        // appDataBase = dataBaseComponent.getAppDataBase();
    }

    @Override
    public void addCelebration() {

    }

    @Override
    public void deleteCelebration() {

    }

    @Override
    public void upDateCelebration() {

    }

    @Override
    public void attachPresenter(PresenterListCelebration presenter) {
        this.presenter = presenter;
    }

}
