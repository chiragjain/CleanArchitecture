package com.cjlib.cleanarchitecture.login.presenters;

import android.app.Activity;
import android.content.Intent;

import com.cjlib.cleanarchitecture.Utils;
import com.cjlib.cleanarchitecture.base.Presenter;
import com.cjlib.cleanarchitecture.components.data.Prefs;
import com.cjlib.cleanarchitecture.components.Navigator;

public class SplashPresenter implements Presenter {

    private static final int REQUEST_CODE_LOGIN = 1000;

    private Navigator navigator;

    private Prefs prefs;

    public SplashPresenter(Navigator navigator, Prefs prefs) {
        this.navigator = navigator;
        this.prefs = prefs;
    }

    @Override
    public void onCreate() {
        decideNavigation();
    }

    private void decideNavigation() {
        if (isAccessTokenAvailable()) {
            navigator.navigateToHome();
            navigator.finish();
        } else {
            navigator.navigateToLogin(REQUEST_CODE_LOGIN);
        }
    }


    private boolean isAccessTokenAvailable() {
        return (!Utils.isEmpty(prefs.getAccessToken()));
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CODE_LOGIN:
                if (resultCode == Activity.RESULT_OK) {
                    decideNavigation();
                } else {
                    navigator.finish();
                }
                break;
        }
    }
}
