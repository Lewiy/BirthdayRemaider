package com.romanenko.lew.birthdayremaider.DISystem.Components;

import com.romanenko.lew.birthdayremaider.DISystem.Modules.MVPMNotification;
import com.romanenko.lew.birthdayremaider.Notification.DataNotifReceiver;

import dagger.Component;

@Component(modules = MVPMNotification.class)
public interface MVPCompNotification {

    void inject(DataNotifReceiver dataNotifReceiver);
}
