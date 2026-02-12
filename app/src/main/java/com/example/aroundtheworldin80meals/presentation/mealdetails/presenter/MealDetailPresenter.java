package com.example.aroundtheworldin80meals.presentation.mealdetails.presenter;

import com.example.aroundtheworldin80meals.data.meal.model.Meal;

public interface MealDetailPresenter {
    void getMealDetails(String mealId);
//    void addMealToFavorite(Meal meal);
//    void addPlannedMeal(Meal meal);
    void onDestroy();
}
