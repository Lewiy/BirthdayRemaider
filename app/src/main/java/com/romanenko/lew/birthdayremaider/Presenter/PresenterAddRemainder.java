package com.romanenko.lew.birthdayremaider.Presenter;

import com.romanenko.lew.birthdayremaider.AddCelebrationContract;
import com.romanenko.lew.birthdayremaider.Model.DTO.CelebrationVO;
import com.romanenko.lew.birthdayremaider.Model.DTO.DateCelebrationVO;

public class PresenterAddRemainder extends Presenter<AddCelebrationContract.ViewAddRemainder, AddCelebrationContract.ModelAddRemainder> implements AddCelebrationContract.PresenterAddRemainder {


    @Override
    public void attachView(AddCelebrationContract.ViewAddRemainder IView) {

    }

    @Override
    public void attachModel(AddCelebrationContract.ModelAddRemainder IModel) {

    }

    @Override
    public void viewIsReady() {
        //= getView();
    }

    @Override
    public void detachView() {

    }

    @Override
    public void detachModel() {

    }

    @Override
    public void destroy() {

    }

    private DateCelebrationVO createDateCelebration() {
        DateCelebrationVO dateCelebrationVO = new DateCelebrationVO();
        dateCelebrationVO.setDay(getView().getDay());
        dateCelebrationVO.setMonth(getView().getMonth());
        dateCelebrationVO.setYear(getView().getYear());
        return dateCelebrationVO;
    }

    private CelebrationVO createCelebration(){
        CelebrationVO celebrationVO = new CelebrationVO();
        celebrationVO.setFirstName(getView().getName());
        celebrationVO.setLastName(getView().getSurname());
        celebrationVO.setFotoPath(getView().getPathImage());
       // celebrationVO.setDate(getView().);
        return celebrationVO;
    }



}
