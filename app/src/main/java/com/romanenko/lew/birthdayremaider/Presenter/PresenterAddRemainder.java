package com.romanenko.lew.birthdayremaider.Presenter;

import android.content.Context;
import android.widget.Toast;

import com.romanenko.lew.birthdayremaider.AddCelebrationContract;
import com.romanenko.lew.birthdayremaider.AlarmingSystem.CelebrationAlarmManager;
import com.romanenko.lew.birthdayremaider.AlarmingSystem.MyDate;
import com.romanenko.lew.birthdayremaider.Model.DTO.CelebrationMapper;
import com.romanenko.lew.birthdayremaider.Model.DTO.CelebrationVO;
import com.romanenko.lew.birthdayremaider.Model.DTO.DateCelebrationVO;
import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.QueryObjects.PersonalPageAllInformation;

import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class PresenterAddRemainder extends Presenter<AddCelebrationContract.ViewAddRemainder, AddCelebrationContract.ModelAddRemainder> implements AddCelebrationContract.PresenterAddRemainder {

    Context context;

   public  PresenterAddRemainder(Context context) {
        this.context = context;
    }




    public void setAlarm(String date ){

        CelebrationAlarmManager celebrationAlarmManager = new CelebrationAlarmManager(context);
      //  celebrationAlarmManager.setDateOnce();
    }

    private DateCelebrationVO createDateCelebration() {
        DateCelebrationVO dateCelebrationVO = new DateCelebrationVO();
        dateCelebrationVO.setDay(getView().getDay());
        dateCelebrationVO.setMonth(getView().getMonth());
        dateCelebrationVO.setYear(getView().getYear());

        if(getView().getUserId()>0)
        dateCelebrationVO.setDateId(getView().getDateId());

        return dateCelebrationVO;
    }

    private CelebrationVO createCelebration() {
        CelebrationVO celebrationVO = new CelebrationVO();
        celebrationVO.setFirstName(getView().getName());
        celebrationVO.setLastName(getView().getSurname());
        celebrationVO.setFotoPath(getView().getPathImage());
        celebrationVO.setTypeCelebration(getView().getTypeCelebration());
        celebrationVO.setComment(getView().getComment());

        if(getView().getUserId()>0)
        celebrationVO.setIdUser(getView().getUserId());

        return celebrationVO;
    }

    public void addRemainder() {

        //appDataBase.celebrationPersonEntityDao();
        DateCelebrationVO dateCelebrationVO = createDateCelebration();
        CelebrationVO celebrationVO = createCelebration();
       /* CelebrationAlarmManager celebrationAlarmManager = new CelebrationAlarmManager(context);
        MyDate myDate = new MyDate(dateCelebrationVO.getYear(), dateCelebrationVO.getMonth(), dateCelebrationVO.getDay(), 20, 31);
        celebrationAlarmManager.setDateOnce(dateCelebrationVO.getDateId(),myDate);*/

        //celebrationAlarmManager.setDateRepeating(myDate);
        getModel().addCelebration(CelebrationMapper
                .constructDateEntity(celebrationVO), CelebrationMapper
                .constructDateEntity(dateCelebrationVO))
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

    @Override
    public void editCelebration() {


        getModel().upDateCelebration(CelebrationMapper
                .constructDateEntity(createCelebration()),CelebrationMapper
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

   /* @Override
    public void editCelebrationAndShow() {
        getModel().upDateCelebrationAndShow(CelebrationMapper
                .constructDateEntity(createCelebration()),CelebrationMapper
                .constructDateEntity(createDateCelebration()))
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
                });
    }*/
}
