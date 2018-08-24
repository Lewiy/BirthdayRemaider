package com.romanenko.lew.birthdayremaider.DISystem.Components;

import com.romanenko.lew.birthdayremaider.DISystem.Modules.DataBaseModule;
import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.AppDataBase;

import dagger.Component;

@DataBaseSingleScope
@Component(modules = DataBaseModule.class)
public interface DataBaseComponent {
    AppDataBase getAppDataBase();
}
