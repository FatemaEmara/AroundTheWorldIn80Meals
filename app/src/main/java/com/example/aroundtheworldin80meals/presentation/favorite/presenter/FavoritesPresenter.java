package com.example.aroundtheworldin80meals.presentation.favorite.presenter;

import com.example.aroundtheworldin80meals.data.meal.model.Meal;

public interface FavoritesPresenter {
    void getFavoriteMeals();
    void deleteFromFavorite(Meal meal);
}
