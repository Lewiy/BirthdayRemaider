package com.romanenko.lew.birthdayremaider.DISystem.Components;

import com.romanenko.lew.birthdayremaider.DISystem.Modules.DataBaseModule;
import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.CelebrationPersonEntity;
import com.romanenko.lew.birthdayremaider.Model.ModelAddRemainder;
import com.romanenko.lew.birthdayremaider.Model.ModelEditCelebration;
import com.romanenko.lew.birthdayremaider.Model.ModelHomeScreen;
import com.romanenko.lew.birthdayremaider.Model.ModelListCelebration;
import com.romanenko.lew.birthdayremaider.Model.ModelNotif;

import dagger.Component;

@DataBaseSingleScope
@Component(dependencies = AppComponent.class, modules = DataBaseModule.class)
public interface DataBaseComponent {
   // AppDataBase getAppDataBase();
    void inject(ModelListCelebration modelListCelebration);
    void inject(ModelAddRemainder modelAddRemainder);
    void inject(ModelEditCelebration modelEditCelebration);
    void inject(ModelHomeScreen modelHomeScreen);
    void inject(ModelNotif modelNotif);
    CelebrationPersonEntity getCelebrationPersonEntity();
}
