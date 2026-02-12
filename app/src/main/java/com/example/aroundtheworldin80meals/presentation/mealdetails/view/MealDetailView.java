package com.example.aroundtheworldin80meals.presentation.mealdetails.view;

import com.example.aroundtheworldin80meals.data.meal.model.Meal;

public interface MealDetailView {
    void showLoading();
    void hideLoading();
    void showMealDetails(Meal meal);
    void showError(String message);
}