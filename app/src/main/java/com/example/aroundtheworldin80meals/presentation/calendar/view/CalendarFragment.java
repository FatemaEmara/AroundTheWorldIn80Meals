package com.example.aroundtheworldin80meals.presentation.calendar.view;

import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aroundtheworldin80meals.R;
import com.example.aroundtheworldin80meals.data.meal.model.Meal;
import com.example.aroundtheworldin80meals.presentation.calendar.presenter.CalendarPresenter;
import com.example.aroundtheworldin80meals.presentation.calendar.presenter.CalendarPresenterImp;

import java.util.List;

public class CalendarFragment extends Fragment implements OnPlannedMealClickListener, CalendarView {


    RecyclerView calendarRecyclerView;
    PlannedMealAdapter plannedMealAdapter;
    CalendarPresenter calendarPresenter;
    DatePicker datePicker;

    public CalendarFragment() {
        super(R.layout.fragment_calendar);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        datePicker = view.findViewById(R.id.dpPlan);
        calendarRecyclerView = view.findViewById(R.id.rvPlannedMeals);
        plannedMealAdapter = new PlannedMealAdapter(this);

        calendarRecyclerView.setLayoutManager(
                new LinearLayoutManager(requireContext())
        );
        calendarRecyclerView.setAdapter(plannedMealAdapter);
        calendarPresenter = new CalendarPresenterImp(requireActivity().getApplication(), this);

        calendarPresenter.getPlannedMeals(getSelectedDate());
        datePicker.init(
                datePicker.getYear(),
                datePicker.getMonth(),
                datePicker.getDayOfMonth(),
                (view1, year, month, day) -> {
                    String date = formatDate(year, month, day);
                    calendarPresenter.getPlannedMeals(date);
                }
        );
    }

    private String formatDate(int year, int month, int day) {
        return year + "-"
                + String.format("%02d", month + 1)
                + "-" + String.format("%02d", day);
    }

    private String getSelectedDate() {
        return formatDate(
                datePicker.getYear(),
                datePicker.getMonth(),
                datePicker.getDayOfMonth()
        );
    }


    @Override
    public void onPlannedMealDeleted() {
        Toast.makeText(getContext(),
                "Meal removed from plan",
                Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showPlannedMeals(List<Meal> meals) {
        if (meals == null || meals.isEmpty()) {
            calendarRecyclerView.setVisibility(View.GONE);
            Toast.makeText(getContext(),
                    "No meals planned for this day",
                    Toast.LENGTH_SHORT).show();
        } else {
            calendarRecyclerView.setVisibility(View.VISIBLE);
            plannedMealAdapter.setMeals(meals);
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        calendarPresenter.onDestroy();
    }



    @Override
    public void onDeleteClick(Meal meal) {
        calendarPresenter.deleteFromPlannedMeals(getSelectedDate(), meal);

    }
}