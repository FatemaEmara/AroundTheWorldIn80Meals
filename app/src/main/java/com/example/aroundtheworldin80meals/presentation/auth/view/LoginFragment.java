package com.example.aroundtheworldin80meals.presentation.auth.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.aroundtheworldin80meals.R;
import com.example.aroundtheworldin80meals.presentation.auth.presenter.AuthPresenterImpl;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.material.textfield.TextInputEditText;

public class LoginFragment extends Fragment implements AuthView {

    private AuthPresenterImpl presenter;

    private TextInputEditText etEmail, etPassword;
    private View progressBar;

    private GoogleSignInClient googleSignInClient;
    private ActivityResultLauncher<Intent> googleLauncher;

    private Button btnGoogle, btnGuest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);

        presenter = new AuthPresenterImpl(this, requireContext());

        etEmail = view.findViewById(R.id.etEmail);
        etPassword = view.findViewById(R.id.etPassword);
        progressBar = view.findViewById(R.id.progressBar);

        setupGoogle(view);

        view.findViewById(R.id.btnLogIn).setOnClickListener(v -> attemptLogin());

        btnGuest = view.findViewById(R.id.btnGuest);
        if (btnGuest != null) {
            btnGuest.setOnClickListener(v -> attemptGuestLogin());
        }

        return view;
    }

    private void attemptLogin() {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            showError("Please fill all fields");
            return;
        }

        presenter.login(email, password);
    }

    private void attemptGuestLogin() {
        new androidx.appcompat.app.AlertDialog.Builder(requireContext())
                .setTitle("Continue as Guest")
                .setMessage("Guest mode has limited features. You won't be able to save your progress or sync across devices. Continue?")
                .setPositiveButton("Continue", (dialog, which) -> {
                    showLoading();
                    presenter.loginAsGuest();
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void setupGoogle(View view) {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso);

        googleLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getData() != null) {
                        handleGoogleResult(result.getData());
                    } else {
                        showError("Google Sign-In cancelled");
                        hideLoading();
                    }
                });

        btnGoogle = view.findViewById(R.id.btnGoogle);
        btnGoogle.setOnClickListener(v -> {
            showLoading();
            googleSignInClient.signOut().addOnCompleteListener(task -> {
                Intent signInIntent = googleSignInClient.getSignInIntent();
                googleLauncher.launch(signInIntent);
            });
        });
    }

    private void handleGoogleResult(Intent data) {
        try {
            GoogleSignInAccount account = GoogleSignIn.getSignedInAccountFromIntent(data)
                    .getResult(ApiException.class);

            if (account != null && account.getIdToken() != null) {
                presenter.loginWithGoogle(account.getIdToken());
            } else {
                hideLoading();
                showError("Failed to get Google account");
            }

        } catch (ApiException e) {
            hideLoading();
            showError("Google Sign-In failed: " + e.getMessage());
        } catch (Exception e) {
            hideLoading();
            showError("Error: " + e.getMessage());
        }
    }

    @Override
    public void showLoading() {
        if (progressBar != null) {
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideLoading() {
        if (progressBar != null) {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigateToHome() {
        Navigation.findNavController(requireView())
                .navigate(R.id.action_loginFragment_to_homeFragment);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.clear();
    }
}
