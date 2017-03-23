package com.cjlib.cleanarchitecture;

import com.cjlib.cleanarchitecture.components.ResourceProvider;
import com.cjlib.cleanarchitecture.components.data.Prefs;
import com.cjlib.cleanarchitecture.components.Navigator;
import com.cjlib.cleanarchitecture.login.presenters.LoginPresenter;
import com.cjlib.cleanarchitecture.login.viewmodels.LoginViewModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Observable;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LoginPresenterTest {

    @Mock
    Prefs prefs;

    @Mock
    Navigator navigator;

    @Mock
    ResourceProvider provider;

    @InjectMocks
    LoginPresenter presenter;

    @Before
    public void setUp() {
        presenter.onCreate();
        when(provider.getString(R.string.error_msg_invalid_email)).thenReturn("Invalid Email Id");
        when(provider.getString(R.string.error_msg_invalid_password)).thenReturn("Invalid Password");
    }

    @Test
    public void testFormValidation_EmailWrongPasswordCorrect() {

        presenter.onPasswordTextChange("test");
        presenter.onEmailIdTextChange("abc");

        Observable<LoginViewModel> observable = presenter.getLoginViewModel();
        observable.subscribe(viewModel -> {
            assertEquals("Invalid Email Id", viewModel.getEmailIdError());
            assertEquals(null, viewModel.getPasswordError());
        });
    }

    @Test
    public void testFormValidation_PasswordWrongEmailCorrect() {

        presenter.onEmailIdTextChange("abc@abc.com");
        presenter.onPasswordTextChange("");

        Observable<LoginViewModel> observable = presenter.getLoginViewModel();
        observable.subscribe(viewModel -> {
            assertEquals(null, viewModel.getEmailIdError());
            assertEquals("Invalid Password", viewModel.getPasswordError());
        });
    }


    @Test
    public void testFormValidation_PasswordCorrectEmailCorrect() {

        presenter.onEmailIdTextChange("abc@abc.com");
        presenter.onPasswordTextChange("test");

        Observable<LoginViewModel> observable = presenter.getLoginViewModel();
        observable.subscribe(viewModel -> {
            assertEquals(null, viewModel.getEmailIdError());
            assertEquals(null, viewModel.getPasswordError());
        });
    }

    @Test
    public void testFormValidation_PasswordCorrectEmailWrong() {

        presenter.onEmailIdTextChange("abc");
        presenter.onPasswordTextChange("test");

        Observable<LoginViewModel> observable = presenter.getLoginViewModel();
        observable.subscribe(viewModel -> {
            assertEquals("Invalid Email Id", viewModel.getEmailIdError());
            assertEquals(null, viewModel.getPasswordError());
        });

    }

    @Test
    public void testFormValidation_EmailCorrectPasswordWrong() {

        presenter.onPasswordTextChange("");
        presenter.onEmailIdTextChange("abc@abc.com");

        Observable<LoginViewModel> observable = presenter.getLoginViewModel();
        observable.subscribe(viewModel -> {
            assertEquals(null, viewModel.getEmailIdError());
            assertEquals("Invalid Password", viewModel.getPasswordError());
        });
    }

}
