package com.romanenko.lew.birthdayremaider;

import android.content.Context;

import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.QueryObjects.CelebrListNameDateFotoDTO;
import com.romanenko.lew.birthdayremaider.Model.IModel;
import com.romanenko.lew.birthdayremaider.Presenter.MvpPresenter;
import com.romanenko.lew.birthdayremaider.View.IView;

import java.util.List;

import io.reactivex.Flowable;

public interface SettingsContract {

    interface ViewSettings extends IView {

    }

    interface PresenterSettings  extends MvpPresenter<SettingsContract.ViewSettings,SettingsContract.ModelSettings> {

    }

    interface ModelSettings  extends IModel {

    }
}
