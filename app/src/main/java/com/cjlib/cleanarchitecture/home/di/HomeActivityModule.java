package com.cjlib.cleanarchitecture.home.di;


import com.cjlib.cleanarchitecture.components.ResourceProvider;
import com.cjlib.cleanarchitecture.components.Navigator;
import com.cjlib.cleanarchitecture.di.PerActivity;
import com.cjlib.cleanarchitecture.home.HomeActivity;
import com.cjlib.cleanarchitecture.home.interactors.MovieInteractor;
import com.cjlib.cleanarchitecture.home.presenters.HomePresenter;
import com.cjlib.cleanarchitecture.network.ApiService;

import dagger.Module;
import dagger.Provides;

@Module
public class HomeActivityModule {

    private HomeActivity activity;

    public HomeActivityModule(HomeActivity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    public HomePresenter homePresenter(MovieInteractor interactor,
                                       ResourceProvider provider, Navigator navigator) {
        return new HomePresenter(interactor, navigator, provider);
    }

    @Provides
    @PerActivity
    public Navigator navigator() {
        return new Navigator(activity);
    }

    @Provides
    @PerActivity
    public MovieInteractor movieInteractor(ApiService service) {
        return new MovieInteractor(service);
    }

}
