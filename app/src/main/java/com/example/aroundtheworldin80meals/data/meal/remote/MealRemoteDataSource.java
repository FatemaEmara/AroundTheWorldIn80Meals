package com.example.aroundtheworldin80meals.data.meal.remote;

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
}


