package com.example.catologodelivros;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.example.catologodelivros.cart.CartState;
import com.example.catologodelivros.model.Book;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class CartStateTest {

    private CartState cartState;
    private Book book;

    @Before
    public void setUp() {
        cartState = new CartState();
        book = new Book(1, "Teste", "Autor", "Tema", "Assunto", "Descricao", 20.0, 2, 0);
    }

    @Test
    public void calculatesTotalCorrectly() {
        cartState.addBook(book);
        cartState.addBook(book);

        assertEquals(40.0, cartState.getTotalValue(Arrays.asList(book)), 0.001);
    }

    @Test
    public void respectsStockLimit() {
        assertTrue(cartState.addBook(book));
        assertTrue(cartState.addBook(book));
        assertFalse(cartState.addBook(book));
        assertEquals(2, cartState.getQuantity(book.getId()));
    }
}
