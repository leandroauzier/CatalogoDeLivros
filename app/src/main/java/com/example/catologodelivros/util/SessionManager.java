package com.example.catologodelivros.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {

    private static final String PREFS_NAME = "catalogo_session";
    private static final String KEY_LOGGED_IN = "logged_in";
    private static final String KEY_USERNAME = "username";

    private final SharedPreferences preferences;

    public SessionManager(Context context) {
        preferences = context.getApplicationContext()
                .getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public boolean authenticate(String username, String password) {
        boolean valid = AppConstants.DEMO_USERNAME.equals(username)
                && AppConstants.DEMO_PASSWORD.equals(password);
        if (valid) {
            preferences.edit()
                    .putBoolean(KEY_LOGGED_IN, true)
                    .putString(KEY_USERNAME, username)
                    .apply();
        }
        return valid;
    }

    public boolean isLoggedIn() {
        return preferences.getBoolean(KEY_LOGGED_IN, false);
    }

    public String getUsername() {
        return preferences.getString(KEY_USERNAME, AppConstants.DEMO_USERNAME);
    }

    public void logout() {
        preferences.edit().clear().apply();
    }
}
