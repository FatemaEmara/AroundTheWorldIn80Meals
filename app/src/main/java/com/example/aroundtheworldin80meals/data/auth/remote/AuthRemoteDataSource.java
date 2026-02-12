package com.example.aroundtheworldin80meals.data.auth.remote;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import io.reactivex.rxjava3.core.Completable;

public class AuthRemoteDataSource {

    private final FirebaseAuth firebaseAuth;

    public AuthRemoteDataSource() {
        this.firebaseAuth = FirebaseAuth.getInstance();
    }

    public Completable login(String email, String password) {
        return Completable.create(emitter ->
                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                emitter.onComplete();
                            } else {
                                emitter.onError(task.getException() != null
                                        ? task.getException()
                                        : new Exception("Login failed"));
                            }
                        })
        );
    }


    public Completable register(String email, String password) {
        return Completable.create(emitter ->
                firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                emitter.onComplete();
                            } else {
                                emitter.onError(task.getException() != null
                                        ? task.getException()
                                        : new Exception("Registration failed"));
                            }
                        })
        );
    }


    public Completable signInAnonymously() {
        return Completable.create(emitter ->
                firebaseAuth.signInAnonymously()
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                emitter.onComplete();
                            } else {
                                emitter.onError(task.getException() != null
                                        ? task.getException()
                                        : new Exception("Anonymous sign-in failed"));
                            }
                        })
        );
    }


    public Completable logout() {
        return Completable.fromAction(() -> firebaseAuth.signOut());
    }

    public FirebaseUser getCurrentUser() {
        return firebaseAuth.getCurrentUser();
    }



}



