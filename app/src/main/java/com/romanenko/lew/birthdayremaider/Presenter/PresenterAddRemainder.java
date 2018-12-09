package com.romanenko.lew.birthdayremaider.Presenter;

import android.content.Context;

import com.romanenko.lew.birthdayremaider.AddCelebrationContract;
import com.romanenko.lew.birthdayremaider.Model.DTO.CelebrationMapper;
import com.romanenko.lew.birthdayremaider.Model.DTO.CelebrationVO;
import com.romanenko.lew.birthdayremaider.Model.DTO.DateCelebrationVO;
import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.QueryObjects.PersonalPageAllInformation;

import java.util.Calendar;
import java.util.GregorianCalendar;

import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class PresenterAddRemainder extends Presenter<AddCelebrationContract.ViewAddRemainder, AddCelebrationContract.ModelAddRemainder> implements AddCelebrationContract.PresenterAddRemainder {

    Context context;

    public PresenterAddRemainder(Context context) {
        this.context = context;
    }


    private DateCelebrationVO createDateCelebration() {
        DateCelebrationVO dateCelebrationVO = new DateCelebrationVO();
        dateCelebrationVO.setDay(getView().getDay());
        dateCelebrationVO.setMonth(getView().getMonth());
        dateCelebrationVO.setYear(getView().getYear());

        if (getView().getUserId() > 0)
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
        celebrationVO.setIdTemporary(getView().getNumberOfRows());
        if (getView().getUserId() > 0)
            celebrationVO.setIdUser(getView().getUserId());
        return celebrationVO;
    }

    private String createDateCelebrForIdentification(CelebrationVO celebrationVO, DateCelebrationVO dateCelebrationVO) {
        Calendar myCalendar = new GregorianCalendar(dateCelebrationVO.getYear(), dateCelebrationVO.getMonth(), dateCelebrationVO.getDay());
        return myCalendar.toString();
    }

    @Override
    public void getNumberOfRows() {
        getModel()
                .getNumberOfRows()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer numberOfRows) throws Exception {
                        getView().setNumberOfRows(numberOfRows);
                    }
                });
    }

    public void addRemainder() {
        DateCelebrationVO dateCelebrationVO = createDateCelebration();
        CelebrationVO celebrationVO = createCelebration();


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
    public void pullPersonalPage(int idUser) {
        getModel().pullPersonalPage(String.valueOf(idUser))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<PersonalPageAllInformation>() {
                    @Override
                    public void accept(PersonalPageAllInformation personalPageAllInformation) throws Exception {
                        //  personalPageAllInfo = personalPageAllInformation;
                        //getView().loadListCelebration(new CelebrationMapper().getVOObjects(datumCelebrationForLists));
                        getView().setName(personalPageAllInformation.firstName);
                        getView().setSurname(personalPageAllInformation.lastName);
                       /* Toast toast = Toast.makeText(context,
                                personalPageAllInformation.firstName, Toast.LENGTH_SHORT);
                        toast.show();*/
                        getView().setComment(personalPageAllInformation.comment);
                        getView().setTypeCelebration(personalPageAllInformation.typeCelebration);

                        getView().setDate(Integer.parseInt(personalPageAllInformation.year)
                                ,Integer.parseInt(personalPageAllInformation.month)
                        ,Integer.parseInt(personalPageAllInformation.day));


                        getView().setPathImage(personalPageAllInformation.fotoPath);
                        getView().setIdUser((int) personalPageAllInformation.userId);
                        getView().setIdDate((int) personalPageAllInformation.dateId);
                        getView().setNumberOfRows(personalPageAllInformation.idTemporary);
                    }
                });
    }
}
