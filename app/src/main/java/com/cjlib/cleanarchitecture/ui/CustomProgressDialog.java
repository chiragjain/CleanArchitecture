package com.cjlib.cleanarchitecture.ui;

import android.content.Context;
import android.text.TextUtils;
import android.widget.TextView;

import com.cjlib.cleanarchitecture.R;


public class CustomProgressDialog extends CustomDialog {

    private TextView tvMessage;

    public CustomProgressDialog(Context context) {
        super(context);
        init(0);
    }

    public CustomProgressDialog(Context context, int layoutId) {
        super(context);
        init(layoutId);
    }

    private void init(int layoutId) {
        initLayout(layoutId);
        setCanceledOnTouchOutside(false);
    }

    private void initLayout(int layoutId) {
        setContentView(layoutId == 0 ? R.layout.layout_progress : layoutId);
        tvMessage = (TextView) findViewById(R.id.tv_progress_msg);
    }

    public void setCustomContentView(int layoutId) {
        initLayout(layoutId);
    }

    public void setMessage(int msgId) {
        setMessage(getContext().getString(msgId));
    }

    public void setMessage(String msg) {
        if (tvMessage != null && !TextUtils.isEmpty(msg))
            tvMessage.setText(msg);
    }
}