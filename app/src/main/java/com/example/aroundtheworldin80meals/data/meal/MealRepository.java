package com.example.aroundtheworldin80meals.data.meal;

import android.app.Application;

import com.example.aroundtheworldin80meals.data.meal.model.Area;
import com.example.aroundtheworldin80meals.data.meal.model.AreaResponse;
import com.example.aroundtheworldin80meals.data.meal.model.Category;
import com.example.aroundtheworldin80meals.data.meal.model.CategoryResponse;
import com.example.aroundtheworldin80meals.data.meal.model.Ingredient;
import com.example.aroundtheworldin80meals.data.meal.model.Meal;
import com.example.aroundtheworldin80meals.data.meal.model.MealResponse;
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


    public Single<List<Meal>> filterByCategory(String category) {
        return mealRemoteDataSource.filterByCategory(category)
                .map(MealResponse::getMeals);
    }

    public Single<List<Meal>> filterByArea(String area) {
        return mealRemoteDataSource.filterByArea(area)
                .map(MealResponse::getMeals);
    }

    public Single<List<Category>> getCategories() {
        return mealRemoteDataSource.getCategories()
                .map(CategoryResponse::getCategories);
    }

    public Single<List<Area>> getAreas() {
        return mealRemoteDataSource.getAreas()
                .map(AreaResponse::getAreas);
    }


    public Single<List<Ingredient>> getIngredients() {
        return mealRemoteDataSource.getIngredients()
                .map(response ->
                        response.getMeals() == null
                                ? new ArrayList<>()
                                : response.getMeals()
                );
    }


    public Single<List<Meal>> filterByMainIngredient(String ingredient) {
        return mealRemoteDataSource.filterByIngredient(ingredient)
                .map(MealResponse::getMeals);
    }

}
