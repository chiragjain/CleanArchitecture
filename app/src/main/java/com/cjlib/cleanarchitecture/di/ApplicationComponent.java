package com.cjlib.cleanarchitecture.di;


import com.cjlib.cleanarchitecture.components.ResourceProvider;
import com.cjlib.cleanarchitecture.components.data.Prefs;
import com.cjlib.cleanarchitecture.network.ApiService;

import dagger.Component;

@Component(modules = ApplicationModule.class)
@ApplicationScope
public interface ApplicationComponent {

    ApiService getApiService();

    ResourceProvider getResourceProvider();

    Prefs getPrefs();

}
