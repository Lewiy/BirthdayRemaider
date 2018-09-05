package com.romanenko.lew.birthdayremaider;

import android.content.Context;

import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.QueryObjects.ListRequirementData;
import com.romanenko.lew.birthdayremaider.Model.IModel;
import com.romanenko.lew.birthdayremaider.Presenter.MvpPresenter;
import com.romanenko.lew.birthdayremaider.Presenter.PresenterListCelebration;
import com.romanenko.lew.birthdayremaider.View.IView;

import java.util.List;

public interface ListCelebrationContract {

    interface ViewListBirthday extends IView {
        void loadListCelebration(List<ListRequirementData> listRequirementData);
    }

    interface PresenterListBirthday extends MvpPresenter<ViewListBirthday, ModelListBirthday> {
        void loadListCelebration(List<ListRequirementData> listRequirementData);
        void addRemainder(String name, String serName, String comment, String date, String typeCelebration);
    }

    interface ModelListBirthday extends IModel {
        void initLocalReposetory(Context context);

        void addCelebration(String name, String serName, String comment, String date, String typeCelebration);

        void deleteCelebration();

        void upDateCelebration();

        void loadData();

        void attachPresenter(PresenterListCelebration presenter);
    }
}
