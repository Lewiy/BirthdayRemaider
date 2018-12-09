package com.romanenko.lew.birthdayremaider.DISystem.Modules;

import com.romanenko.lew.birthdayremaider.AddCelebrationContract;
import com.romanenko.lew.birthdayremaider.ListCelebrationContract;

import dagger.Module;
import dagger.Provides;

@Module
public class MVPMAddRemainder {

    private AddCelebrationContract.ViewAddRemainder view;
    private AddCelebrationContract.PresenterAddRemainder presenter;
    private ListCelebrationContract.ModelListBirthday model;

    public MVPMAddRemainder(AddCelebrationContract.ViewAddRemainder view, AddCelebrationContract.PresenterAddRemainder presenter) {
        this.view = view;
        this.presenter = presenter;
    }
    public MVPMAddRemainder (ListCelebrationContract.ModelListBirthday model) {
        this.model = model;
    }

    @Provides
    AddCelebrationContract.ViewAddRemainder  provideViewAddRemainder() {
        return view;
    }


    @Provides
    AddCelebrationContract.PresenterAddRemainder providePresenterAddRemainder() {
        return presenter;
    }

}
