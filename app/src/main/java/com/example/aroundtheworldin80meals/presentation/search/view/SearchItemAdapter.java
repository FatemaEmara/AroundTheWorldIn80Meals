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

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;


public class SearchItemAdapter extends RecyclerView.Adapter<SearchItemAdapter.ViewHolder> {

    private List<SearchItem> items;
    private OnIngredientClickListener ingredientClickListener;
    private OnAreaClickListener areaClickListener;

    private OnCategoryClickListener categoryClickListener;

    private Fragment fragment;

    public SearchItemAdapter(Fragment fragment,
                             OnIngredientClickListener ingredientClickListener,
                             OnAreaClickListener areaClickListener,
                             OnCategoryClickListener categoryClickListener) {
        items = new ArrayList<>();
        this.ingredientClickListener = ingredientClickListener;
        this.areaClickListener = areaClickListener;
        this.categoryClickListener = categoryClickListener;


        this.fragment = fragment;
    }

    public void setItem(List<SearchItem> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SearchItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_item, parent, false);
        return new SearchItemAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchItemAdapter.ViewHolder holder, int position) {
        SearchItem searchItem = items.get(position);
        holder.bind(searchItem);
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name;

        ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.ivIngredientImage);
            name = itemView.findViewById(R.id.tvIngredientTitle);


        }

        void bind(SearchItem searchItem) {
            name.setText(searchItem.getName());

            if (fragment.isAdded()) {
                Glide.with(fragment).load(searchItem.getThumb()).into(image);
            }
            itemView.setOnClickListener(v -> {
                switch (searchItem.getType()) {
                    case AREA:
                        areaClickListener.onAreaClick(searchItem.getName());
                        break;

                    case CATEGORY:
                        categoryClickListener.onCategoryClick(searchItem.getName());
                        break;

                    case INGREDIENT:
                        ingredientClickListener.onIngredientClick(searchItem.getName());
                        break;
                }
            });


        }
    }
}