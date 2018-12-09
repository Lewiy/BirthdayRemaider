package com.romanenko.lew.birthdayremaider.DISystem.Modules;

import com.romanenko.lew.birthdayremaider.HomeScreenContract;
import com.romanenko.lew.birthdayremaider.ListCelebrationContract;

import dagger.Module;
import dagger.Provides;

@Module
public class MVPMHomeScreen {
    private HomeScreenContract.ViewCelebrations view;
    private HomeScreenContract.PresenterCelebrations presenter;
    private HomeScreenContract.ModelCelebrations model;

    public MVPMHomeScreen(HomeScreenContract.ViewCelebrations view, HomeScreenContract.PresenterCelebrations presenter) {
        this.view = view;
        this.presenter = presenter;
    }
    public MVPMHomeScreen(HomeScreenContract.ModelCelebrations model) {
        this.model = model;
    }

    @Provides
    HomeScreenContract.ViewCelebrations  provideViewListBirthday() {
        return view;
    }


    @Provides
    HomeScreenContract.PresenterCelebrations providePresenterListBirthday() {
        return presenter;
    }

    @Provides
    HomeScreenContract.ModelCelebrations provideModel(){
        return model;
    }
}
