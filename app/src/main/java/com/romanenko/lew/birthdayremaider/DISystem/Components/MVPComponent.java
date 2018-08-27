package com.romanenko.lew.birthdayremaider.DISystem.Components;

import com.romanenko.lew.birthdayremaider.DISystem.Modules.MVPModule;
import com.romanenko.lew.birthdayremaider.ListBirthdayContract;
import com.romanenko.lew.birthdayremaider.View.Fragments.FragListBirthdays;

import dagger.Component;

@Component(modules = MVPModule.class)
public interface MVPComponent {
    void inject( FragListBirthdays viewListBirthday);
}
