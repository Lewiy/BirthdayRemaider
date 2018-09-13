package com.romanenko.lew.birthdayremaider;

import android.content.Context;

import com.romanenko.lew.birthdayremaider.Model.DTO.CelebrationVO;
import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.QueryObjects.DataCelebrationForListDTO;
import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.QueryObjects.PersonalPageRequirementDataDTO;
import com.romanenko.lew.birthdayremaider.Model.IModel;
import com.romanenko.lew.birthdayremaider.Presenter.MvpPresenter;
import com.romanenko.lew.birthdayremaider.View.IView;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public interface ListCelebrationContract {

    interface ViewListBirthday extends IView {
        void loadListCelebration(List<CelebrationVO> items);


    }

    interface PresenterListBirthday extends MvpPresenter<ViewListBirthday, ModelListBirthday> {


        void pullListCelebration();

        void deleteCelebration();

        void upDateCelebration();

        void pullPersonalPage();
    }

    interface ModelListBirthday extends IModel {

        void initLocalReposetory(Context context);

        Completable deleteCelebration(DataCelebrationForListDTO dataCelebrationForListDTO);

        Completable upDateCelebration(DataCelebrationForListDTO dataCelebrationForListDTO);

        Flowable<List<DataCelebrationForListDTO>> pullListCelebration();

        Flowable<PersonalPageRequirementDataDTO> pullPersonalPage(String id);
    }
}
