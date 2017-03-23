package com.cjlib.cleanarchitecture.login.di;


import com.cjlib.cleanarchitecture.components.data.Prefs;
import com.cjlib.cleanarchitecture.components.Navigator;
import com.cjlib.cleanarchitecture.di.PerActivity;
import com.cjlib.cleanarchitecture.login.SplashActivity;
import com.cjlib.cleanarchitecture.login.presenters.SplashPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class SplashActivityModule {

    private SplashActivity activity;

    public SplashActivityModule(SplashActivity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    public SplashPresenter splashPresenter(Navigator navigator, Prefs prefs) {
        return new SplashPresenter(navigator, prefs);
    }


    @Provides
    @PerActivity
    public Navigator navigator() {
        return new Navigator(activity);
    }
}
