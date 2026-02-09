package com.example.aroundtheworldin80meals.data.db;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.aroundtheworldin80meals.data.meal.model.Meal;

@Database(entities = {Meal.class}, version = 1)
public abstract class MealsDatabase extends RoomDatabase {
    private static MealsDatabase instance = null;

    public abstract MealsDAO getMealsDAO();

    public static MealsDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (MealsDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(), MealsDatabase.class, "meals_db").build();
                }
            }
        }
        return instance;
    }

}
