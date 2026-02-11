package com.example.aroundtheworldin80meals.data.meal.local;

import android.content.Context;

import com.example.aroundtheworldin80meals.data.db.MealDao;
import com.example.aroundtheworldin80meals.data.db.MealsDatabase;
import com.example.aroundtheworldin80meals.data.meal.model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;


public class MealsLocalDataSource {

    MealDao mealsDAO;

    public MealsLocalDataSource(Context context) {
        this.mealsDAO = MealsDatabase.getInstance(context).getMealsDAO();

    }

    public Flowable<List<Meal>> getFavoriteMeals() {
        return mealsDAO.getFavoriteMeals();
    }

    public Completable insertFavoriteMeal(Meal meal) {
        return mealsDAO.insertFavMeal(meal);

    }

    public Completable deleteFavoriteMeal(Meal meal) {
        return mealsDAO.deleteFavMealById(meal.getIdMeal());

    }

    public Flowable<List<Meal>> getPlannedMeals(String date) {
        return mealsDAO.getPlannedMealsByDate(date);
    }

//    public Completable insertPlannedMeal(Meal meal) {
//        return mealsDAO.planMeal(meal.getIdMeal(), meal.getDate());
//
//    }

    public Completable deletePlannedMeal(Meal meal) {
        return mealsDAO.deletePlannedMealById(meal.getIdMeal(), meal.getDate());

    }


    public Completable upsertMeal(Meal meal) {
        return mealsDAO.upsertMeal(meal);
    }

    public Completable markMealPlanned(long idMeal, String date) {
        return mealsDAO.planMeal(idMeal, date);
    }

    public Completable unPlanMeal(long idMeal) {
        return mealsDAO.unPlanMeal(idMeal);
    }


}
