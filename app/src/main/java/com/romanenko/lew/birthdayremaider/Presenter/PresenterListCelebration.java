package com.romanenko.lew.birthdayremaider.Presenter;

import android.content.Context;

import com.romanenko.lew.birthdayremaider.ListCelebrationContract;
import com.romanenko.lew.birthdayremaider.Model.DTO.CelebrationVO;
import com.romanenko.lew.birthdayremaider.Model.DTO.CelebrationMapper;
import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.QueryObjects.DataCelebrationForListDTO;

import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class PresenterListCelebration extends Presenter<ListCelebrationContract.ViewListBirthday, ListCelebrationContract.ModelListBirthday> implements ListCelebrationContract.PresenterListBirthday {


    private Context context;
    private int start = 0,end = 7;
  //  private final static  int NUMBER_ROW_IN_PAGE = 7;


    public PresenterListCelebration(Context context) {
        this.context = context;
    }

    public PresenterListCelebration() {

    }

    @Override
    public void viewIsReady() {

        getModel().initLocalReposetory(context);

    }


    @Override
    public Flowable<Integer> getNumbersOfRows() {
        return getModel().getNumbersOfRows();
    }

    public void pullListCelebration() {
        getModel().pullListCelebration( )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<DataCelebrationForListDTO>>() {
                    @Override
                    public void accept(List<DataCelebrationForListDTO> datumCelebrationForLists) throws Exception {
                        getView().loadListCelebration(new CelebrationMapper().getVOObjects(datumCelebrationForLists));
                        //start += NUMBER_ROW_IN_PAGE;
                      //  end += NUMBER_ROW_IN_PAGE;
                    }
                });
    }

    @Override
    public void pullListCelebrationSearch(String pattern) {
        getModel().pullListCelebrationSearch("%" + pattern + "%")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<DataCelebrationForListDTO>>() {
                    @Override
                    public void accept(List<DataCelebrationForListDTO> datumCelebrationForLists) throws Exception {
                        getView().loadListCelebrationSearch(new CelebrationMapper().getVOObjects(datumCelebrationForLists ));
                    }
                });
    }

}
