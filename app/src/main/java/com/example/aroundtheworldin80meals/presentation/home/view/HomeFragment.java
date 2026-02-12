package com.example.aroundtheworldin80meals.presentation.home.view;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.aroundtheworldin80meals.R;
import com.example.aroundtheworldin80meals.data.meal.model.Area;
import com.example.aroundtheworldin80meals.data.meal.model.Category;
import com.example.aroundtheworldin80meals.data.meal.model.Meal;
import com.example.aroundtheworldin80meals.presentation.home.presenter.HomePresenter;
import com.example.aroundtheworldin80meals.presentation.home.presenter.HomePresenterImpl;

import java.util.List;

public class HomeFragment extends Fragment
        implements OnAreaClickListener, OnCategoryClickListener, HomeView {

    private RecyclerView categoriesRecycler;
    private RecyclerView areasRecycler;

    private CategoryAdapter categoryAdapter;
    private AreaAdapter areaAdapter;

    private ProgressBar progressBar;
    private TextView errorText;

    private HomePresenter presenter;

    private ImageView randomMealImage;
    private TextView randomMealName;

    public HomeFragment() {
        super(R.layout.fragment_home);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //progressBar = view.findViewById(R.id.progress_circular);
        //errorText = view.findViewById(R.id.tv_error);

        categoriesRecycler = view.findViewById(R.id.rvAllCategoriesRecipes);
        areasRecycler = view.findViewById(R.id.rvAreas);

        randomMealImage = view.findViewById(R.id.ivRandomMealImage);
        randomMealName = view.findViewById(R.id.tvRandomMealTitle);

        categoryAdapter = new CategoryAdapter(this, this);
        areaAdapter = new AreaAdapter(this, this);

        categoriesRecycler.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false)
        );
        areasRecycler.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false)
        );

        categoriesRecycler.setAdapter(categoryAdapter);
        areasRecycler.setAdapter(areaAdapter);

        presenter = new HomePresenterImpl(requireActivity().getApplication(), this);

        presenter.getRandomMeal();
        presenter.getCategories();
        presenter.getAreas();
    }


    @Override
    public void showLoading() {
//        progressBar.setVisibility(View.VISIBLE);
//        errorText.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        // progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showRandomMeal(Meal meal) {

        if (meal == null || !isAdded()) return;

        randomMealName.setText(meal.getMealName());

        Glide.with(requireContext())
                .load(meal.getMealPhoto())
                .into(randomMealImage);

        randomMealImage.setOnClickListener(v -> {

        });
    }


    @Override
    public void showCategories(List<Category> categories) {
        categoryAdapter.setCategories(categories);
    }

    @Override
    public void showAreas(List<Area> areas) {
        areaAdapter.setAreas(areas);
    }

    @Override
    public void showError(String message) {
//        errorText.setVisibility(View.VISIBLE);
//        errorText.setText(message);
    }


    @Override
    public void onCategoryClick(String categoryName) {
        Bundle bundle = new Bundle();
        bundle.putString("filterType", "Category");
        bundle.putString("filterValue", categoryName);

        NavHostFragment.findNavController(this)
                .navigate(R.id.action_homeFragment_to_searchFragment, bundle);

    }

    @Override
    public void onAreaClick(String areaName) {
        Bundle bundle = new Bundle();
        bundle.putString("filterType", "AREA");
        bundle.putString("filterValue", areaName);

        NavHostFragment.findNavController(this)
                .navigate(R.id.action_homeFragment_to_searchFragment, bundle);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.onDestroyView();
    }
}







