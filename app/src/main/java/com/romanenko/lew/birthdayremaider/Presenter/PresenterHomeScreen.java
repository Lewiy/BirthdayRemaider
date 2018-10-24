package com.romanenko.lew.birthdayremaider.Presenter;

import android.content.Context;

import com.romanenko.lew.birthdayremaider.HomeScreenContract;
import com.romanenko.lew.birthdayremaider.Model.DTO.CelebrationMapper;
import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.QueryObjects.CelebrListNameDateFotoDTO;
import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.QueryObjects.DataCelebrationForListDTO;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class PresenterHomeScreen extends
        Presenter<HomeScreenContract.ViewCelebrations,HomeScreenContract.ModelCelebrations>
        implements HomeScreenContract.PresenterCelebrations {
    public PresenterHomeScreen(Context context) {
        this.context = context;
    }

    private Context context;

    @Override
    public void viewIsReady() {
        getModel().initLocalReposetory(context);
    }

    @Override
    public void loadCelebrations() {
        getModel().pullCelebrations()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<CelebrListNameDateFotoDTO>>() {
                    @Override
                    public void accept(List<CelebrListNameDateFotoDTO> datumCelebrationForLists) throws Exception {
                        getView().showCelebrations(datumCelebrationForLists);
                    }
                });
    }
}
