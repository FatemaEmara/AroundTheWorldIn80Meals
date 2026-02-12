package com.example.aroundtheworldin80meals.presentation.auth.presenter;
import android.content.Context;
import com.example.aroundtheworldin80meals.data.auth.AuthRepository;
import com.example.aroundtheworldin80meals.presentation.auth.view.AuthView;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class AuthPresenterImpl implements AuthPresenter {

    private final AuthRepository repo;
    private final AuthView view;
    private final CompositeDisposable disposables = new CompositeDisposable();

    public AuthPresenterImpl(AuthView view, Context context) {
        this.view = view;
        this.repo = new AuthRepository(context);
    }

    @Override
    public void login(String email, String password) {

        view.showLoading();

        disposables.add(
                repo.login(email, password)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                () -> {
                                    view.hideLoading();
                                    view.navigateToHome();
                                },
                                throwable -> {
                                    view.hideLoading();
                                    view.showError(throwable.getMessage());
                                }
                        )
        );
    }

    @Override
    public void register(String email, String password) {

        view.showLoading();

        disposables.add(
                repo.register(email, password)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                () -> {
                                    view.hideLoading();
                                    view.navigateToHome();
                                },
                                throwable -> {
                                    view.hideLoading();
                                    view.showError(throwable.getMessage());
                                }
                        )
        );
    }

    @Override
    public void loginWithGoogle(String idToken) {

        view.showLoading();

        disposables.add(
                repo.signInWithGoogle(idToken)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                () -> {
                                    view.hideLoading();
                                    view.navigateToHome();
                                },
                                throwable -> {
                                    view.hideLoading();
                                    view.showError(throwable.getMessage());
                                }
                        )
        );
    }

    @Override
    public void loginAsGuest() {

        view.showLoading();

        disposables.add(
                repo.signInAnonymously()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                () -> {
                                    view.hideLoading();
                                    view.navigateToHome();
                                },
                                throwable -> {
                                    view.hideLoading();
                                    view.showError("Guest login failed: " + throwable.getMessage());
                                }
                        )
        );
    }


    @Override
    public void clear() {
        disposables.clear();
    }
}



