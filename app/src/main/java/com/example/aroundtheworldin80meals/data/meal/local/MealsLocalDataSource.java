package com.example.aroundtheworldin80meals.data.meal.local;

import android.content.Context;

import com.example.aroundtheworldin80meals.data.db.MealsDatabase;
import com.example.aroundtheworldin80meals.data.db.MealsDAO;
import com.example.aroundtheworldin80meals.data.meal.model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;

public class MealsLocalDataSource {

    MealsDAO mealsDAO;

    public MealsLocalDataSource(Context context) {
        this.mealsDAO = MealsDatabase.getInstance(context).getMealsDAO();

    }

    public Observable<List<Meal>> getFavoriteMeals() {
        return mealsDAO.getFavMeals();
    }

    public Completable insertFavoriteMeal(Meal meal) {
        return mealsDAO.insertFavMeal(meal);

    }

    public Completable deleteFavoriteMeal(Meal meal) {
        return mealsDAO.deleteFavMealById(meal.getIdMeal());

    }
}
