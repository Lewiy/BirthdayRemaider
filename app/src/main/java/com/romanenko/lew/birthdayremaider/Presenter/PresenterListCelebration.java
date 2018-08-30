package com.romanenko.lew.birthdayremaider.Presenter;

import android.content.Context;

import com.romanenko.lew.birthdayremaider.ListCelebrationContract;

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
        getModel().attachPresenter(this);
    }

    public void addRemainder(String name,String serName,String comment,String date){

        //appDataBase.celebrationPersonEntityDao();
    }

    public void foo(){

    }


}
