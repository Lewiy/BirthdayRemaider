package com.romanenko.lew.birthdayremaider;

import android.content.Context;

import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.QueryObjects.NotifyDTO;
import com.romanenko.lew.birthdayremaider.Model.IModel;
import com.romanenko.lew.birthdayremaider.Presenter.MvpPresenter;
import com.romanenko.lew.birthdayremaider.View.IView;

import java.util.Date;
import java.util.List;

import io.reactivex.Flowable;

public interface NotificationContract {

    interface ViewNotif extends IView {

        void setName(String name);
        void setSurname(String surname);
        void setPhoto(String photo);
        void setTypeCelebr(String typeCelebr);
        void setDate(String date);

        void setNotifData(List<NotifyDTO> notifData);
    }

    interface PresenterNotif extends MvpPresenter<NotificationContract.ViewNotif, NotificationContract.ModelNotif> {

        void loadDataNotif(int day,int month,int year);
    }

    interface ModelNotif extends IModel {
        void initLocalReposetory(Context context);
        Flowable<List<NotifyDTO>> loadCelebrs(int day,int month,int yaer);
    }
}
