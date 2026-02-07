package com.example.aroundtheworldin80meals.presentation.home.presenter;

import android.app.Application;

import com.example.aroundtheworldin80meals.data.meal.MealRepository;
import com.example.aroundtheworldin80meals.presentation.home.view.HomeView;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomePresenterImpl implements HomePresenter  {
    private final MealRepository repository;
    private final HomeView view;
    public HomePresenterImpl(Application application, HomeView view) {
        this.repository = new MealRepository(application);
        this.view = view;
    }

    @Override
    public void getRandomMeal() {
        view.showLoading();
        repository.getRandomMeal()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        meal -> {
                         //   view.hideLoading();
                            view.showRandomMeal(meal);
                        },
                        error -> {
                          //  view.hideLoading();
                            view.showError("Failed to load random meal");
                        }
                );
    }

    @Override
    public void getCategories() {
        repository.getCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        view::showCategories,
                        error -> view.showError("Failed to load categories")
                );
    }

    @Override
    public void getAreas() {
        repository.getAreas()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        view::showAreas,
                        error -> view.showError("Failed to load areas")
                );
    }
}
