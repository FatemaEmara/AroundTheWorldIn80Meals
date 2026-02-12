package com.example.aroundtheworldin80meals.data.meal.remote;

import com.example.aroundtheworldin80meals.data.meal.model.AreaResponse;
import com.example.aroundtheworldin80meals.data.meal.model.CategoryResponse;
import com.example.aroundtheworldin80meals.data.meal.model.IngredientResponse;
import com.example.aroundtheworldin80meals.data.meal.model.MealResponse;
import com.example.aroundtheworldin80meals.data.network.Network;

import io.reactivex.rxjava3.core.Single;


public class MealRemoteDataSource {
    private MealService mealService;

    public MealRemoteDataSource() {
        mealService = Network.getInstance().create(MealService.class);
    }

    public Single<MealResponse> getRandomMeal() {
        return mealService.getRandomMeal();
    }

    public Single<MealResponse> searchMealByName(String name) {
        return mealService.searchMealByName(name);
    }

    public Single<MealResponse> getMealDetails(String id) {
        return mealService.getMealDetails(id);
    }

    public Single<MealResponse> filterByCategory(String category) {
        return mealService.filterByCategory(category);
    }

    public Single<MealResponse> filterByArea(String area) {
        return mealService.filterByArea(area);
    }

    public Single<CategoryResponse> getCategories() {
        return mealService.getCategories();
    }

    public Single<AreaResponse> getAreas() {
        return mealService.getAreas("list");
    }

    public Single<IngredientResponse> getIngredients() {
        return mealService.getIngredients();
    }

    public Single<MealResponse> filterByIngredient(String ingredient) {
        return mealService.filterByIngredient(ingredient);
    }
}


