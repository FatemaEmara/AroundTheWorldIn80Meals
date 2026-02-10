package com.example.aroundtheworldin80meals.data.db;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.aroundtheworldin80meals.data.meal.model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface MealDao {
    @Query("Select * from meals  where isFavorite = 1")
    Flowable<List<Meal>> getFavoriteMeals();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertFavMeal(Meal meal);

    @Query("DELETE FROM meals WHERE idMeal = :idMeal  AND isFavorite = 1")
    Completable deleteFavMealById(long idMeal);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertPlannedMeal(Meal meal);

    @Query("SELECT * FROM meals WHERE date = :date")
    Flowable<List<Meal>> getPlannedMealsByDate(String date);

    @Query("DELETE FROM meals WHERE idMeal = :idMeal AND date = :date")
    Completable deletePlannedMealById(long idMeal, String date);
}



