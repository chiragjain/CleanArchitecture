package com.cjlib.cleanarchitecture.components;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

/**
 * A wrapper for providing app resources to presenter
 */
public class ResourceProvider {

    private Context context;

    public ResourceProvider(Context context) {
        this.context = context;
    }

    public String getString(int resId) {
        return context.getString(resId);
    }

    public String getString(int resId, Object... objects) {
        return context.getString(resId, objects);
    }

    public Drawable getDrawable(int resId) {
        return ContextCompat.getDrawable(context, resId);
    }

    public int getColor(int resId) {
        return ContextCompat.getColor(context, resId);
    }
}
