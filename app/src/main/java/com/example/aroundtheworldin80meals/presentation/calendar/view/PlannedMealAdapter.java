package com.example.aroundtheworldin80meals.presentation.calendar.view;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.aroundtheworldin80meals.R;
import com.example.aroundtheworldin80meals.data.meal.model.Meal;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;

public class PlannedMealAdapter extends
        RecyclerView.Adapter<com.example.aroundtheworldin80meals.presentation.calendar.view.PlannedMealAdapter.ViewHolder> {

    private List<Meal> meals;
    private OnPlannedMealClickListener listener;

    public PlannedMealAdapter(OnPlannedMealClickListener listener) {
        meals = new ArrayList<>();
        this.listener = listener;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public com.example.aroundtheworldin80meals.presentation.calendar.view.PlannedMealAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipe_item_with_del, parent, false);
        return new com.example.aroundtheworldin80meals.presentation.calendar.view.PlannedMealAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull com.example.aroundtheworldin80meals.presentation.calendar.view.PlannedMealAdapter.ViewHolder holder, int position) {
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
        ImageView delPlannedMeal;

        ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.ivMealRecipeImage);
            name = itemView.findViewById(R.id.tvMealRecipeTitle);
            area = itemView.findViewById(R.id.tvMealRecipeArea);
            delPlannedMeal = itemView.findViewById(R.id.ivDeleteIcon);

        }

        void bind(Meal meal) {
            name.setText(meal.getMealName());
            area.setText(meal.getMealArea());

            Glide.with(itemView)
                    .load(meal.getMealPhoto())
                    .into(image);

            delPlannedMeal.setOnClickListener(v ->
                    listener.onDeleteClick(meal));
        }
    }
}