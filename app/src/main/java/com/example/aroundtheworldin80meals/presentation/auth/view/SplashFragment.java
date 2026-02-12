package com.example.aroundtheworldin80meals.presentation.auth.view;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavOptions;
import androidx.navigation.fragment.NavHostFragment;

import com.example.aroundtheworldin80meals.R;
import com.example.aroundtheworldin80meals.data.auth.AuthRepository;

public class SplashFragment extends Fragment {

    private AuthRepository authRepository;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        authRepository = new AuthRepository(requireContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_splash, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        new Handler(Looper.getMainLooper()).postDelayed(() -> {

            NavOptions navOptions = new NavOptions.Builder()
                    .setPopUpTo(R.id.splashFragment, true)
                    .build();

            boolean isLoggedIn = authRepository.isLoggedIn();
            boolean onboardingSeen = authRepository.isOnboardingSeen();

            android.util.Log.d("SplashFragment", "Onboarding seen: " + onboardingSeen);
            android.util.Log.d("SplashFragment", "Is logged in: " + isLoggedIn);

            if (!onboardingSeen) {
                NavHostFragment.findNavController(this)
                        .navigate(R.id.action_splashFragment_to_onboardingFirstFragment,
                                null,
                                navOptions);

            } else if (isLoggedIn) {
                NavHostFragment.findNavController(this)
                        .navigate(R.id.action_splashFragment_to_homeFragment,
                                null,
                                navOptions);

            } else {
                NavHostFragment.findNavController(this)
                        .navigate(R.id.action_splashFragment_to_loginFragment,
                                null,
                                navOptions);
            }

        }, 2000);
    }
}


