package com.romanenko.lew.birthdayremaider.Presenter;

import android.content.Context;

import com.romanenko.lew.birthdayremaider.DISystem.Components.DaggerDataBaseComponent;
import com.romanenko.lew.birthdayremaider.DISystem.Components.DataBaseComponent;
import com.romanenko.lew.birthdayremaider.DISystem.Modules.ContextModule;
import com.romanenko.lew.birthdayremaider.DISystem.Modules.DataBaseModule;
import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.AppDataBase;
import com.romanenko.lew.birthdayremaider.Model.IModel;
import com.romanenko.lew.birthdayremaider.MyApp;
import com.romanenko.lew.birthdayremaider.View.IView;

public class PresenterListBirthday extends Presenter<IView, IModel> {


    Context context;
    AppDataBase appDataBase;

    public  PresenterListBirthday(Context context) {
        this.context = context;
    }

    public PresenterListBirthday() {

    }

    @Override
    public void viewIsReady() {
        foo();
        DataBaseComponent dataBaseComponent = DaggerDataBaseComponent
                .builder().appComponent(MyApp.get(context).component())
                .dataBaseModule(new DataBaseModule())
                //.contextModule(new ContextModule(context))
                .build();

        appDataBase = dataBaseComponent.getAppDataBase();
         foo();
    }

    public void addRemainder(String name,String serName,String comment,String date){

        appDataBase.celebrationPersonEntityDao();
    }

    public void foo(){

    }
}
