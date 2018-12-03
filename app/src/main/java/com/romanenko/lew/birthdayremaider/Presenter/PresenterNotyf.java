package com.romanenko.lew.birthdayremaider.Presenter;

import android.content.Context;
import android.content.SharedPreferences;

import com.romanenko.lew.birthdayremaider.AlarmingSystem.MyDate;
import com.romanenko.lew.birthdayremaider.ListCelebrationContract;
import com.romanenko.lew.birthdayremaider.Model.DTO.CelebrationMapper;
import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.QueryObjects.DataCelebrationForListDTO;
import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.QueryObjects.NotifyDTO;
import com.romanenko.lew.birthdayremaider.NotificationContract;
import com.romanenko.lew.birthdayremaider.util.PreferencesManager;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static com.romanenko.lew.birthdayremaider.View.Fragments.FragmentSettings.APP_PREFERENCES;

public class PresenterNotyf
        extends Presenter<NotificationContract.ViewNotif, NotificationContract.ModelNotif>
        implements NotificationContract.PresenterNotif {

    List<NotifyDTO> celebrationsList;
    List<NotifyDTO> todayCelebrations = new LinkedList<>();
    private SharedPreferences mSettings;

    public PresenterNotyf(Context context) {
        this.context = context;
        mSettings = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
    }

    Context context;


    @Override
    public void viewIsReady() {
        getModel().initLocalReposetory(context);
    }


    private void alarmChecker() {
        for (NotifyDTO celebration : celebrationsList) {
            if (isCelebrationToday(celebration)) {
                todayCelebrations.add(celebration);
            }

        }

        getView().setNotifData(todayCelebrations);
    }

    private boolean isCelebrationToday(NotifyDTO celebration) {
        List<LocalDate> datesConverted = convertDateToSettingsForm(celebration);
        LocalDate dateNow = new LocalDate();
        for (LocalDate isToDayCelebr : datesConverted) {

            if (dateNow.equals(isToDayCelebr)) {
                return true;
            }
        }

        return false;

    }

    private List<LocalDate> convertDateToSettingsForm(NotifyDTO celebration) {
        List<LocalDate> dates = new ArrayList<>();
        LocalDate dateNow = new LocalDate();
        LocalDate dateCelebrationTransformed = new LocalDate(dateNow.getYear(), celebration.month, celebration.day);
        dates.add(dateCelebrationTransformed);
        HashMap<Integer, Boolean> settings = PreferencesManager.getSettingsPrefDay(mSettings);
        for (Map.Entry<Integer, Boolean> entry : settings.entrySet()) {
            if (entry.getValue()) {
                int days = entry.getKey();
                dates.add(dateCelebrationTransformed.minusDays(days));
            }
        }
        return dates;
    }

    @Override
    public void loadDataNotif() {
        getModel().pullListCelebration()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<DataCelebrationForListDTO>>() {
                    @Override
                    public void accept(List<DataCelebrationForListDTO> datumCelebrationForLists) throws Exception {
                        // getView().loadListCelebration(new CelebrationMapper().getVOObjects(datumCelebrationForLists));
                        List<NotifyDTO> notifyDTOS = converter(datumCelebrationForLists);
                        celebrationsList = notifyDTOS;
                        alarmChecker();

                    }
                });
    }

    private List<NotifyDTO> converter(List<DataCelebrationForListDTO> dataCelebrationForListDTOS) {
        List<NotifyDTO> notifyDTOS = new ArrayList<>();

        for (DataCelebrationForListDTO dataCelebrationForListDTO : dataCelebrationForListDTOS) {
            NotifyDTO notifyDTO = new NotifyDTO();
            notifyDTO.firstName = dataCelebrationForListDTO.firstName;
            notifyDTO.lastName = dataCelebrationForListDTO.lastName;
            notifyDTO.userId = dataCelebrationForListDTO.userId;
            notifyDTO.year = dataCelebrationForListDTO.year;
            notifyDTO.month = dataCelebrationForListDTO.month;
            notifyDTO.day = dataCelebrationForListDTO.day;
            notifyDTO.fotoPath = dataCelebrationForListDTO.fotoPath;
            notifyDTO.typeCelebr = dataCelebrationForListDTO.typeCelebration;
            notifyDTOS.add(notifyDTO);
        }

        return notifyDTOS;
    }
}
