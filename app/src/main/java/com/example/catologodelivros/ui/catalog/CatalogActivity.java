package com.example.catologodelivros.ui.catalog;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.catologodelivros.R;
import com.example.catologodelivros.data.BookRepository;
import com.example.catologodelivros.model.Book;
import com.example.catologodelivros.ui.BaseActivity;
import com.example.catologodelivros.ui.detail.BookDetailActivity;
import com.example.catologodelivros.util.SessionManager;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class CatalogActivity extends BaseActivity {

    private BookAdapter adapter;
    private TextView emptyStateText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);
        setupToolbar(R.id.catalogToolbar, false, R.string.catalog_title);

        TextView greetingText = findViewById(R.id.greetingText);
        greetingText.setText(getString(R.string.greeting_user, new SessionManager(this).getUsername()));

        emptyStateText = findViewById(R.id.emptyStateText);
        RecyclerView recyclerView = findViewById(R.id.booksRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BookAdapter(this::openDetails);
        recyclerView.setAdapter(adapter);

        TextInputEditText searchEditText = findViewById(R.id.searchEditText);
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterBooks(s == null ? "" : s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        filterBooks("");
    }

    private void filterBooks(String query) {
        List<Book> filteredBooks = BookRepository.searchBooks(query);
        adapter.submitList(filteredBooks);
        emptyStateText.setVisibility(filteredBooks.isEmpty() ? View.VISIBLE : View.GONE);
    }

    private void openDetails(Book book) {
        Intent intent = new Intent(this, BookDetailActivity.class);
        intent.putExtra(BookDetailActivity.EXTRA_BOOK_ID, book.getId());
        startActivity(intent);
    }
}
