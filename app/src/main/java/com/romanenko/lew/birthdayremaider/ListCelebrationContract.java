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
import io.reactivex.Single;

public interface ListCelebrationContract {

    interface ViewListBirthday extends IView {
        void loadListCelebration(List<CelebrationVO> items);
        void loadItemCelebration(CelebrationVO item);

        void loadListCelebrationSearch(List<CelebrationVO> items);
    }

    interface PresenterListBirthday extends MvpPresenter<ViewListBirthday, ModelListBirthday> {
        Flowable<Integer> getNumbersOfRows();
        void pullListCelebration();
        void pullListCelebrationSearch(String pattern);
    }

    interface ModelListBirthday extends IModel {
        void initLocalReposetory(Context context);
        Flowable<Integer> getNumbersOfRows();
        Flowable<List<DataCelebrationForListDTO>> pullListCelebration();
        Flowable<List<DataCelebrationForListDTO>> pullListCelebrationSearch(String pattern);
    }
}
