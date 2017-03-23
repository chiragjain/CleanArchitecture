package com.cjlib.cleanarchitecture.ui;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

public class CustomDialog extends Dialog {

    public CustomDialog(Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.gravity = Gravity.NO_GRAVITY;
        getWindow().setAttributes(layoutParams);
        setCanceledOnTouchOutside(false);
        setWindowFeatures();
    }

    private void setWindowFeatures() {
        getWindow().getAttributes().flags |= WindowManager.LayoutParams.FLAG_DIM_BEHIND;
    }

    public CustomDialog setBackgroundDimEnabled() {
        getWindow().getAttributes().flags |= WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        return this;
    }

    public CustomDialog setInteractOutSideEnabled() {
        getWindow().getAttributes().flags |= WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
        return this;
    }

    public CustomDialog setAnimations(int styleId) {
        getWindow().setWindowAnimations(styleId);
        return this;
    }

    public CustomDialog setCloseOnTouchOutsideEnabled() {
        setCanceledOnTouchOutside(true);
        return this;
    }
}
