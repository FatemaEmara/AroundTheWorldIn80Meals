package com.example.aroundtheworldin80meals.presentation.search.view;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.aroundtheworldin80meals.R;
import com.example.aroundtheworldin80meals.data.meal.model.Meal;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;

public class MealAdapter extends
        RecyclerView.Adapter<com.example.aroundtheworldin80meals.presentation.search.view.MealAdapter.ViewHolder> {

    private List<Meal> meals;
    private OnMealClickListener listener;
    private Fragment fragment;

    public MealAdapter(Fragment fragment, OnMealClickListener listener) {
        meals = new ArrayList<>();
        this.listener = listener;
        this.fragment = fragment;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public com.example.aroundtheworldin80meals.presentation.search.view.MealAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipe_item, parent, false);
        return new com.example.aroundtheworldin80meals.presentation.search.view.MealAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull com.example.aroundtheworldin80meals.presentation.search.view.MealAdapter.ViewHolder holder, int position) {
        Meal meal = meals.get(position);
        holder.bind(meal);
    }

    @Override
    public int getItemCount() {
        return meals != null ? meals.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name;
        TextView area;

        ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.ivMealRecipeImage);
            name = itemView.findViewById(R.id.tvMealRecipeTitle);
            area = itemView.findViewById(R.id.tvMealRecipeArea);


        }

        void bind(Meal meal) {
            name.setText(meal.getMealName());
            area.setText(meal.getMealArea());
            if (fragment.isAdded()) {
                Glide.with(fragment)
                        .load(meal.getMealPhoto())
                        .into(image);
            }

            itemView.setOnClickListener(v ->
                    listener.onMealClick(meal.getMealName()));
        }
    }
}