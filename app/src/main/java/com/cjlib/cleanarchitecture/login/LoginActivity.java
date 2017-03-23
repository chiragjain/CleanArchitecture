package com.cjlib.cleanarchitecture.login;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cjlib.cleanarchitecture.AppApplication;
import com.cjlib.cleanarchitecture.R;
import com.cjlib.cleanarchitecture.base.BaseActivity;
import com.cjlib.cleanarchitecture.login.di.DaggerLoginActivityComponent;
import com.cjlib.cleanarchitecture.login.di.LoginActivityComponent;
import com.cjlib.cleanarchitecture.login.di.LoginActivityModule;
import com.cjlib.cleanarchitecture.login.presenters.LoginPresenter;
import com.cjlib.cleanarchitecture.login.viewmodels.LoginViewModel;

import javax.inject.Inject;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private EditText emailIdView;
    private EditText passwordView;
    private Button loginButton;

    @Inject
    LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();

        LoginActivityComponent component = DaggerLoginActivityComponent.builder()
                .loginActivityModule(new LoginActivityModule(this))
                .applicationComponent(AppApplication.get().getComponent()).build();

        component.inject(this);

        loginPresenter.onCreate();

        subscribeViewModel();
    }

    private void initViews() {
        emailIdView = (EditText) findViewById(R.id.et_email_id);
        passwordView = (EditText) findViewById(R.id.et_password);
        passwordView.setTypeface(Typeface.DEFAULT);
        passwordView.setTransformationMethod(new PasswordTransformationMethod());
        passwordView.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);

        loginButton = (Button) findViewById(R.id.btn_sign_in);
        loginButton.setOnClickListener(this);

        emailIdView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                loginPresenter.onEmailIdTextChange(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });


        passwordView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                loginPresenter.onPasswordTextChange(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }


    private void subscribeViewModel() {
        loginPresenter.getLoginViewModel().subscribe(model -> bindViewModel(model));
    }


    private void bindViewModel(LoginViewModel viewModel) {

        emailIdView.setError(viewModel.getEmailIdError());
        passwordView.setError(viewModel.getPasswordError());

        loginButton.setEnabled(viewModel.isLoginButtonEnabled());

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_sign_in:
                loginPresenter.onLoginButtonClick();
        }
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        super.onBackPressed();
    }
}
