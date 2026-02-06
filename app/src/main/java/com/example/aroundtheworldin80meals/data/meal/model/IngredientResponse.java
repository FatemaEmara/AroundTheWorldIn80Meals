package com.example.aroundtheworldin80meals.data.meal.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class IngredientResponse {


    @SerializedName("meals")
    private List<Ingredient> meals;

    public IngredientResponse(List<Ingredient> meals) {
        this.meals = meals;
    }

    public List<Ingredient> getMeals() {
        return meals;
    }
    public void setMeals(List<Ingredient> meals) {
        this.meals = meals;
    }
}


