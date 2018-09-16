package com.romanenko.lew.birthdayremaider.DISystem.Components;

import com.romanenko.lew.birthdayremaider.DISystem.Modules.MVPMAddRemainder;
import com.romanenko.lew.birthdayremaider.DISystem.Modules.MVPMEditCelebration;
import com.romanenko.lew.birthdayremaider.View.Fragments.FragEditCelebration;

import dagger.Component;

@Component(modules = MVPMEditCelebration.class)
public interface MVPCompEditCelebr {
    void inject(FragEditCelebration fragEditCelebration);
}
