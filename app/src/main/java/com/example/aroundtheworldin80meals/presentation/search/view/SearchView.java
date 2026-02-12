package com.example.aroundtheworldin80meals.presentation.search.view;

import com.example.aroundtheworldin80meals.data.meal.model.Area;
import com.example.aroundtheworldin80meals.data.meal.model.Category;
import com.example.aroundtheworldin80meals.data.meal.model.Ingredient;
import com.example.aroundtheworldin80meals.data.meal.model.Meal;

import java.util.List;

public interface SearchView {

    void showLoading();

    void hideLoading();


    void showCategories(List<Category> categories);

    void showAreas(List<Area> areas);

    void showIngredients(List<Ingredient> ingredients);

    void showMealById(Meal meal);

    void showMealsByCountry(List<Meal> meals);

    void showMealsByCategory(List<Meal> meals);

    void showMealsByIngredients(List<Meal> meals);


    void showError(String message);

    void searchMealByName(List<Meal> meals);

    void showGuestModeRestriction();
    void showMealAddedToFavorites();
    void showMealPlannedSuccess();

    void onDestroy();
}
