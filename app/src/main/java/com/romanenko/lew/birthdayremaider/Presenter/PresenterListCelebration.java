package com.romanenko.lew.birthdayremaider.Presenter;

import android.content.Context;

import com.romanenko.lew.birthdayremaider.ListCelebrationContract;
import com.romanenko.lew.birthdayremaider.Model.DTO.CelebrationVO;
import com.romanenko.lew.birthdayremaider.Model.DTO.CelebrationMapper;
import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.QueryObjects.DataCelebrationForListDTO;

import org.reactivestreams.Publisher;

import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class PresenterListCelebration extends Presenter<ListCelebrationContract.ViewListBirthday, ListCelebrationContract.ModelListBirthday> implements ListCelebrationContract.PresenterListBirthday {


    private Context context;
    private int start = 0, end = 7;
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
        getModel().pullListCelebration()
                .flatMap(new Function<List<DataCelebrationForListDTO>, Flowable<DataCelebrationForListDTO>>() {
                    @Override
                    public Flowable<DataCelebrationForListDTO> apply(List<DataCelebrationForListDTO> dataCelebrationForListDTOS) throws Exception {
                        // return Observable.from(dataCelebrationForListDTOS);
                        return Flowable.fromIterable(dataCelebrationForListDTOS);
                    }
                })
                .map(new Function<DataCelebrationForListDTO, CelebrationVO>() {
                    @Override
                    public CelebrationVO apply(DataCelebrationForListDTO dataCelebrationForListDTOS) throws Exception {
                        // return  CelebrationMapper.constructCelebrationVO(dataCelebrationForListDTOS);
                        return CelebrationMapper.constructCelebrationVO(dataCelebrationForListDTOS);
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CelebrationVO>() {
                    @Override
                    public void accept(CelebrationVO celebrationVOS) throws Exception {
                        getView().loadItemCelebration(celebrationVOS);
                    }
                });
    }

    @Override
    public void pullListCelebrationSearch(String pattern) {
        getModel().pullListCelebrationSearch("%" + pattern + "%")
                .map(new Function<List<DataCelebrationForListDTO>, List<CelebrationVO>>() {

                    @Override
                    public List<CelebrationVO> apply(List<DataCelebrationForListDTO> dataCelebrationForListDTOS) throws Exception {

                        return new CelebrationMapper().getVOObjects(dataCelebrationForListDTOS);
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<CelebrationVO>>() {
                    @Override
                    public void accept(List<CelebrationVO> celebrationVOS) throws Exception {
                        getView().loadListCelebrationSearch(celebrationVOS);
                    }
                });
    }

}
