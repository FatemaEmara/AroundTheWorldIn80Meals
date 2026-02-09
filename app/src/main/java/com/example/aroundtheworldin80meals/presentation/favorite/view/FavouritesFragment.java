package com.example.aroundtheworldin80meals.presentation.favorite.view;

import static androidx.lifecycle.AndroidViewModel_androidKt.getApplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.aroundtheworldin80meals.R;
import com.example.aroundtheworldin80meals.data.meal.local.MealsLocalDataSource;
import com.example.aroundtheworldin80meals.data.meal.model.Meal;
import com.example.aroundtheworldin80meals.presentation.favorite.presenter.FavoritePresenterImp;
import com.example.aroundtheworldin80meals.presentation.favorite.presenter.FavoritesPresenter;

import java.util.List;

public class FavouritesFragment extends Fragment implements OnFavoriteClickListener, FavoriteView {


    RecyclerView favoriteRecyclerView;
    FavoriteAdapter favoriteAdapter;

    FavoritesPresenter favoritesPresenter;

    public FavouritesFragment() {
        super(R.layout.fragment_favourites);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        favoriteRecyclerView = view.findViewById(R.id.rvFavorites);
        favoriteAdapter = new FavoriteAdapter(this);
        favoriteRecyclerView.setLayoutManager(
                new LinearLayoutManager(requireContext())
        );
        favoriteRecyclerView.setAdapter(favoriteAdapter);
        favoritesPresenter = new FavoritePresenterImp(requireActivity().getApplication(), this);


        favoritesPresenter.getFavoriteMeals();
    }

    @Override
    public void onMealDeleted() {
        Toast.makeText(getContext(), "Product is deleted!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFavoriteMeals(List<Meal> meals) {

        favoriteAdapter.setList(meals);
    }

    @Override
    public void onFavoriteClick(Meal meal) {

        favoritesPresenter.deleteFromFavorite(meal);
    }
}