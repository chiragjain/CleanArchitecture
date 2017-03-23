package com.cjlib.cleanarchitecture.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.cjlib.cleanarchitecture.AppApplication;
import com.cjlib.cleanarchitecture.R;
import com.cjlib.cleanarchitecture.base.BaseActivity;
import com.cjlib.cleanarchitecture.login.di.DaggerSplashActivityComponent;
import com.cjlib.cleanarchitecture.login.di.SplashActivityComponent;
import com.cjlib.cleanarchitecture.login.di.SplashActivityModule;
import com.cjlib.cleanarchitecture.login.presenters.SplashPresenter;

import javax.inject.Inject;

public class SplashActivity extends BaseActivity {

    @Inject
    SplashPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        SplashActivityComponent component = DaggerSplashActivityComponent.builder()
                .splashActivityModule(new SplashActivityModule(this))
                .applicationComponent(AppApplication.get().getComponent())
                .build();

        component.inject(this);

        presenter.onCreate();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.onActivityResult(requestCode, resultCode, data);
    }

}
