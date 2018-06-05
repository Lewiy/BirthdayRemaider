package com.romanenko.lew.birthdayremaider.Presenter;

import com.romanenko.lew.birthdayremaider.Model.POJO.IModel;
import com.romanenko.lew.birthdayremaider.Model.POJO.MvpModel;
import com.romanenko.lew.birthdayremaider.View.IView;

public abstract class Presenter <T extends IView, M extends IModel> implements MvpPresenter<T>,MvpModel<M> {
    private T view;
    private M model;

    @Override
    public void attachView(T mvpView) {
        view = mvpView;
    }

    @Override
    public void detachView() {
        view = null;
    }

    public T getView() {
        return view;
    }

    protected boolean isViewAttached() {
        return view != null;
    }

    @Override
    public void destroy() {

    }

}
