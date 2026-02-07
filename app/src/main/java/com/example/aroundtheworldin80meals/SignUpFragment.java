package com.example.aroundtheworldin80meals;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;


public class SignUpFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        view.findViewById(R.id.tvSignUpLink).setOnClickListener(v ->
                Navigation.findNavController(v)
                        .navigate(R.id.action_signUpFragment_to_loginFragment)
        );
        return view;
    }
}