package com.romanenko.lew.birthdayremaider.DISystem.Modules;

import com.romanenko.lew.birthdayremaider.ListCelebrationContract;
import com.romanenko.lew.birthdayremaider.NotificationContract;

import dagger.Module;
import dagger.Provides;
@Module
public class MVPMNotification {
    private NotificationContract.ViewNotif view;
    private NotificationContract.PresenterNotif presenter;
    private NotificationContract.ModelNotif model;

    public MVPMNotification(NotificationContract.ViewNotif view, NotificationContract.PresenterNotif presenter) {
        this.view = view;
        this.presenter = presenter;
    }
    public MVPMNotification(NotificationContract.ModelNotif model) {
        this.model = model;
    }

    @Provides
    NotificationContract.ViewNotif  provideViewListBirthday() {
        return view;
    }


    @Provides
    NotificationContract.PresenterNotif providePresenterListBirthday() {

        //  presenter.attachView(view);
        //   presenter.attachModel(model);

        return presenter;
    }

    @Provides
    NotificationContract.ModelNotif provideModel(){
        return model;
    }
}
