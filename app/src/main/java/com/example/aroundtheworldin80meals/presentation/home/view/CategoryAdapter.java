package com.example.aroundtheworldin80meals.presentation.home.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.aroundtheworldin80meals.R;
import com.example.aroundtheworldin80meals.data.meal.model.Category;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private List<Category> categories;
    private OnCategoryClickListener listener;
    private Fragment fragment;

    public CategoryAdapter(Fragment fragment, OnCategoryClickListener listener) {
        categories = new ArrayList<>();
        this.listener = listener;
        this.fragment = fragment;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Category category = categories.get(position);
        holder.bind(category);
    }

    @Override
    public int getItemCount() {
        return categories != null ? categories.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name;
        TextView description;

        ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.ivRecipeImage);
            name = itemView.findViewById(R.id.tvRecipeTitle);
            description = itemView.findViewById(R.id.tvCategoryDescription);

        }

        void bind(Category category) {
            name.setText(category.getName());
            description.setText(category.getCategoryDescription());
            if (fragment.isAdded()) {
                Glide.with(fragment)
                        .load(category.getThumb())
                        .into(image);
            }

            itemView.setOnClickListener(v ->
                    listener.onCategoryClick(category.getName()));
        }
    }
}

