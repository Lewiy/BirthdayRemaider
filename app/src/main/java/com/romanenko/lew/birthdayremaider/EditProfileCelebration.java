package com.romanenko.lew.birthdayremaider;

import android.content.Context;

import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.CelebrationPersonEntity;
import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.DateEntity;
import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.QueryObjects.DataCelebrationForListDTO;
import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.QueryObjects.PersonalPageAllInformation;
import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.QueryObjects.PersonalPageRequirementDataDTO;
import com.romanenko.lew.birthdayremaider.Model.IModel;
import com.romanenko.lew.birthdayremaider.Presenter.MvpPresenter;
import com.romanenko.lew.birthdayremaider.View.IView;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public interface EditProfileCelebration {

    interface ViewEditCelebration extends IView{

        void setName(String name,String surName);
        void setTypeCelebration(String typeCelebration);
        void setComment(String comment);
        void setDate(String date);
        void setTimeToAlarm(String timeToAlarm);
        void setPictureContact(String path);
        void setIdUser(int userId);
        void setIdDate(int dateId);
    }

    interface PresenterEditCelebration extends MvpPresenter<ViewEditCelebration,ModelEditCelebration>{

        void deleteCelebration();
        void pullPersonalPage(int idUser);
    }


    interface ModelEditCelebration extends IModel{
        void initLocalRepository(Context context);

        Flowable<PersonalPageAllInformation> pullPersonalPage(String id);
        Completable deleteCelebration(int userId, int dateId);
    }

}
