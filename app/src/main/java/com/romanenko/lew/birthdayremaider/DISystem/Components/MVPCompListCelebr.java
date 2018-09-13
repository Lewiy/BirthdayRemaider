package com.romanenko.lew.birthdayremaider.DISystem.Components;

import com.romanenko.lew.birthdayremaider.DISystem.Modules.MVPMAddRemainder;
import com.romanenko.lew.birthdayremaider.DISystem.Modules.MVPMListCelebration;
import com.romanenko.lew.birthdayremaider.ListCelebrationContract;
import com.romanenko.lew.birthdayremaider.View.Fragments.FragAddReminder;
import com.romanenko.lew.birthdayremaider.View.Fragments.FragListCelebration;

import dagger.Component;

@Component(modules = MVPMListCelebration.class)
public interface MVPCompListCelebr {
    void inject( FragListCelebration viewListCelebration);
    void inject(ListCelebrationContract.ModelListBirthday modelListBirthday);

}
