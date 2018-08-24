package com.romanenko.lew.birthdayremaider.DISystem.Modules;

import com.romanenko.lew.birthdayremaider.ListBirthdayContract;
import com.romanenko.lew.birthdayremaider.Presenter.Presenter;

import dagger.Module;
import dagger.Provides;
@Module
public class MVPModule {

    private ListBirthdayContract.ViewListBirthday viewListBirthday;
    private Presenter presenterListBirthday;

    public MVPModule(ListBirthdayContract.ViewListBirthday viewListBirthday, Presenter presenterListBirthday) {
        this.viewListBirthday = viewListBirthday;
        this.presenterListBirthday = presenterListBirthday;
    }

    @Provides
    ListBirthdayContract.ViewListBirthday provideViewListBirthday() {
        return viewListBirthday;
    }

    @Provides
    Presenter providePresenterListBirthday() {
        return presenterListBirthday;
    }
}
