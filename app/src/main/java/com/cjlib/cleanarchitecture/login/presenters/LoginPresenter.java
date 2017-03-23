package com.cjlib.cleanarchitecture.login.presenters;

import com.cjlib.cleanarchitecture.base.Presenter;
import com.cjlib.cleanarchitecture.components.ResourceProvider;
import com.cjlib.cleanarchitecture.components.data.Prefs;
import com.cjlib.cleanarchitecture.components.Navigator;
import com.cjlib.cleanarchitecture.login.viewmodels.LoginViewModel;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

public class LoginPresenter implements Presenter {

    private Navigator navigator;

    private ResourceProvider provider;

    private Prefs prefs;

    private LoginViewModel loginViewModel;


    private BehaviorSubject<LoginViewModel> loginViewModelSubject = BehaviorSubject.create();

    public LoginPresenter(Navigator navigator, ResourceProvider provider, Prefs prefs) {
        this.navigator = navigator;
        this.provider = provider;
        this.prefs = prefs;
    }

    @Override
    public void onCreate() {
        loginViewModel = LoginViewModel.createLoginViewModel(provider);
        loginViewModelSubject.onNext(loginViewModel);
    }

    public void onLoginButtonClick() {
        // navigator.showProgressDialog(R.string.msg_loading);
        // TODO call actual login api
        prefs.saveAccessToken("Test Token");
        navigator.finishWithResultOK();
        loginViewModelSubject.onNext(loginViewModel);
    }

    public void onEmailIdTextChange(String emailInput) {
        loginViewModel.setEmailId(emailInput);
        loginViewModelSubject.onNext(loginViewModel);
    }


    public void onPasswordTextChange(String passwordInput) {
        loginViewModel.setPassword(passwordInput);
        loginViewModelSubject.onNext(loginViewModel);
    }

    public Observable<LoginViewModel> getLoginViewModel() {
        return loginViewModelSubject;
    }


}
