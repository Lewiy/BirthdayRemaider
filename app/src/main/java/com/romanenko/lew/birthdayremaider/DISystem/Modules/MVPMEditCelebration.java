package com.romanenko.lew.birthdayremaider.DISystem.Modules;

import com.romanenko.lew.birthdayremaider.EditProfileCelebration;
import com.romanenko.lew.birthdayremaider.ListCelebrationContract;

import dagger.Module;
import dagger.Provides;
@Module
public class MVPMEditCelebration  {

    private EditProfileCelebration.ViewEditCelebration view;
    private EditProfileCelebration.PresenterEditCelebration presenter;
    private ListCelebrationContract.ModelListBirthday model;

    public MVPMEditCelebration(EditProfileCelebration.ViewEditCelebration view, EditProfileCelebration.PresenterEditCelebration presenter) {
        this.view = view;
        this.presenter = presenter;
    }

    public MVPMEditCelebration(ListCelebrationContract.ModelListBirthday model) {
        this.model = model;
    }

    @Provides
    EditProfileCelebration.ViewEditCelebration  provideViewListBirthday() {
        return view;
    }


    @Provides
    EditProfileCelebration.PresenterEditCelebration providePresenterListBirthday() {
        return presenter;
    }

    @Provides
    ListCelebrationContract.ModelListBirthday provideModel(){
        return model;
    }
}
