package com.romanenko.lew.birthdayremaider.Presenter;

import android.content.Context;

import com.romanenko.lew.birthdayremaider.ListCelebrationContract;
import com.romanenko.lew.birthdayremaider.Model.DTO.CelebrationVO;
import com.romanenko.lew.birthdayremaider.Model.DTO.CelebrationMapper;
import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.QueryObjects.DataCelebrationForListDTO;

import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class PresenterListCelebration extends Presenter<ListCelebrationContract.ViewListBirthday, ListCelebrationContract.ModelListBirthday> implements ListCelebrationContract.PresenterListBirthday {


    Context context;


    public PresenterListCelebration(Context context) {
        this.context = context;
    }

    public PresenterListCelebration() {

    }

    @Override
    public void viewIsReady() {

        getModel().initLocalReposetory(context);

    }

    public void addRemainder(String name, String serName, String comment, String date, String typeCelebration,String pathPictureContact) {

        //appDataBase.celebrationPersonEntityDao();
        getModel().addCelebration(name, serName, comment, date, typeCelebration, pathPictureContact).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.print("Complite");
                    }

                    @Override
                    public void onComplete() {
                        System.out.print("Complite");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.print(e.getMessage().toString());
                    }
                });


    }


    public void pullListCelebration() {
        getModel().pullListCelebration()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<DataCelebrationForListDTO>>() {
                    @Override
                    public void accept(List<DataCelebrationForListDTO> datumCelebrationForLists) throws Exception {
                        getView().loadListCelebration(new CelebrationMapper().getVOObjects(datumCelebrationForLists));

                    }
                });
    }

    @Override
    public void deleteCelebration() {
        DataCelebrationForListDTO dataCelebrationForListDTO = new DataCelebrationForListDTO();
       // dataCelebrationForListDTO.firstName = getView().
        //getModel().deleteCelebration()
    }

    @Override
    public void upDateCelebration() {

    }

    @Override
    public void pullPersonalPage() {

    }


}
