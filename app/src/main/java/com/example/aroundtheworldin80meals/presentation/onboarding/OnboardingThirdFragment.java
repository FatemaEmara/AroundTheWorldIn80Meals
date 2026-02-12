package com.example.aroundtheworldin80meals.presentation.onboarding;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.aroundtheworldin80meals.R;
import com.example.aroundtheworldin80meals.data.auth.AuthRepository;


public class OnboardingThirdFragment extends Fragment {

    private AuthRepository authRepository;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        authRepository = new AuthRepository(requireContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_onboarding_third, container, false);

        view.findViewById(R.id.btnGetStarted).setOnClickListener(v -> {
            authRepository.setOnboardingSeenSync();

            Navigation.findNavController(v)
                    .navigate(R.id.action_onboardingThirdFragment_to_signUpFragment);
        });

        return view;
    }
}
