package com.romanenko.lew.birthdayremaider.DISystem.Components;

import com.romanenko.lew.birthdayremaider.DISystem.Modules.DataBaseModule;
import com.romanenko.lew.birthdayremaider.Model.ModelListCelebration;

import dagger.Component;

@DataBaseSingleScope
@Component(dependencies = AppComponent.class, modules = DataBaseModule.class)
public interface DataBaseComponent {
   // AppDataBase getAppDataBase();
    void inject(ModelListCelebration modelListCelebration);
}
