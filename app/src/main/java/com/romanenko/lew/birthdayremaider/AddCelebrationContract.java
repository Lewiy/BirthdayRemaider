package com.romanenko.lew.birthdayremaider;

import com.romanenko.lew.birthdayremaider.Model.IModel;
import com.romanenko.lew.birthdayremaider.Presenter.MvpPresenter;
import com.romanenko.lew.birthdayremaider.Presenter.Presenter;
import com.romanenko.lew.birthdayremaider.View.IView;

public interface AddCelebrationContract {
    interface ViewAddRemainder extends IView{

        String getName();
        String getSurname();
        int getYear();
        int getDay();
        int getMonth();
        String getPathImage();
        String getTypeCelebration();
        String getComment();
    }

    interface PresenterAddRemainder extends MvpPresenter<ViewAddRemainder,ModelAddRemainder> {

    }

    interface ModelAddRemainder extends IModel{}

}
