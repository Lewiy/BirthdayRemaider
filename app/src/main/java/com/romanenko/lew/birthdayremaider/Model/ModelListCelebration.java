package com.romanenko.lew.birthdayremaider.Model;

import android.content.Context;

import com.romanenko.lew.birthdayremaider.DISystem.Components.DaggerDataBaseComponent;
import com.romanenko.lew.birthdayremaider.DISystem.Components.DataBaseComponent;
import com.romanenko.lew.birthdayremaider.DISystem.Modules.DataBaseModule;
import com.romanenko.lew.birthdayremaider.ListCelebrationContract;
import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.AppDataBase;
import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.CelebrationPersonEntity;
import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.QueryObjects.ListRequirementData;
import com.romanenko.lew.birthdayremaider.MyApp;
import com.romanenko.lew.birthdayremaider.Presenter.Presenter;
import com.romanenko.lew.birthdayremaider.Presenter.PresenterListCelebration;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

public class ModelListCelebration implements ListCelebrationContract.ModelListBirthday {

    @Inject
    AppDataBase appDataBase;
    //@Inject
    PresenterListCelebration presenterListCelebration;

    DataBaseComponent dataBaseComponent;

    /*public ModelListCelebration(Presenter presenter) {
        this.presenter = presenter;
    }*/


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
    public void addCelebration(String name, String serName, String comment, String date, String typeCelebration) {
        CelebrationPersonEntity celebrationPersonEntity = dataBaseComponent.getCelebrationPersonEntity();
        celebrationPersonEntity.firstName = name;
        celebrationPersonEntity.lastName = serName;
        celebrationPersonEntity.comment = comment;
        celebrationPersonEntity.date = date;
        celebrationPersonEntity.typeCelebration = typeCelebration;
        /////////
        appDataBase.celebrationPersonEntityDao().birthdayPersonInsert(celebrationPersonEntity);
    }

    @Override
    public void deleteCelebration() {

    }

    @Override
    public void upDateCelebration() {

    }

    @Override
    public void loadData() {
        appDataBase.celebrationPersonEntityDao()
                .getListCelebration()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<ListRequirementData>>() {
                    @Override
                    public void accept(List<ListRequirementData> listRequirementData) throws Exception {
                        //presenterListCelebration.loadListCelebration();

                    }
                });
    }

    @Override
    public void attachPresenter(PresenterListCelebration presenter) {
        this.presenterListCelebration = presenter;
    }

}
