package com.cjlib.cleanarchitecture.login.di;


import com.cjlib.cleanarchitecture.components.ResourceProvider;
import com.cjlib.cleanarchitecture.components.data.Prefs;
import com.cjlib.cleanarchitecture.components.Navigator;
import com.cjlib.cleanarchitecture.di.PerActivity;
import com.cjlib.cleanarchitecture.login.LoginActivity;
import com.cjlib.cleanarchitecture.login.presenters.LoginPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginActivityModule {

    private LoginActivity activity;

    public LoginActivityModule(LoginActivity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    public LoginPresenter loginPresenter(Navigator navigator,
                                         ResourceProvider provider, Prefs prefs) {
        return new LoginPresenter(navigator, provider, prefs);
    }

    @Provides
    @PerActivity
    public Navigator navigator() {
        return new Navigator(activity);
    }

}
