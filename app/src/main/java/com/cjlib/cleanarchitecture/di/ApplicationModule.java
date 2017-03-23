package com.cjlib.cleanarchitecture.di;

import android.content.Context;

import com.cjlib.cleanarchitecture.components.ResourceProvider;
import com.cjlib.cleanarchitecture.components.data.Prefs;
import com.cjlib.cleanarchitecture.network.ApiService;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = NetworkModule.class)
public class ApplicationModule {

    private static final String BASE_URL = "http://www.omdbapi.com/";

    @Provides
    @ApplicationScope
    public ApiService apiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }

    @Provides
    @ApplicationScope
    public Retrofit retrofit(OkHttpClient httpClient) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(httpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()).build();
    }

    @Provides
    @ApplicationScope
    public ResourceProvider resourceProvider(@ApplicationContext Context context) {
        return new ResourceProvider(context);
    }

    @Provides
    @ApplicationScope
    public Prefs prefs(@ApplicationContext Context context) {
        return new Prefs(context);
    }
}
