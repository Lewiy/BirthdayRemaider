package com.romanenko.lew.birthdayremaider.Presenter;

import com.romanenko.lew.birthdayremaider.View.IView;

public interface MvpPresenter <V extends IView>{

    void attachView(V IView);

    void viewIsReady();

    void detachView();

    void destroy();
}
