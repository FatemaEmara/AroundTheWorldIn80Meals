package com.example.aroundtheworldin80meals.presentation.calendar.presenter;

import com.example.aroundtheworldin80meals.data.meal.model.Meal;

public interface CalendarPresenter {
    void getPlannedMeals();

    void deleteFromPlannedMeals(Meal meal);

    void onDestroy();

}
