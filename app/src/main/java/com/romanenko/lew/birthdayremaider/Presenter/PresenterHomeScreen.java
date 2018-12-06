package com.romanenko.lew.birthdayremaider.Presenter;

import android.content.Context;

import com.romanenko.lew.birthdayremaider.HomeScreenContract;
import com.romanenko.lew.birthdayremaider.Model.DTO.CelebrationMapper;
import com.romanenko.lew.birthdayremaider.Model.DTO.CelebrationVO;
import com.romanenko.lew.birthdayremaider.Model.DTO.HomeCelebrationVO;
import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.QueryObjects.CelebrListNameDateFotoDTO;
import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.QueryObjects.DataCelebrationForListDTO;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
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
                .flatMap(new Function<List<CelebrListNameDateFotoDTO>, Flowable<CelebrListNameDateFotoDTO>>() {
                    @Override
                    public Flowable<CelebrListNameDateFotoDTO> apply(List<CelebrListNameDateFotoDTO> dataCelebrationForListDTOS) throws Exception {
                        // return Observable.from(dataCelebrationForListDTOS);
                        return Flowable.fromIterable(dataCelebrationForListDTOS);
                    }
                })
                .map(new Function<CelebrListNameDateFotoDTO, HomeCelebrationVO>() {
                    @Override
                    public HomeCelebrationVO apply(CelebrListNameDateFotoDTO dataCelebrationForListDTOS) throws Exception {
                        // return  CelebrationMapper.constructCelebrationVO(dataCelebrationForListDTOS);
                        return CelebrationMapper.constructHomeCelebrVO(dataCelebrationForListDTOS);
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<HomeCelebrationVO>() {
                    @Override
                    public void accept(HomeCelebrationVO homeCelebrationVO) throws Exception {
                        getView().showItemCelebrHome(homeCelebrationVO);
                    }
                });
    }
}
