package com.cjlib.cleanarchitecture.login.di;

import com.cjlib.cleanarchitecture.di.ApplicationComponent;
import com.cjlib.cleanarchitecture.di.PerActivity;
import com.cjlib.cleanarchitecture.login.SplashActivity;

import dagger.Component;

@Component(modules = SplashActivityModule.class, dependencies = ApplicationComponent.class)
@PerActivity
public interface SplashActivityComponent {

    void inject(SplashActivity activity);
}
