package com.romanenko.lew.birthdayremaider;

import android.app.Application;
import android.content.Context;

import com.romanenko.lew.birthdayremaider.DISystem.Components.AppComponent;
import com.romanenko.lew.birthdayremaider.DISystem.Components.DaggerAppComponent;
import com.romanenko.lew.birthdayremaider.DISystem.Modules.AppModule;
import com.romanenko.lew.birthdayremaider.DISystem.Modules.ContextModule;

public class MyApp extends Application {


    private AppComponent component;

    public static MyApp get(Context context) {
        return (MyApp) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .contextModule(new ContextModule(this))
                .build();
    }

    public AppComponent component() {
        return component;
    }
}
