package com.cjlib.cleanarchitecture.components.data;

import android.content.Context;

/**
 * A app specific shared preference manager
 */
public class Prefs {

    private static final String PREFERENCE_FILE_NAME = "cjlib.data";
    private static final String KEY_EMAIL_ID = "email.id";
    private static final String KEY_ACCESS_TOKEN = "access.token";

    private KeyValueManager manager;

    public Prefs(Context context) {
        manager = new SharedPreferenceManager(context, PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
    }

    private KeyValueManager getManager() {
        if (manager == null) {
            throw new RuntimeException("Preferences Manager is not initialize.");
        }

        return manager;
    }

    public void saveUserEmailId(String emailId) {
        getManager().save(KEY_EMAIL_ID, emailId);
    }

    public String getUserEmailId() {
        return getManager().getString(KEY_EMAIL_ID);
    }

    public void saveAccessToken(String accessToken) {
        getManager().save(KEY_ACCESS_TOKEN, accessToken);
    }

    public String getAccessToken() {
        return getManager().getString(KEY_ACCESS_TOKEN);
    }

}
