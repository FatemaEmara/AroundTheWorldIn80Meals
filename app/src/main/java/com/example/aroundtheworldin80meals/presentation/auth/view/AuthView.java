package com.example.aroundtheworldin80meals.presentation.auth.view;


public interface AuthView {

    void showLoading();

    void hideLoading();

    void showError(String message);

    void navigateToHome();
}
