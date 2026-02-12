package com.example.aroundtheworldin80meals.presentation.onboarding;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.aroundtheworldin80meals.R;

public class OnboardingFirstFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_onboarding_first, container, false);
        view.findViewById(R.id.btnNext).setOnClickListener(v ->
                Navigation.findNavController(v)
                        .navigate(R.id.action_onboardingFirstFragment_to_onboardingSecondFragment)
        );

        view.findViewById(R.id.btnSkip).setOnClickListener(v ->
                Navigation.findNavController(v)
                        .navigate(R.id.action_onboardingFirstFragment_to_signUpFragment)
        );
        return view;
    }
}