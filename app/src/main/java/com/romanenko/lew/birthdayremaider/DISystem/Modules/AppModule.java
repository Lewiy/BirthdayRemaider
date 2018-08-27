package com.romanenko.lew.birthdayremaider.DISystem.Modules;

import android.app.Application;

import com.romanenko.lew.birthdayremaider.MyApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private MyApp myApp;

    public AppModule(MyApp initApplication) {
        this.myApp = initApplication;
    }

    @Provides
    @Singleton
    public Application provideApplication() {
        return myApp;
    }
}
