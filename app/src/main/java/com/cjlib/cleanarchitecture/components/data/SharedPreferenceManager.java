package com.cjlib.cleanarchitecture.components.data;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;
import java.util.Set;

/**
 * Shared Preference Manager will able to handle
 * key value in persistence storage
 */
public class SharedPreferenceManager implements KeyValueManager {

    private SharedPreferences sharedPreferences;

    public SharedPreferenceManager(Context context, String fileName, int mode) {
        this.sharedPreferences = context.getSharedPreferences(fileName, mode);
    }

    @Override
    public void save(String key, String data) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, data);
        editor.apply();
    }

    @Override
    public void save(String key, boolean data) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, data);
        editor.apply();
    }

    @Override
    public void save(String key, float data) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(key, data);
        editor.apply();
    }

    @Override
    public void save(String key, int data) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, data);
        editor.apply();
    }

    @Override
    public void save(String key, long data) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(key, data);
        editor.apply();
    }

    @Override
    public void save(String key, Set<String> data) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet(key, data);
        editor.apply();
    }

    @Override
    public String getString(String key, String defaultValue) {
        return sharedPreferences.getString(key, defaultValue);
    }

    @Override
    public String getString(String key) {
        return sharedPreferences.getString(key, "");
    }

    @Override
    public Integer getInteger(String key, int defaultValue) {
        return sharedPreferences.getInt(key, defaultValue);
    }

    @Override
    public Integer getInteger(String key) {
        return sharedPreferences.getInt(key, 0);
    }

    @Override
    public Float getFloat(String key, float defaultValue) {
        return sharedPreferences.getFloat(key, defaultValue);
    }

    @Override
    public Float getFloat(String key) {
        return sharedPreferences.getFloat(key, 0.0f);
    }

    @Override
    public Long getLong(String key, long defaultValue) {
        return sharedPreferences.getLong(key, defaultValue);
    }

    @Override
    public Long getLong(String key) {
        return sharedPreferences.getLong(key, 0L);
    }

    @Override
    public Boolean getBoolean(String key, boolean defaultValue) {
        return sharedPreferences.getBoolean(key, defaultValue);
    }

    @Override
    public Boolean getBoolean(String key) {
        return sharedPreferences.getBoolean(key, false);
    }

    @Override
    public Map<String, ?> getMap() {
        return sharedPreferences.getAll();
    }

    @Override
    public void clear() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
