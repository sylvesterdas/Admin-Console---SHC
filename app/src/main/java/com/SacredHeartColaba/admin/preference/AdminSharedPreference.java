package com.SacredHeartColaba.admin.preference;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author Sylvester Das
 * @version 1.0.0
 * @since 11-Jun-17.
 */

public class AdminSharedPreference {

    private static final String TAG = AdminSharedPreference.class.getPackage().getName();
    private SharedPreferences preferences;

    public AdminSharedPreference(Activity mActivity) {
        preferences = mActivity.getSharedPreferences(TAG, Context.MODE_PRIVATE);
        if (!preferences.contains(Helper.IS_LOGGED_IN)) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean(Helper.IS_LOGGED_IN, false);
            editor.apply();
        }
    }

    public boolean login(String username, String password) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Helper.USERNAME, username);
        editor.putString(Helper.PASSWORD, password);
        editor.putBoolean(Helper.IS_LOGGED_IN, true);
        return editor.commit();
    }

    public boolean isLoggedIn() {
        return preferences.getBoolean(Helper.IS_LOGGED_IN, false);
    }

    public boolean logout() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(Helper.USERNAME);
        editor.remove(Helper.PASSWORD);
        editor.putBoolean(Helper.IS_LOGGED_IN, false);
        return editor.commit();
    }

    private interface Helper {
        String USERNAME = "username";
        String PASSWORD = "password";

        String IS_LOGGED_IN = "isLoggedIn";
    }
}
