package com.example.aroundtheworldin80meals.data.auth.local;


import android.content.Context;
import android.content.SharedPreferences;

import io.reactivex.rxjava3.core.Completable;

public class AuthLocalDataSource {

    private static final String PREF_NAME = "auth_prefs";
    private static final String KEY_LOGGED_IN = "is_logged_in";
    private static final String KEY_ONBOARDING = "onboarding_seen";
    private static final String KEY_IS_GUEST = "is_on_guest_mode";

    private final SharedPreferences prefs;

    public AuthLocalDataSource(Context context) {
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public Completable setGuestMode(boolean value) {
        return Completable.fromAction(() ->
                prefs.edit().putBoolean(KEY_IS_GUEST, value).apply()
        );
    }

    public boolean isGuestMode() {
        return prefs.getBoolean(KEY_IS_GUEST, false);
    }

    public Completable setLoggedIn(boolean value) {
        return Completable.fromAction(() ->
                prefs.edit().putBoolean(KEY_LOGGED_IN, value).apply()
        );
    }

    public boolean isLoggedIn() {
        return prefs.getBoolean(KEY_LOGGED_IN, false);
    }


    public void setOnboardingSeenSync() {
        prefs.edit().putBoolean(KEY_ONBOARDING, true).apply();
    }

    public boolean isOnboardingSeen() {
        return prefs.getBoolean(KEY_ONBOARDING, false);
    }

    public Completable clear() {
        return Completable.fromAction(() ->
                prefs.edit().clear().apply()
        );
    }
}
