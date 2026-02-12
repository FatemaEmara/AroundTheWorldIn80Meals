package com.example.aroundtheworldin80meals.data.auth;


import android.content.Context;

import com.example.aroundtheworldin80meals.data.auth.local.AuthLocalDataSource;
import com.example.aroundtheworldin80meals.data.auth.remote.AuthRemoteDataSource;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

import io.reactivex.rxjava3.core.Completable;

public class AuthRepository {

    private final AuthRemoteDataSource remote;
    private final AuthLocalDataSource local;

    public AuthRepository(Context context) {
        remote = new AuthRemoteDataSource();
        local = new AuthLocalDataSource(context.getApplicationContext());
    }

    public Completable login(String email, String password) {
        return remote.login(email, password)
                .andThen(local.setLoggedIn(true));
    }

    public Completable register(String email, String password) {
        return remote.register(email, password)
                .andThen(local.setLoggedIn(true));
    }

    public Completable logout() {
        return remote.logout()
                .andThen(local.clear());
    }

    public boolean isLoggedIn() {
        return local.isLoggedIn() || remote.getCurrentUser() != null;
    }

    public boolean isOnboardingSeen() {
        return local.isOnboardingSeen();
    }

    public Completable signInWithGoogle(String idToken) {
        AuthCredential credential =
                GoogleAuthProvider.getCredential(idToken, null);

        return Completable.create(emitter ->
                FirebaseAuth.getInstance()
                        .signInWithCredential(credential)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                emitter.onComplete();
                            } else {
                                emitter.onError(task.getException());
                            }
                        })
        ).andThen(local.setLoggedIn(true));
    }


    public Completable signInAnonymously() {
        return remote.signInAnonymously()
                .andThen(local.setLoggedIn(true))
                .andThen(local.setGuestMode(true));
    }


    public boolean isGuestMode() {
        return local.isGuestMode();
    }


    public void setOnboardingSeenSync() {
        local.setOnboardingSeenSync();
    }
}
