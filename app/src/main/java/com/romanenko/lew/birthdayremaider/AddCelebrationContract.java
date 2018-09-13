package com.romanenko.lew.birthdayremaider;

import android.content.Context;

import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.CelebrationPersonEntity;
import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.DateEntity;
import com.romanenko.lew.birthdayremaider.Model.IModel;
import com.romanenko.lew.birthdayremaider.Presenter.MvpPresenter;
import com.romanenko.lew.birthdayremaider.Presenter.Presenter;
import com.romanenko.lew.birthdayremaider.View.IView;

import io.reactivex.Completable;

public interface AddCelebrationContract {
    interface ViewAddRemainder extends IView{

        String getName();
        String getSurname();
        int getYear();
        int getDay();
        int getMonth();
        String getPathImage();
        String getTypeCelebration();
        String getComment();
    }

    interface PresenterAddRemainder extends MvpPresenter<ViewAddRemainder,ModelAddRemainder> {

        void addRemainder();
    }

    interface ModelAddRemainder extends IModel{
        void initLocalReposetory(Context context);
        Completable addCelebration(CelebrationPersonEntity celebrationPersonEntity, DateEntity dateEntity);
    }

}
