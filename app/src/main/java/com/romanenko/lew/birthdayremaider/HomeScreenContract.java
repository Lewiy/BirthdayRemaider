package com.romanenko.lew.birthdayremaider;

import android.content.Context;

import com.romanenko.lew.birthdayremaider.Model.DTO.CelebrationVO;
import com.romanenko.lew.birthdayremaider.Model.DTO.HomeCelebrationVO;
import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.QueryObjects.CelebrListNameDateFotoDTO;
import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.QueryObjects.DataCelebrationForListDTO;
import com.romanenko.lew.birthdayremaider.Model.IModel;
import com.romanenko.lew.birthdayremaider.Presenter.MvpPresenter;
import com.romanenko.lew.birthdayremaider.View.IView;

import java.util.List;

import io.reactivex.Flowable;

public interface HomeScreenContract {

    interface ViewCelebrations extends IView {

       void  showCelebrations(List<CelebrListNameDateFotoDTO> datumCelebrationForLists);
       void showItemCelebrHome(HomeCelebrationVO homeCelebrationVO);
    }

    interface PresenterCelebrations extends MvpPresenter<HomeScreenContract.ViewCelebrations,HomeScreenContract.ModelCelebrations> {
        void  loadCelebrations();
    }

    interface ModelCelebrations extends IModel {
        Flowable<List<CelebrListNameDateFotoDTO>>  pullCelebrations();
        void initLocalReposetory(Context context);
    }

}
