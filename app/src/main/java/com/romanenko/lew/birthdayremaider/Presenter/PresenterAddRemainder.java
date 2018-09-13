package com.romanenko.lew.birthdayremaider.Presenter;

import android.content.Context;

import com.romanenko.lew.birthdayremaider.AddCelebrationContract;
import com.romanenko.lew.birthdayremaider.Model.DTO.CelebrationMapper;
import com.romanenko.lew.birthdayremaider.Model.DTO.CelebrationVO;
import com.romanenko.lew.birthdayremaider.Model.DTO.DateCelebrationVO;

import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PresenterAddRemainder extends Presenter<AddCelebrationContract.ViewAddRemainder, AddCelebrationContract.ModelAddRemainder> implements AddCelebrationContract.PresenterAddRemainder {

    Context context;

   public  PresenterAddRemainder(Context context) {
        this.context = context;
    }

    private DateCelebrationVO createDateCelebration() {
        DateCelebrationVO dateCelebrationVO = new DateCelebrationVO();
        dateCelebrationVO.setDay(getView().getDay());
        dateCelebrationVO.setMonth(getView().getMonth());
        dateCelebrationVO.setYear(getView().getYear());
        return dateCelebrationVO;
    }

    private CelebrationVO createCelebration() {
        CelebrationVO celebrationVO = new CelebrationVO();
        celebrationVO.setFirstName(getView().getName());
        celebrationVO.setLastName(getView().getSurname());
        celebrationVO.setFotoPath(getView().getPathImage());
        // celebrationVO.setDate(getView().);
        celebrationVO.setComment(getView().getComment());
        return celebrationVO;
    }

    public void addRemainder() {

        //appDataBase.celebrationPersonEntityDao();
        getModel().addCelebration(CelebrationMapper
                .constructDateEntity(createCelebration()), CelebrationMapper
                .constructDateEntity(createDateCelebration()))
                .observeOn(AndroidSchedulers.mainThread())
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

    @Override
    public void viewIsReady() {
        getModel().initLocalReposetory(context);
    }
}
