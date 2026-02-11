package com.example.aroundtheworldin80meals.presentation.calendar.presenter;

import android.app.Application;

import com.example.aroundtheworldin80meals.data.meal.MealRepository;
import com.example.aroundtheworldin80meals.data.meal.model.Meal;
import com.example.aroundtheworldin80meals.presentation.calendar.view.CalendarView;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class CalendarPresenterImp implements CalendarPresenter {

    MealRepository mealRepository;
    CalendarView calendarView;
    private final CompositeDisposable disposable = new CompositeDisposable();


    public CalendarPresenterImp(Application application, CalendarView calendarView) {
        this.mealRepository = new MealRepository(application);
        this.calendarView = calendarView;
    }

    @Override
    public void getPlannedMeals(String date) {

        disposable.add(
                mealRepository.getPlannedMeals(date)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                calendarView::showPlannedMeals,
                                throwable -> {
                                }
                        )
        );

    }

    @Override
    public void deleteFromPlannedMeals(String date, Meal meal) {
        disposable.add(
                mealRepository.deletePlannedMeal(meal)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                () -> {

                                    calendarView.onPlannedMealDeleted();
                                    getPlannedMeals(date);

                                },
                                throwable -> {
                                }
                        )
        );
    }

    @Override
    public void onDestroy() {

        disposable.dispose();
    }


}
