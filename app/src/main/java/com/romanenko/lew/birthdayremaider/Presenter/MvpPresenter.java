package com.romanenko.lew.birthdayremaider.Presenter;

import com.romanenko.lew.birthdayremaider.Model.IModel;
import com.romanenko.lew.birthdayremaider.View.IView;

public interface MvpPresenter <V extends IView,M extends IModel>{

    void attachView(V IView);
    void attachModel(M IModel);

    void viewIsReady();

    void detachView();
    void detachModel();

    void destroy();
}
