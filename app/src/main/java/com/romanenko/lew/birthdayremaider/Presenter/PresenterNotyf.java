package com.romanenko.lew.birthdayremaider.Presenter;

import android.content.Context;

import com.romanenko.lew.birthdayremaider.ListCelebrationContract;
import com.romanenko.lew.birthdayremaider.Model.DTO.CelebrationMapper;
import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.QueryObjects.DataCelebrationForListDTO;
import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.QueryObjects.NotifyDTO;
import com.romanenko.lew.birthdayremaider.NotificationContract;

import java.util.Date;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class PresenterNotyf
        extends Presenter<NotificationContract.ViewNotif, NotificationContract.ModelNotif>
        implements NotificationContract.PresenterNotif {

    public PresenterNotyf(Context context) {
        this.context = context;
    }

    Context context;


    @Override
    public void viewIsReady() {
        getModel().initLocalReposetory(context);
    }


    @Override
    public void loadDataNotif(int day, int month, int yaer) {
        getModel().loadCelebrs(day, month, yaer).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<NotifyDTO>>() {
                    @Override
                    public void accept(List<NotifyDTO> notifyDTOS) throws Exception {
                        getView().setNotifData(notifyDTOS);

                    }
                });
    }
}
