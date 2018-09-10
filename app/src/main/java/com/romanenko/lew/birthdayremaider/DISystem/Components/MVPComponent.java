package com.romanenko.lew.birthdayremaider.DISystem.Components;

import com.romanenko.lew.birthdayremaider.DISystem.Modules.MVPModule;
import com.romanenko.lew.birthdayremaider.ListCelebrationContract;
import com.romanenko.lew.birthdayremaider.View.Fragments.FragListCelebration;

import dagger.Component;

@Component(modules = MVPModule.class)
public interface MVPComponent {
    void inject( FragListCelebration viewListCelebration);
    void inject(ListCelebrationContract.ModelListBirthday modelListBirthday);
}
