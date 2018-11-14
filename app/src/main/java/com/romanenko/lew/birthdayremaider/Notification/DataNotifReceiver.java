package com.romanenko.lew.birthdayremaider.Notification;

import android.content.Context;

import com.romanenko.lew.birthdayremaider.AlarmingSystem.MyDate;
import com.romanenko.lew.birthdayremaider.DISystem.Components.AppComponent;
import com.romanenko.lew.birthdayremaider.DISystem.Components.DaggerMVPCompNotification;
import com.romanenko.lew.birthdayremaider.DISystem.Modules.MVPMNotification;
import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.QueryObjects.NotifyDTO;
import com.romanenko.lew.birthdayremaider.Model.ModelNotif;
import com.romanenko.lew.birthdayremaider.NotificationContract;
import com.romanenko.lew.birthdayremaider.Presenter.PresenterNotyf;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

public class DataNotifReceiver implements NotificationContract.ViewNotif {

    public List<NotifyDTO> getNotifData() {
        return notifData;
    }

    private List<NotifyDTO> notifData;
    private AppComponent appComponent;
    private Context context;
    private CelebrNotificationManager celebrNotificationManager;

    @Inject
    public NotificationContract.PresenterNotif presenter;

    public DataNotifReceiver(Context context, MyDate date) {

        this.context = context;
       /* dataBaseComponent = DaggerDataBaseComponent.builder().appComponent(MyApp.get(context).component())
                .dataBaseModule(new DataBaseModule())
                //.contextModule(new ContextModule(context))
                .build();

        dataBaseComponent.inject(this);

        appComponent = DaggerAppComponent.builder().appModule()*/
        //     DaggerMVPCompAddRemain

        DaggerMVPCompNotification
                .builder()
                .mVPMNotification(new MVPMNotification(this, new PresenterNotyf(context)))
                .build()
                .inject(this);

        presenter.attachView(this);
        presenter.attachModel(new ModelNotif());
        presenter.viewIsReady();
        presenter.loadDataNotif(date.getDay(), date.getMonth(), date.getYear());
    }

    public void setCelebrNotificationManager(CelebrNotificationManager celebrNotificationManager) {
        this.celebrNotificationManager = celebrNotificationManager;
    }

    @Override
    public void setName(String name) {

    }

    @Override
    public void setSurname(String surname) {

    }

    @Override
    public void setPhoto(String photo) {

    }

    @Override
    public void setTypeCelebr(String typeCelebr) {

    }

    @Override
    public void setDate(String date) {

    }

    @Override
    public void setNotifData(List<NotifyDTO> notifData) {
        this.notifData = notifData;
        CelebrNotificationManager.notifyCelebr(context, "Test", NotificationChannels.ANDROID_CHANNEL_ID, notifData);
    }


    @Override
    public void showView(String error) {

    }
}
