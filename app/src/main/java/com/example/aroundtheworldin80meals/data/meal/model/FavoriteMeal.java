package com.example.aroundtheworldin80meals.data.meal.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "favorite_meals")
public class FavoriteMeal {
    private int uniqueId;
    @PrimaryKey
    @NonNull
    @ColumnInfo(name ="idMeal" )
    private Long idMeal;
    @SerializedName("strMeal")
    @ColumnInfo(name ="mealName" )
    private String mealName;
    @SerializedName("strMealThumb")
    @ColumnInfo(name ="mealPhoto" )
    private String mealPhoto;
    @SerializedName("strCategory")
    @ColumnInfo(name ="strCategory" )
    private String mealCate;
    @SerializedName("strArea")
    @ColumnInfo(name ="mealArea" )
    private String mealArea;
}
