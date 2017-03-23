package com.cjlib.cleanarchitecture.di;


import android.content.Context;

import java.io.File;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

@Module(includes = ContextModule.class)
public class NetworkModule {

    @Provides
    @ApplicationScope
    public OkHttpClient okHttpClient(HttpLoggingInterceptor logging, Cache cache) {

        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();


        if (!httpClientBuilder.interceptors().contains(logging)) {
            httpClientBuilder.addInterceptor(logging);
        }


        httpClientBuilder.cache(cache);

        return httpClientBuilder.build();
    }

    @Provides
    @ApplicationScope
    public Cache cache(File cacheFile) {
        // 10 MB Cache
        return new Cache(cacheFile, 10 * 1000 * 1000);
    }

    @Provides
    @ApplicationScope
    public File cacheFile(@ApplicationContext Context context) {
        return new File(context.getCacheDir(), "okhttp3.cache");
    }

    @Provides
    @ApplicationScope
    HttpLoggingInterceptor loggingInterceptor() {
        return new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY);
    }

}
