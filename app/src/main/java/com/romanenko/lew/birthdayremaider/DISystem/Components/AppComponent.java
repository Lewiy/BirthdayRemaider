package com.romanenko.lew.birthdayremaider.DISystem.Components;

import android.app.Application;
import android.content.Context;

import com.romanenko.lew.birthdayremaider.DISystem.Modules.AppModule;
import com.romanenko.lew.birthdayremaider.DISystem.Modules.ContextModule;
import com.romanenko.lew.birthdayremaider.MyApp;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, ContextModule.class})
public interface AppComponent {
    void inject(MyApp initApplication);
    Context getContext();
    Application getApplication();
}
