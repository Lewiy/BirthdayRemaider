package com.romanenko.lew.birthdayremaider.DISystem.Modules;

import com.romanenko.lew.birthdayremaider.ListCelebrationContract;
import com.romanenko.lew.birthdayremaider.Model.IModel;
import com.romanenko.lew.birthdayremaider.Presenter.Presenter;
import com.romanenko.lew.birthdayremaider.Presenter.PresenterListCelebration;
import com.romanenko.lew.birthdayremaider.View.IView;

import dagger.Module;
import dagger.Provides;

@Module
public class MVPMListCelebration {

    private ListCelebrationContract.ViewListBirthday view;
    private ListCelebrationContract.PresenterListBirthday presenter;
    private ListCelebrationContract.ModelListBirthday model;

    public MVPMListCelebration(ListCelebrationContract.ViewListBirthday view, ListCelebrationContract.PresenterListBirthday presenter) {
        this.view = view;
        this.presenter = presenter;
    }
    public MVPMListCelebration(ListCelebrationContract.ModelListBirthday model) {
        this.model = model;
    }

    @Provides
    ListCelebrationContract.ViewListBirthday  provideViewListBirthday() {
        return view;
    }


    @Provides
    ListCelebrationContract.PresenterListBirthday providePresenterListBirthday() {

      //  presenter.attachView(view);
     //   presenter.attachModel(model);

        return presenter;
    }

    @Provides
    ListCelebrationContract.ModelListBirthday provideModel(){
        return model;
    }
}
