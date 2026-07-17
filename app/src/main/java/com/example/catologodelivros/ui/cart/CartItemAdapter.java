package com.example.catologodelivros.ui.cart;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.catologodelivros.R;
import com.example.catologodelivros.model.CartItem;
import com.example.catologodelivros.util.FormatUtils;

import java.util.ArrayList;
import java.util.List;

public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.CartItemViewHolder> {

    public interface CartActionsListener {
        void onIncrease(CartItem item);

        void onDecrease(CartItem item);

        void onRemove(CartItem item);
    }

    private final List<CartItem> items = new ArrayList<>();
    private final CartActionsListener listener;

    public CartItemAdapter(CartActionsListener listener) {
        this.listener = listener;
    }

    public void submitList(List<CartItem> newItems) {
        items.clear();
        items.addAll(newItems);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CartItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new CartItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartItemViewHolder holder, int position) {
        holder.bind(items.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class CartItemViewHolder extends RecyclerView.ViewHolder {
        private final TextView titleText;
        private final TextView unitPriceText;
        private final TextView quantityText;
        private final TextView subtotalText;
        private final TextView stockHintText;
        private final ImageButton increaseButton;
        private final ImageButton decreaseButton;
        private final ImageButton removeButton;

        CartItemViewHolder(@NonNull View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.cartItemTitleText);
            unitPriceText = itemView.findViewById(R.id.cartItemUnitPriceText);
            quantityText = itemView.findViewById(R.id.cartItemQuantityText);
            subtotalText = itemView.findViewById(R.id.cartItemSubtotalText);
            stockHintText = itemView.findViewById(R.id.cartItemStockHintText);
            increaseButton = itemView.findViewById(R.id.increaseQuantityButton);
            decreaseButton = itemView.findViewById(R.id.decreaseQuantityButton);
            removeButton = itemView.findViewById(R.id.removeItemButton);
        }

        void bind(CartItem item, CartActionsListener listener) {
            titleText.setText(item.getBook().getTitle());
            unitPriceText.setText(itemView.getContext().getString(
                    R.string.cart_unit_price, FormatUtils.formatCurrency(item.getBook().getPrice())));
            quantityText.setText(String.valueOf(item.getQuantity()));
            subtotalText.setText(itemView.getContext().getString(
                    R.string.cart_subtotal, FormatUtils.formatCurrency(item.getSubtotal())));
            stockHintText.setText(itemView.getContext().getString(
                    R.string.stock_limit_hint, item.getBook().getStock()));
            increaseButton.setOnClickListener(v -> listener.onIncrease(item));
            decreaseButton.setOnClickListener(v -> listener.onDecrease(item));
            removeButton.setOnClickListener(v -> listener.onRemove(item));
        }
    }
}
