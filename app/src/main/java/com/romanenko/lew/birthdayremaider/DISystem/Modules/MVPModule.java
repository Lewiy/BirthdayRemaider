package com.romanenko.lew.birthdayremaider.DISystem.Modules;

import com.romanenko.lew.birthdayremaider.ListBirthdayContract;
import com.romanenko.lew.birthdayremaider.Model.IModel;
import com.romanenko.lew.birthdayremaider.Presenter.Presenter;
import com.romanenko.lew.birthdayremaider.View.IView;

import dagger.Module;
import dagger.Provides;

@Module
public class MVPModule {

    private ListBirthdayContract.ViewListBirthday view;
    private Presenter presenter;

    public MVPModule(ListBirthdayContract.ViewListBirthday view, Presenter presenter) {
        this.view = view;
        this.presenter = presenter;
    }

    @Provides
    ListBirthdayContract.ViewListBirthday provideViewListBirthday() {
        return view;
    }

    @Provides
    Presenter providePresenterListBirthday() {

      //  presenter.attachView(view);
     //   presenter.attachModel(model);

        return presenter;
    }
}
