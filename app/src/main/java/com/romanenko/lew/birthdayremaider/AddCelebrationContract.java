package com.romanenko.lew.birthdayremaider;

import android.content.Context;

import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.Tables.CelebrationPersonEntity;
import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.Tables.DateEntity;
import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.QueryObjects.PersonalPageAllInformation;
import com.romanenko.lew.birthdayremaider.Model.IModel;
import com.romanenko.lew.birthdayremaider.Presenter.MvpPresenter;
import com.romanenko.lew.birthdayremaider.View.IView;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public interface AddCelebrationContract {
    interface ViewAddRemainder extends IView {


        String getName();

        String getSurname();

        int getYear();

        int getDay();

        int getMonth();


        String getPathImage();


        String getTypeCelebration();


        String getComment();

        int getUserId();

        int getDateId();

        int getNumberOfRows();


        void setName(String name);

        void setSurname(String surName);

        void setYear(int year);

        void setDay(int day);

        void setMonth(int month);

        void setPathImage(String pathName);

        void setTypeCelebration(String typeCelebration);

        void setComment(String Comment);

        void setIdUser(int userId);

        void setIdDate(int dateId);

        void setNumberOfRows(Integer numberOfRows);


    }

    interface PresenterAddRemainder extends MvpPresenter<ViewAddRemainder, ModelAddRemainder> {
        void getNumberOfRows();

        void addRemainder();

        void editCelebration();
        // void editCelebrationAndShow();
        void pullPersonalPage(int idUser);
    }

    interface ModelAddRemainder extends IModel {

        Flowable<PersonalPageAllInformation> pullPersonalPage(String id);

        Flowable<Integer> getNumberOfRows();

        void initLocalReposetory(Context context);

        Completable upDateCelebration(CelebrationPersonEntity celebrationPersonEntity, DateEntity dateEntity);

        Completable addCelebration(CelebrationPersonEntity celebrationPersonEntity, DateEntity dateEntity);
        // Flowable<PersonalPageAllInformation> upDateCelebrationAndShow(CelebrationPersonEntity celebrationPersonEntity, DateEntity dateEntity);
    }

}
