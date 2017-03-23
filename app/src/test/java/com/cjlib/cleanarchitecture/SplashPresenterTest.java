package com.cjlib.cleanarchitecture;

import com.cjlib.cleanarchitecture.components.data.Prefs;
import com.cjlib.cleanarchitecture.components.Navigator;
import com.cjlib.cleanarchitecture.login.presenters.SplashPresenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SplashPresenterTest {

    @Mock
    Navigator navigator;

    @Mock
    Prefs prefs;

    @InjectMocks
    SplashPresenter presenter;

    @Before
    public void setUp() {
        doNothing().when(navigator).navigateToLogin(1000);
        doNothing().when(navigator).navigateToHome();
    }

    @Test
    public void testDecideNavigation_NoToken() {

        when(prefs.getAccessToken()).thenReturn("");

        presenter.onCreate();

        verify(navigator, times(1)).navigateToLogin(1000);

    }

    @Test
    public void testDecideNavigation_WithToken() {

        when(prefs.getAccessToken()).thenReturn("auth_token");

        presenter.onCreate();

        verify(navigator, times(1)).navigateToHome();

    }

}
