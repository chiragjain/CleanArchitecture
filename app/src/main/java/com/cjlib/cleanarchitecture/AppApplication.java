package com.cjlib.cleanarchitecture;

import android.app.Application;

import com.cjlib.cleanarchitecture.components.data.Prefs;
import com.cjlib.cleanarchitecture.di.ApplicationComponent;
import com.cjlib.cleanarchitecture.di.ContextModule;
import com.cjlib.cleanarchitecture.di.DaggerApplicationComponent;


public class AppApplication extends Application {

    private ApplicationComponent component;

    private static AppApplication appApplication;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerApplicationComponent.builder()
                .contextModule(new ContextModule(this)).build();

        appApplication = this;
    }

    public ApplicationComponent getComponent() {
        return component;
    }

    public static AppApplication get() {
        return appApplication;
    }

    public static Prefs getPrefs() {
        return get().component.getPrefs();
    }

}
