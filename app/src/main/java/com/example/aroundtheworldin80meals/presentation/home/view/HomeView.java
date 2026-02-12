package com.example.aroundtheworldin80meals.presentation.home.view;

import com.example.aroundtheworldin80meals.data.meal.model.Area;
import com.example.aroundtheworldin80meals.data.meal.model.Category;
import com.example.aroundtheworldin80meals.data.meal.model.Meal;

import java.util.List;

public interface HomeView {
    void showLoading();

    void hideLoading();

    void showRandomMeal(Meal meal);

    void showCategories(List<Category> categories);

    void showAreas(List<Area> areas);

    void showError(String message);
}
