package com.example.catologodelivros.ui.cart;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.catologodelivros.R;
import com.example.catologodelivros.cart.CartManager;
import com.example.catologodelivros.model.CartItem;
import com.example.catologodelivros.ui.BaseActivity;
import com.example.catologodelivros.util.FormatUtils;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.List;

public class CartActivity extends BaseActivity {

    private CartManager cartManager;
    private CartItemAdapter adapter;
    private TextView totalText;
    private TextView emptyStateText;
    private View clearButton;
    private View checkoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        setupToolbar(R.id.cartToolbar, true, R.string.cart_title);

        cartManager = CartManager.getInstance(this);
        totalText = findViewById(R.id.cartTotalText);
        emptyStateText = findViewById(R.id.cartEmptyStateText);
        clearButton = findViewById(R.id.clearCartButton);
        checkoutButton = findViewById(R.id.checkoutButton);

        RecyclerView recyclerView = findViewById(R.id.cartRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CartItemAdapter(new CartItemAdapter.CartActionsListener() {
            @Override
            public void onIncrease(CartItem item) {
                if (cartManager.increase(item.getBook())) {
                    renderCart();
                } else {
                    Toast.makeText(CartActivity.this, R.string.stock_limit_message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onDecrease(CartItem item) {
                cartManager.decrease(item.getBook().getId());
                renderCart();
            }

            @Override
            public void onRemove(CartItem item) {
                cartManager.remove(item.getBook().getId());
                renderCart();
            }
        });
        recyclerView.setAdapter(adapter);

        clearButton.setOnClickListener(v -> {
            cartManager.clear();
            renderCart();
        });

        checkoutButton.setOnClickListener(v -> showCheckoutDialog());

        renderCart();
    }

    @Override
    protected boolean shouldShowCartAction() {
        return false;
    }

    private void renderCart() {
        List<CartItem> items = cartManager.getItems();
        adapter.submitList(items);
        boolean isEmpty = items.isEmpty();
        emptyStateText.setVisibility(isEmpty ? View.VISIBLE : View.GONE);
        totalText.setText(getString(R.string.total_label, FormatUtils.formatCurrency(cartManager.getTotalValue())));
        clearButton.setEnabled(!isEmpty);
        checkoutButton.setEnabled(!isEmpty);
        invalidateOptionsMenu();
    }

    private void showCheckoutDialog() {
        new MaterialAlertDialogBuilder(this)
                .setTitle(R.string.checkout_title)
                .setMessage(getString(R.string.checkout_message, FormatUtils.formatCurrency(cartManager.getTotalValue())))
                .setNegativeButton(R.string.cancel, null)
                .setPositiveButton(R.string.finish_purchase, (dialog, which) -> {
                    cartManager.clear();
                    renderCart();
                    Toast.makeText(this, R.string.purchase_success, Toast.LENGTH_LONG).show();
                })
                .show();
    }
}
