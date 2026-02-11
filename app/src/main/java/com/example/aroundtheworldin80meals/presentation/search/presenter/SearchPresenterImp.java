package com.example.aroundtheworldin80meals.presentation.search.presenter;

import android.app.Application;

import com.example.aroundtheworldin80meals.data.meal.MealRepository;
import com.example.aroundtheworldin80meals.data.meal.model.Meal;
import com.example.aroundtheworldin80meals.presentation.search.view.SearchView;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchPresenterImp implements SearchPresenter {
    private final MealRepository repository;
    private final SearchView view;
    private final CompositeDisposable disposable = new CompositeDisposable();


    public SearchPresenterImp(Application application, SearchView view) {
        this.repository = new MealRepository(application);
        this.view = view;
    }

    @Override
    public void getCategories() {

        disposable.add(
                repository.getCategories()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                view::showCategories,
                                error -> view.showError("Failed to load categories")
                        )
        );

    }

    @Override
    public void getAreas() {
        disposable.add(
                repository.getAreas()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                view::showAreas,
                                error -> view.showError("Failed to load areas"))
        );
    }

    @Override
    public void getIngredients() {

        disposable.add(
                repository.getIngredients()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                view::showIngredients,
                                error -> view.showError("Failed to load ingredients"))
        );
    }

    @Override
    public void filterByCategory(String category) {
        disposable.add(
                repository.filterByCategory(category)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                view::showMealsByCategory,
                                error -> view.showError("Failed to filter by category")
                        )
        );
    }

    @Override
    public void filterByIngredients(String ingredient) {
        disposable.add(
                repository.filterByMainIngredient(ingredient)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                view::showMealsByIngredients,
                                error -> view.showError("Failed to filter by ingredient")
                        )
        );
    }

    @Override
    public void filterByCountry(String country) {
        disposable.add(
                repository.filterByArea(country)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                view::showMealsByCountry,
                                error -> view.showError("Failed to filter by country")
                        )
        );
    }


    @Override
    public void getMealDetailsById(String id) {
        disposable.add(
                repository.getMealDetails(id)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                view::showMealById,
                                error -> view.showError("Failed to load meal details")
                        )
        );
    }

    @Override
    public void searchMealByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return;
        }

        disposable.add(
                repository.searchMealByName(name)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                view::searchMealByName,
                                error -> view.showError("No meals found")
                        )
        );
    }

    @Override
    public void addMealToFavorite(Meal meal) {
        repository.addMealToFavorite(meal)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> {
                            view.hideLoading();
                        },
                        Throwable -> {
                            view.hideLoading();
                            view.showError("error");
                        }
                );
    }

    @Override
    public void addPlannedMeal(Meal meal) {
        repository.addPlannedMeal(meal)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> {
                            view.hideLoading();
                        },
                        Throwable -> {
                            view.hideLoading();
                            view.showError("error");
                        }
                );
    }


    @Override
    public void onDestroy() {
        disposable.dispose();
    }

}
