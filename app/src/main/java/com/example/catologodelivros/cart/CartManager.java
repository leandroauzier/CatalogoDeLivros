package com.example.catologodelivros.cart;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.catologodelivros.data.BookRepository;
import com.example.catologodelivros.model.Book;
import com.example.catologodelivros.model.CartItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class CartManager {

    private static final String PREFS_NAME = "catalogo_cart";
    private static final String KEY_CART = "cart_items";
    private static CartManager instance;

    private final SharedPreferences preferences;
    private final CartState cartState;

    private CartManager(Context context) {
        preferences = context.getApplicationContext()
                .getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        cartState = new CartState();
        restore();
    }

    public static synchronized CartManager getInstance(Context context) {
        if (instance == null) {
            instance = new CartManager(context);
        }
        return instance;
    }

    public boolean addBook(Book book) {
        boolean added = cartState.addBook(book);
        if (added) {
            persist();
        }
        return added;
    }

    public boolean increase(Book book) {
        boolean updated = cartState.increase(book);
        if (updated) {
            persist();
        }
        return updated;
    }

    public void decrease(int bookId) {
        cartState.decrease(bookId);
        persist();
    }

    public void remove(int bookId) {
        cartState.remove(bookId);
        persist();
    }

    public void clear() {
        cartState.clear();
        persist();
    }

    public int getQuantity(int bookId) {
        return cartState.getQuantity(bookId);
    }

    public int getTotalItems() {
        return cartState.getTotalItems();
    }

    public double getTotalValue() {
        return cartState.getTotalValue(BookRepository.getAllBooks());
    }

    public List<CartItem> getItems() {
        return Collections.unmodifiableList(cartState.toCartItems(BookRepository.getAllBooks()));
    }

    private void persist() {
        JSONArray array = new JSONArray();
        for (Map.Entry<Integer, Integer> entry : cartState.snapshot().entrySet()) {
            JSONObject object = new JSONObject();
            try {
                object.put("bookId", entry.getKey());
                object.put("quantity", entry.getValue());
                array.put(object);
            } catch (JSONException ignored) {
            }
        }
        preferences.edit().putString(KEY_CART, array.toString()).apply();
    }

    private void restore() {
        String rawCart = preferences.getString(KEY_CART, "[]");
        try {
            JSONArray array = new JSONArray(rawCart);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                Book book = BookRepository.getBookById(object.getInt("bookId"));
                if (book != null) {
                    cartState.setQuantity(book, object.getInt("quantity"));
                }
            }
        } catch (JSONException ignored) {
        }
    }
}
