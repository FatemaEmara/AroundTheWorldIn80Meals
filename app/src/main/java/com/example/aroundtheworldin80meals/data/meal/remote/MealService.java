package com.example.aroundtheworldin80meals.data.meal.remote;

import com.example.aroundtheworldin80meals.data.meal.model.AreaResponse;
import com.example.aroundtheworldin80meals.data.meal.model.CategoryResponse;
import com.example.aroundtheworldin80meals.data.meal.model.IngredientResponse;
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

    @GET("filter.php")
    Single<MealResponse> filterByCategory(@Query("c") String category);

    @GET("filter.php")
    Single<MealResponse> filterByArea(@Query("a") String area);

    @GET("categories.php")
    Single<CategoryResponse> getCategories();

    @GET("list.php")
    Single<AreaResponse> getAreas(@Query("a") String list);

    @GET("list.php?i=list")
    Single<IngredientResponse> getIngredients();

    @GET("filter.php")
    Single<MealResponse> filterByIngredient(@Query("i") String ingredient);

}
