package com.romanenko.lew.birthdayremaider.Presenter;

import com.romanenko.lew.birthdayremaider.Model.IModel;
import com.romanenko.lew.birthdayremaider.View.IView;

public abstract class Presenter<T extends IView, M extends IModel> implements MvpPresenter<T, M> {
    private T view;
    private M model;

    @Override
    public void attachView(T mvpView) {
        view = mvpView;
    }

    @Override
    public void attachModel(M mvpModel) {
        model = mvpModel;
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void detachModel() {
        model = null;
    }


    public T getView() {
        return view;
    }

    public M getModel() {
        return model;
    }

    protected boolean isViewAttached() {
        return view != null;
    }

    @Override
    public void destroy() {

    }

}
