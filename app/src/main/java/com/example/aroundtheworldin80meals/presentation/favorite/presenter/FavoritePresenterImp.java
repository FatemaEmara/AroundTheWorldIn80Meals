package com.example.aroundtheworldin80meals.presentation.favorite.presenter;

import android.app.Application;

import com.example.aroundtheworldin80meals.data.meal.MealRepository;
import com.example.aroundtheworldin80meals.data.meal.model.Meal;
import com.example.aroundtheworldin80meals.presentation.favorite.view.FavoriteView;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FavoritePresenterImp implements FavoritesPresenter {

    MealRepository mealRepository;
    FavoriteView favoriteView;
    private final CompositeDisposable disposable = new CompositeDisposable();


    public FavoritePresenterImp(Application application, FavoriteView favoriteView) {
        this.mealRepository = new MealRepository(application);
        this.favoriteView = favoriteView;
    }

    @Override
    public void getFavoriteMeals() {

        mealRepository.getFavoriteMeals().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response ->
                        favoriteView.showFavoriteMeals(response));

    }

    @Override
    public void deleteFromFavorite(Meal meal) {

        disposable.add(
                mealRepository.deleteMealFromFavorite(meal)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                () -> {

                                    favoriteView.onMealDeleted();
                                    getFavoriteMeals();
                                },
                                throwable -> {
                                }
                        )
        );
    }
}
