package com.example.aroundtheworldin80meals;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;


public class OnboardingThirdFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_onboarding_third, container, false);

        view.findViewById(R.id.btnGetStarted).setOnClickListener(v ->
                Navigation.findNavController(v)
                        .navigate(R.id.action_onboardingThirdFragment_to_signUpFragment)
        );
        return view;
    }
}