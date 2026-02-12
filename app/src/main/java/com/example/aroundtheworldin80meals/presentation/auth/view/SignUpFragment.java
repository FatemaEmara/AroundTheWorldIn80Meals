package com.example.aroundtheworldin80meals.presentation.auth.view;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.aroundtheworldin80meals.R;
import com.example.aroundtheworldin80meals.presentation.auth.presenter.AuthPresenterImpl;
import com.google.android.material.textfield.TextInputEditText;

public class SignUpFragment extends Fragment implements AuthView {

    private AuthPresenterImpl presenter;

    private TextInputEditText etEmail, etPassword, etConfirmPassword;
    //  private View progressBar;

    private TextView tvLogin;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        presenter = new AuthPresenterImpl(this, requireContext());

        etEmail = view.findViewById(R.id.etSignUpEmail);
        etPassword = view.findViewById(R.id.etSignUpPassword);
        etConfirmPassword = view.findViewById(R.id.etSignUpConfirmPassword);
        // progressBar = view.findViewById(R.id.progressBar);
        tvLogin = view.findViewById(R.id.tvLoginUpLink);

        view.findViewById(R.id.btnSignUp).setOnClickListener(v -> attemptSignUp());

        tvLogin.setOnClickListener(v ->
                Navigation.findNavController(v)
                        .navigate(R.id.action_signUpFragment_to_loginFragment)
        );

        return view;
    }

    private void attemptSignUp() {

        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String confirm = etConfirmPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email) ||
                TextUtils.isEmpty(password) ||
                TextUtils.isEmpty(confirm)) {

            showError("All fields are required");
            return;
        }

        if (!password.equals(confirm)) {
            showError("Passwords do not match");
            return;
        }

        presenter.register(email, password);
    }

    @Override
    public void showLoading() {
        //progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        // progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigateToHome() {
        Navigation.findNavController(requireView())
                .navigate(R.id.action_signUpFragment_to_homeFragment);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.clear();
    }
}
