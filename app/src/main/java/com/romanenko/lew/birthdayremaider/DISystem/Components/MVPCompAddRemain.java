package com.romanenko.lew.birthdayremaider.DISystem.Components;

import com.romanenko.lew.birthdayremaider.DISystem.Modules.MVPMAddRemainder;
import com.romanenko.lew.birthdayremaider.DISystem.Modules.MVPMListCelebration;
import com.romanenko.lew.birthdayremaider.View.Fragments.FragAddReminder;

import dagger.Component;

@Component(modules = MVPMAddRemainder.class)
public interface MVPCompAddRemain {
    void inject(FragAddReminder fragAddReminder);
}
