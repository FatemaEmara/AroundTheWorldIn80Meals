package com.example.aroundtheworldin80meals.presentation.search.view;

import com.example.aroundtheworldin80meals.data.meal.model.Meal;

public interface OnMealClickListener {
    void addMealToFavorite(Meal meal);
    void addMealToPlan(Meal meal);
    void onMealClick(Meal meal);


}
