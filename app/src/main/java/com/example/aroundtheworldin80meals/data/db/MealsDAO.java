package com.example.aroundtheworldin80meals.data.db;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.aroundtheworldin80meals.data.meal.model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;

@Dao
public interface MealsDAO {
    @Query("Select * from meals where isFavorite = 1")
    Observable<List<Meal>> getFavMeals();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertFavMeal(Meal meal);

    @Query("DELETE FROM meals WHERE idMeal = :idMeal")
    Completable deleteFavMealById(long idMeal);

}