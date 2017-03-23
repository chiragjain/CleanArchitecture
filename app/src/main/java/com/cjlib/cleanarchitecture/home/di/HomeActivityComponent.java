package com.cjlib.cleanarchitecture.home.di;

import com.cjlib.cleanarchitecture.di.ApplicationComponent;
import com.cjlib.cleanarchitecture.di.PerActivity;
import com.cjlib.cleanarchitecture.home.HomeActivity;

import dagger.Component;

@Component(modules = HomeActivityModule.class, dependencies = ApplicationComponent.class)
@PerActivity
public interface HomeActivityComponent {

    void inject(HomeActivity activity);

}
