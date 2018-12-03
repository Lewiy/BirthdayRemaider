package com.romanenko.lew.birthdayremaider.Presenter;

import android.content.Context;

import com.romanenko.lew.birthdayremaider.EditProfileCelebration;
import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.QueryObjects.PersonalPageAllInformation;

import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class PresenterEditCelebration extends Presenter<EditProfileCelebration.ViewEditCelebration
        , EditProfileCelebration.ModelEditCelebration>
        implements EditProfileCelebration.PresenterEditCelebration {

    Context context;

    private PersonalPageAllInformation personalPageAllInfo;

    public PresenterEditCelebration(Context context) {
        this.context = context;
    }


    @Override
    public void viewIsReady() {
        getModel().initLocalRepository(context);
    }



    @Override
    public void deleteCelebration() {

       // AlarmCreator alarmCreator = new AlarmCreator(context);
     //   alarmCreator.deleteAccaunt((int) personalPageAllInfo.idTemporary);
        getModel().deleteCelebration((int) personalPageAllInfo.userId, (int) personalPageAllInfo.dateId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
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
    public void pullPersonalPage(int idUser) {
        getModel().pullPersonalPage(String.valueOf(idUser))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<PersonalPageAllInformation>() {
                    @Override
                    public void accept(PersonalPageAllInformation personalPageAllInformation) throws Exception {
                        personalPageAllInfo = personalPageAllInformation;
                        //getView().loadListCelebration(new CelebrationMapper().getVOObjects(datumCelebrationForLists));
                        getView().setName(personalPageAllInformation.firstName,personalPageAllInformation.lastName);
                       /* Toast toast = Toast.makeText(context,
                                personalPageAllInformation.firstName, Toast.LENGTH_SHORT);
                        toast.show();*/
                        getView().setComment(personalPageAllInformation.comment);
                        getView().setTypeCelebration(personalPageAllInformation.typeCelebration);
                        getView().setDate(personalPageAllInformation.day + "/" +
                                personalPageAllInformation.month + "/" +
                                personalPageAllInformation.year);
                        getView().setPictureContact(personalPageAllInformation.fotoPath);
                        getView().setIdUser((int)personalPageAllInformation.userId);
                        getView().setIdDate((int)personalPageAllInformation.dateId);
                    }
                });
    }

    @Override
    public void updatePageSync(int idUser) {
       /* getModel().pullPersonalPage(String.valueOf(idUser))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<PersonalPageAllInformation>() {
                    @Override
                    public void accept(PersonalPageAllInformation personalPageAllInformation) throws Exception {
                        personalPageAllInfo = personalPageAllInformation;
                        //getView().loadListCelebration(new CelebrationMapper().getVOObjects(datumCelebrationForLists));
                        getView().setName(personalPageAllInformation.firstName,personalPageAllInformation.lastName);
                        Toast toast = Toast.makeText(context,
                                personalPageAllInformation.firstName, Toast.LENGTH_SHORT);
                        toast.show();
                        getView().setComment(personalPageAllInformation.comment);
                        getView().setTypeCelebration(personalPageAllInformation.typeCelebration);
                        getView().setDate(personalPageAllInformation.day + "/" +
                                personalPageAllInformation.month + "/" +
                                personalPageAllInformation.year);
                        getView().setPictureContact(personalPageAllInformation.fotoPath);
                        getView().setIdUser((int)personalPageAllInformation.userId);
                        getView().setIdDate((int)personalPageAllInformation.dateId);
                    }
                });*/
    }
}
