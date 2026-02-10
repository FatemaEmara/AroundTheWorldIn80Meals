package com.example.aroundtheworldin80meals.presentation.calendar.view;

import com.example.aroundtheworldin80meals.data.meal.model.Meal;

import java.util.List;

public interface CalendarView {
    void onPlannedMealDeleted();
    void showPlannedMeals(List<Meal> meals);
}
