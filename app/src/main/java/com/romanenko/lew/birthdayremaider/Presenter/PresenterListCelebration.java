package com.romanenko.lew.birthdayremaider.Presenter;

import android.content.Context;

import com.romanenko.lew.birthdayremaider.ListCelebrationContract;
import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.QueryObjects.ListRequirementData;

import java.util.List;

public class PresenterListCelebration extends Presenter<ListCelebrationContract.ViewListBirthday, ListCelebrationContract.ModelListBirthday> implements ListCelebrationContract.PresenterListBirthday {


    Context context;


    public PresenterListCelebration(Context context) {
        this.context = context;
    }

    public PresenterListCelebration() {

    }

    @Override
    public void viewIsReady() {

         //PresenterListCelebration presenterListBirthday = (PresenterListCelebration) getModel();
        //getView().
        getModel().initLocalReposetory(context);
        getModel().attachPresenter(this);

    }

    public void addRemainder(String name,String serName,String comment,String date, String typeCelebration){

        //appDataBase.celebrationPersonEntityDao();
        getModel().addCelebration(name,serName,comment,date,typeCelebration);
    }

    public void foo(){

    }

    public void loadListCelebration(List<ListRequirementData> listRequirementData){
        getView().loadListCelebration(listRequirementData);
    }



}
