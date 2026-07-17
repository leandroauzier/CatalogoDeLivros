package com.example.catologodelivros;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.example.catologodelivros.model.Book;
import com.example.catologodelivros.util.SearchUtils;

import org.junit.Test;

public class SearchUtilsTest {

    @Test
    public void findsBookByTitleOrThemeIgnoringCase() {
        Book book = new Book(1, "Android Essencial", "Marina Costa", "Tecnologia",
                "desenvolvimento mobile", "Descricao", 10.0, 1, 0);

        assertTrue(SearchUtils.matches(book, "android"));
        assertTrue(SearchUtils.matches(book, "TECNOLOGIA"));
        assertFalse(SearchUtils.matches(book, "historia"));
    }
}
