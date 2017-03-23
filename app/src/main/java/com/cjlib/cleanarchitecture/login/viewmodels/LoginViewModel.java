package com.cjlib.cleanarchitecture.login.viewmodels;


import com.cjlib.cleanarchitecture.R;
import com.cjlib.cleanarchitecture.Utils;
import com.cjlib.cleanarchitecture.base.ViewModel;
import com.cjlib.cleanarchitecture.components.ResourceProvider;

public class LoginViewModel implements ViewModel {

    private String emailIdError;

    private String passwordError;

    private String emailId;

    private String password;

    private boolean isLoginButtonEnabled;

    private ResourceProvider provider;

    public static LoginViewModel createLoginViewModel(ResourceProvider provider) {
        return new LoginViewModel(provider);
    }

    public LoginViewModel(ResourceProvider provider) {
        this.provider = provider;
    }

    private void validateLoginForm() {

        emailIdError = null;
        passwordError = null;
        isLoginButtonEnabled = true;


        boolean validEmail = false;
        if (emailId != null) {
            validEmail = Utils.isEmailValid(emailId);
        }

        if (!validEmail) {
            emailIdError = provider.getString(R.string.error_msg_invalid_email);
            isLoginButtonEnabled = false;
        }
        if (password == null || password.isEmpty()) {
            passwordError = provider.getString(R.string.error_msg_invalid_password);
            isLoginButtonEnabled = false;
        }
    }

    public String getEmailIdError() {
        return emailIdError;
    }

    public void setEmailIdError(String emailIdError) {
        this.emailIdError = emailIdError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
        validateLoginForm();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        validateLoginForm();
    }

    public boolean isLoginButtonEnabled() {
        return isLoginButtonEnabled;
    }

    public void setLoginButtonEnabled(boolean loginButtonEnabled) {
        isLoginButtonEnabled = loginButtonEnabled;
    }

}
