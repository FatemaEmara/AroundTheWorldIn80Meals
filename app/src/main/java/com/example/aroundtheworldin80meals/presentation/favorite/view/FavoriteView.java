package com.example.aroundtheworldin80meals.presentation.favorite.view;

import com.example.aroundtheworldin80meals.data.meal.model.Meal;

import java.util.List;

public interface FavoriteView {
    void onMealDeleted();
    void showFavoriteMeals(List<Meal> meals);


}
