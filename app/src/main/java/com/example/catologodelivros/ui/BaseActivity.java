package com.example.catologodelivros.ui;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.catologodelivros.R;
import com.example.catologodelivros.cart.CartManager;
import com.example.catologodelivros.ui.cart.CartActivity;
import com.example.catologodelivros.ui.login.LoginActivity;
import com.example.catologodelivros.ui.support.SupportActivity;
import com.example.catologodelivros.util.SessionManager;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public abstract class BaseActivity extends AppCompatActivity {

    protected void setupToolbar(@IdRes int toolbarId, boolean showBackButton, @StringRes int titleRes) {
        Toolbar toolbar = findViewById(toolbarId);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(showBackButton);
            getSupportActionBar().setTitle(titleRes);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_global, menu);
        menu.findItem(R.id.action_cart).setVisible(shouldShowCartAction());
        menu.findItem(R.id.action_support).setVisible(shouldShowSupportAction());
        menu.findItem(R.id.action_about).setVisible(shouldShowAboutAction());
        menu.findItem(R.id.action_logout).setVisible(shouldShowLogoutAction());
        setupCartBadge(menu.findItem(R.id.action_cart));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == android.R.id.home) {
            getOnBackPressedDispatcher().onBackPressed();
            return true;
        }
        if (itemId == R.id.action_cart) {
            startActivity(new Intent(this, CartActivity.class));
            return true;
        }
        if (itemId == R.id.action_support) {
            startActivity(new Intent(this, SupportActivity.class));
            return true;
        }
        if (itemId == R.id.action_about) {
            AboutDialogHelper.show(this);
            return true;
        }
        if (itemId == R.id.action_logout) {
            showLogoutConfirmation();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        invalidateOptionsMenu();
    }

    protected boolean shouldShowCartAction() {
        return true;
    }

    protected boolean shouldShowSupportAction() {
        return true;
    }

    protected boolean shouldShowAboutAction() {
        return true;
    }

    protected boolean shouldShowLogoutAction() {
        return true;
    }

    private void setupCartBadge(MenuItem cartItem) {
        View actionView = cartItem.getActionView();
        if (actionView == null) {
            return;
        }
        TextView badgeText = actionView.findViewById(R.id.cartBadgeText);
        ImageView iconView = actionView.findViewById(R.id.cartIconView);
        iconView.setImageResource(R.drawable.ic_cart);
        int count = CartManager.getInstance(this).getTotalItems();
        badgeText.setVisibility(count > 0 ? View.VISIBLE : View.GONE);
        badgeText.setText(String.valueOf(count));
        actionView.setOnClickListener(v -> onOptionsItemSelected(cartItem));
    }

    private void showLogoutConfirmation() {
        AlertDialog dialog = new MaterialAlertDialogBuilder(this)
                .setTitle(R.string.logout_title)
                .setMessage(R.string.logout_message)
                .setNegativeButton(R.string.cancel, null)
                .setPositiveButton(R.string.logout, (d, which) -> {
                    CartManager.getInstance(this).clear();
                    new SessionManager(this).logout();
                    Intent intent = new Intent(this, LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                })
                .create();
        dialog.show();
    }
}
