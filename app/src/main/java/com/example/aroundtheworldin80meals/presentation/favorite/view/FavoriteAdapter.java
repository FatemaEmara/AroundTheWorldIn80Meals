package com.example.aroundtheworldin80meals.presentation.favorite.view;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.aroundtheworldin80meals.R;
import com.example.aroundtheworldin80meals.data.meal.model.Meal;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {
    private List<Meal> meals;
    private OnFavoriteClickListener listener;
    public static final String TAG = "FavoriteAdapter";

    public FavoriteAdapter(OnFavoriteClickListener listener) {
        this.meals = new java.util.ArrayList<>();
        this.listener = listener;

        Log.i(TAG, "FavoriteAdapter: ");
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.recipe_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Meal meal = meals.get(position);
        holder.bind(meal);
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    public void setList(List<Meal> updatedMeals) {
        this.meals = updatedMeals;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView favMealImg;
        TextView favMealName;
        TextView favMealArea;
        ImageView removeFavBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            favMealName = itemView.findViewById(R.id.tvMealRecipeTitle);
            favMealArea = itemView.findViewById(R.id.tvMealRecipeArea);
            removeFavBtn = itemView.findViewById(R.id.ivFavoriteIcon);
            favMealImg = itemView.findViewById(R.id.ivMealRecipeImage);
        }

        void bind(Meal meal) {
            favMealArea.setText(meal.getMealArea());
            favMealName.setText(meal.getMealName());

            Glide.with(itemView)
                    .load(meal.getMealPhoto())
                    .into(favMealImg);


            removeFavBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onFavoriteClick(meal);
                }
            });
        }
    }
}