package com.example.catologodelivros.util;

import com.example.catologodelivros.model.Book;

import java.text.Normalizer;
import java.util.Locale;

public final class SearchUtils {

    private SearchUtils() {
    }

    public static boolean matches(Book book, String query) {
        String normalizedQuery = normalize(query);
        return normalize(book.getTitle()).contains(normalizedQuery)
                || normalize(book.getAuthor()).contains(normalizedQuery)
                || normalize(book.getCategory()).contains(normalizedQuery)
                || normalize(book.getSubject()).contains(normalizedQuery);
    }

    public static String normalize(String text) {
        String source = text == null ? "" : text;
        String normalized = Normalizer.normalize(source, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        return normalized.toLowerCase(Locale.ROOT).trim();
    }
}
