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
    private Context context;

    @Inject
    public NotificationContract.PresenterNotif presenter;

    public DataNotifReceiver(Context context) {
        this.context = context;
        DaggerMVPCompNotification
                .builder()
                .mVPMNotification(new MVPMNotification(this, new PresenterNotyf(context)))
                .build()
                .inject(this);

        presenter.attachView(this);
        presenter.attachModel(new ModelNotif());
        presenter.viewIsReady();
    }

    public void runNotification() {
        presenter.loadDataNotif();
    }


    @Override
    public void setNotifData(List<NotifyDTO> notifData) {
        this.notifData = notifData;
        CelebrNotificationManager.notifyCelebr(context, NotificationChannels.ANDROID_CHANNEL_ID, notifData);
    }


    @Override
    public void showView(String error) {

    }
}
