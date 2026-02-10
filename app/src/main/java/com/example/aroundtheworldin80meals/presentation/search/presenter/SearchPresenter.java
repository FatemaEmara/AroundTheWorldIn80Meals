package com.example.aroundtheworldin80meals.presentation.search.presenter;

import com.example.aroundtheworldin80meals.data.meal.model.Meal;

public interface SearchPresenter {
    void getCategories();

    void getAreas();

    void getIngredients();

    void filterByCategory(String category);

    void filterByIngredients(String ingredient);

    void filterByCountry(String country);

    void getMealDetailsById(String id);

    void searchMealByName(String name);
    void addMealToFavorite(Meal meal);

    void onDestroy();
}
