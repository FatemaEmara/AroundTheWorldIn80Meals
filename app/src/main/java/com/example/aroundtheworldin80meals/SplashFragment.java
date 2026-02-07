package com.example.aroundtheworldin80meals;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class SplashFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            NavHostFragment.findNavController(this)
                    .navigate(R.id.action_splashFragment_to_onboardingFirstFragment);
        }, 2000);

        return inflater.inflate(R.layout.fragment_splash, container, false);
    }
}