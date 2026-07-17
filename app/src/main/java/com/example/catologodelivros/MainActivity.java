package com.example.catologodelivros;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.catologodelivros.ui.catalog.CatalogActivity;
import com.example.catologodelivros.ui.login.LoginActivity;
import com.example.catologodelivros.util.SessionManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SessionManager sessionManager = new SessionManager(this);
        Intent destination = sessionManager.isLoggedIn()
                ? new Intent(this, CatalogActivity.class)
                : new Intent(this, LoginActivity.class);
        startActivity(destination);
        finish();
    }
}
