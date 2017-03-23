package com.cjlib.cleanarchitecture.components;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import com.cjlib.cleanarchitecture.base.BaseActivity;
import com.cjlib.cleanarchitecture.home.HomeActivity;
import com.cjlib.cleanarchitecture.login.LoginActivity;


/**
 * A navigator will contain all action by performing them user can navigate or trigger one time
 * events like showing or dismiss dialogs
 */
public class Navigator {

    BaseActivity activity;

    public Navigator(BaseActivity activity) {
        this.activity = activity;
    }

    public void showToast(String message) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
    }

    public void showToast(int resId) {
        Toast.makeText(activity, resId, Toast.LENGTH_SHORT).show();
    }

    public void navigateToLogin(int requestCode) {
        Intent intent = new Intent(activity, LoginActivity.class);
        activity.startActivityForResult(intent, requestCode);
    }

    public void navigateToHome() {
        Intent intent = new Intent(activity, HomeActivity.class);
        activity.startActivity(intent);
    }

    public void finish() {
        activity.finish();
    }

    public void finishWithResultOK() {
        activity.setResult(Activity.RESULT_OK);
        activity.finish();
    }

    public void showProgressDialog(String message) {
        activity.showProgressDialog(message);
    }

    public void showProgressDialog(int resId) {
        activity.showProgressDialog(resId);
    }


    public void dismissProgressDialog() {
        activity.dismissProgressDialog();
    }


}
