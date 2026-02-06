package com.example.aroundtheworldin80meals.data.meal.remote;

import com.example.aroundtheworldin80meals.data.meal.model.MealResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MealService {

    @GET("random.php")
    Single<MealResponse> getRandomMeal();

    @GET("search.php")
    Single<MealResponse> searchMealByName(@Query("s") String name);


    @GET("lookup.php")
    Single<MealResponse> getMealDetails(@Query("i") String id);
}
