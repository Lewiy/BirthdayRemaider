package com.romanenko.lew.birthdayremaider.DISystem.Components;

import com.romanenko.lew.birthdayremaider.DISystem.Modules.MVPMHomeScreen;
import com.romanenko.lew.birthdayremaider.View.Fragments.FragHomeScreen;

import dagger.Component;

@Component(modules = MVPMHomeScreen.class)
public interface MVPHomeScreen {
    void inject(FragHomeScreen view);
}
