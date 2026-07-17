package com.example.catologodelivros.cart;

import com.example.catologodelivros.model.Book;
import com.example.catologodelivros.model.CartItem;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CartState {

    private final LinkedHashMap<Integer, Integer> quantities = new LinkedHashMap<>();

    public boolean addBook(Book book) {
        return setQuantity(book, getQuantity(book.getId()) + 1);
    }

    public boolean increase(Book book) {
        return addBook(book);
    }

    public void decrease(int bookId) {
        int current = getQuantity(bookId);
        if (current <= 1) {
            quantities.remove(bookId);
        } else {
            quantities.put(bookId, current - 1);
        }
    }

    public boolean setQuantity(Book book, int quantity) {
        if (quantity <= 0) {
            quantities.remove(book.getId());
            return true;
        }
        if (quantity > book.getStock()) {
            return false;
        }
        quantities.put(book.getId(), quantity);
        return true;
    }

    public void remove(int bookId) {
        quantities.remove(bookId);
    }

    public void clear() {
        quantities.clear();
    }

    public int getQuantity(int bookId) {
        Integer value = quantities.get(bookId);
        return value == null ? 0 : value;
    }

    public int getTotalItems() {
        int total = 0;
        for (Integer quantity : quantities.values()) {
            total += quantity;
        }
        return total;
    }

    public double getTotalValue(List<Book> books) {
        double total = 0;
        for (CartItem item : toCartItems(books)) {
            total += item.getSubtotal();
        }
        return total;
    }

    public List<CartItem> toCartItems(List<Book> books) {
        List<CartItem> items = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : quantities.entrySet()) {
            for (Book book : books) {
                if (book.getId() == entry.getKey()) {
                    items.add(new CartItem(book, entry.getValue()));
                    break;
                }
            }
        }
        return items;
    }

    public Map<Integer, Integer> snapshot() {
        return new LinkedHashMap<>(quantities);
    }
}
