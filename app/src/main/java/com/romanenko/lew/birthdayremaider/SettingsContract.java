package com.romanenko.lew.birthdayremaider;

import com.romanenko.lew.birthdayremaider.Model.IModel;
import com.romanenko.lew.birthdayremaider.Presenter.MvpPresenter;
import com.romanenko.lew.birthdayremaider.View.IView;

public interface SettingsContract {

    interface ViewSettings extends IView {

    }

    interface PresenterSettings  extends MvpPresenter<SettingsContract.ViewSettings,SettingsContract.ModelSettings> {

    }

    interface ModelSettings  extends IModel {

    }
}
