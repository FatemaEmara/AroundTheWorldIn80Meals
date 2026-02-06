package com.example.aroundtheworldin80meals.data.meal;

import android.app.Application;

import com.example.aroundtheworldin80meals.data.meal.model.Meal;
import com.example.aroundtheworldin80meals.data.meal.remote.MealRemoteDataSource;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Single;

public class MealRepository {
    MealRemoteDataSource mealRemoteDataSource;

    public MealRepository(Application application) {
        this.mealRemoteDataSource = new MealRemoteDataSource();

    }

    public Single<Meal> getRandomMeal() {
        return mealRemoteDataSource.getRandomMeal().map(response -> {
            if (response.getMeals() == null || response.getMeals().isEmpty())
                throw new RuntimeException("No meal found");
            return response.getMeals().get(0);
        });
    }

    public Single<List<Meal>> searchMealByName(String name) {
        return mealRemoteDataSource.searchMealByName(name).map(response -> response.getMeals() == null ? new ArrayList<>() : response.getMeals());
    }

    public Single<Meal> getMealDetails(String id) {
        return mealRemoteDataSource.getMealDetails(id).map(response -> response.getMeals().get(0));
    }
}
