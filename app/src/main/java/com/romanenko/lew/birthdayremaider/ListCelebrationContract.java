package com.romanenko.lew.birthdayremaider;

import android.content.Context;

import com.romanenko.lew.birthdayremaider.Model.IModel;
import com.romanenko.lew.birthdayremaider.Presenter.MvpPresenter;
import com.romanenko.lew.birthdayremaider.Presenter.PresenterListCelebration;
import com.romanenko.lew.birthdayremaider.View.IView;

public interface ListCelebrationContract {

    interface ViewListBirthday extends IView{
        void loadListCelebration();
    }
    interface PresenterListBirthday extends MvpPresenter<ViewListBirthday,ModelListBirthday>{

    }
    interface ModelListBirthday extends IModel{
        void initLocalReposetory(Context context);
        void addCelebration();
        void deleteCelebration();
        void upDateCelebration();

        void attachPresenter(PresenterListCelebration presenter);
    }
}
