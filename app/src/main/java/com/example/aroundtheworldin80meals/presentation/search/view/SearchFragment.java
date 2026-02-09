package com.example.aroundtheworldin80meals.presentation.search.view;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aroundtheworldin80meals.R;
import com.example.aroundtheworldin80meals.data.meal.model.Area;
import com.example.aroundtheworldin80meals.data.meal.model.Category;
import com.example.aroundtheworldin80meals.data.meal.model.Ingredient;
import com.example.aroundtheworldin80meals.data.meal.model.Meal;
import com.example.aroundtheworldin80meals.presentation.search.presenter.SearchPresenter;
import com.example.aroundtheworldin80meals.presentation.search.presenter.SearchPresenterImp;
import com.example.aroundtheworldin80meals.utils.FlagUtils;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.List;


public class SearchFragment extends Fragment implements OnMealClickListener, OnAreaClickListener, OnCategoryClickListener, OnIngredientClickListener, SearchView {


    private RecyclerView ingredientsRecycler;


    private MealAdapter mealAdapter;

    private SearchItemAdapter searchItemAdapter;

    private ProgressBar progressBar;
    private TextView errorText;

    private SearchPresenter presenter;
    EditText etSearch;

    public SearchFragment() {
        super(R.layout.fragment_search);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //progressBar = view.findViewById(R.id.progress_circular);
        //errorText = view.findViewById(R.id.tv_error);
        etSearch = view.findViewById(R.id.etSearch);
        ingredientsRecycler = view.findViewById(R.id.rvAllCategories);
        searchItemAdapter = new SearchItemAdapter(this,
                this,
                this,
                this);

        ingredientsRecycler.setLayoutManager(new GridLayoutManager(getContext(), 2));

        mealAdapter = new MealAdapter(this);

        ingredientsRecycler.setAdapter(searchItemAdapter);
        presenter = new SearchPresenterImp(requireActivity().getApplication(), this);
        presenter.getIngredients();

        EditText searchEditText = view.findViewById(R.id.etSearch);
        ChipGroup chipGroup = view.findViewById(R.id.chipGroup);
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {

            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                presenter.searchMealByName(s.toString());
            }
        });


        Chip chipCategories = (Chip) chipGroup.getChildAt(0);
        Chip chipCountries = (Chip) chipGroup.getChildAt(1);
        Chip chipIngredients = (Chip) chipGroup.getChildAt(2);


        chipCategories.setOnClickListener(v -> {
            etSearch.setText("");
            showSearchItems();
            presenter.getCategories();
        });

        chipCountries.setOnClickListener(v -> {
            etSearch.setText("");
            showSearchItems();
            presenter.getAreas();
        });

        chipIngredients.setOnClickListener(v -> {
            etSearch.setText("");
            showSearchItems();
            presenter.getIngredients();
        });

    }

    private void showSearchItems() {
        ingredientsRecycler.setLayoutManager(new GridLayoutManager(getContext(), 2));
        ingredientsRecycler.setAdapter(searchItemAdapter);
    }

    private void showMeals(List<Meal> meals) {
        ingredientsRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        ingredientsRecycler.setAdapter(mealAdapter);
        mealAdapter.setMeals(meals);
    }


    @Override
    public void onAreaClick(String areaName) {
        presenter.filterByCountry(areaName);

    }

    @Override
    public void onCategoryClick(String categoryName) {
        presenter.filterByCategory(categoryName);

    }

    @Override
    public void onIngredientClick(String ingredientName) {
        presenter.filterByIngredients(ingredientName);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showCategories(List<Category> categories) {

        List<SearchItem> items = new ArrayList<>();
        for (Category c : categories) {
            items.add(new SearchItem(SearchItemType.CATEGORY, c.getName(), c.getThumb()));
        }
        searchItemAdapter.setItem(items);
    }

    @Override
    public void showAreas(List<Area> areas) {


        List<SearchItem> items = new ArrayList<>();
        for (Area a : areas) {
            String flagUrl = FlagUtils.getThumb(a.getName());
            items.add(new SearchItem(SearchItemType.AREA, a.getName(), flagUrl));
        }
        searchItemAdapter.setItem(items);
    }

    @Override
    public void showIngredients(List<Ingredient> ingredients) {
        List<SearchItem> items = new ArrayList<>();
        for (Ingredient i : ingredients) {
            items.add(new SearchItem(SearchItemType.INGREDIENT, i.getName(), i.getThumb()));
        }
        searchItemAdapter.setItem(items);
    }


    @Override
    public void showMealById(Meal meal) {

    }

    @Override
    public void showMealsByCountry(List<Meal> meals) {
        showMeals(meals);

    }

    @Override
    public void showMealsByCategory(List<Meal> meals) {
        showMeals(meals);

    }

    @Override
    public void showMealsByIngredients(List<Meal> meals) {

        showMeals(meals);
    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void searchMealByName(List<Meal> meals) {
        showMeals(meals);
    }


    @Override
    public void addMealToFavorite(Meal meal) {
        presenter.addMealToFavorite(meal);
        Toast.makeText(getContext(), "Product added to favorites", Toast.LENGTH_SHORT).show();

    }
}
