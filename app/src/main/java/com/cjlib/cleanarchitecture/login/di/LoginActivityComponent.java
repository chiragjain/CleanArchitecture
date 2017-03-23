package com.cjlib.cleanarchitecture.login.di;

import com.cjlib.cleanarchitecture.di.ApplicationComponent;
import com.cjlib.cleanarchitecture.di.PerActivity;
import com.cjlib.cleanarchitecture.login.LoginActivity;

import dagger.Component;

@Component(modules = LoginActivityModule.class, dependencies = ApplicationComponent.class)
@PerActivity
public interface LoginActivityComponent {

    void inject(LoginActivity activity);

}
