package com.example.catologodelivros.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.catologodelivros.R;
import com.example.catologodelivros.ui.catalog.CatalogActivity;
import com.example.catologodelivros.util.AppConstants;
import com.example.catologodelivros.util.SessionManager;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout usernameLayout;
    private TextInputLayout passwordLayout;
    private TextInputEditText usernameEditText;
    private TextInputEditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameLayout = findViewById(R.id.usernameLayout);
        passwordLayout = findViewById(R.id.passwordLayout);
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        Button loginButton = findViewById(R.id.loginButton);

        ((android.widget.TextView) findViewById(R.id.credentialsHint)).setText(
                getString(R.string.demo_credentials, AppConstants.DEMO_USERNAME, AppConstants.DEMO_PASSWORD));

        loginButton.setOnClickListener(v -> attemptLogin());
    }

    private void attemptLogin() {
        String username = getText(usernameEditText);
        String password = getText(passwordEditText);

        usernameLayout.setError(null);
        passwordLayout.setError(null);

        boolean hasError = false;
        if (TextUtils.isEmpty(username)) {
            usernameLayout.setError(getString(R.string.error_empty_user));
            hasError = true;
        }
        if (TextUtils.isEmpty(password)) {
            passwordLayout.setError(getString(R.string.error_empty_password));
            hasError = true;
        }
        if (hasError) {
            return;
        }

        SessionManager sessionManager = new SessionManager(this);
        if (sessionManager.authenticate(username, password)) {
            Intent intent = new Intent(this, CatalogActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        } else {
            Toast.makeText(this, R.string.error_invalid_login, Toast.LENGTH_SHORT).show();
        }
    }

    private String getText(TextInputEditText editText) {
        return editText.getText() == null ? "" : editText.getText().toString().trim();
    }
}
