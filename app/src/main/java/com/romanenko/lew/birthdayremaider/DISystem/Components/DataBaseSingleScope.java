package com.romanenko.lew.birthdayremaider.DISystem.Components;

import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.AppDataBase;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

import dagger.Component;

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface DataBaseSingleScope {
    //AppDataBase getAppDataBase();
}
