package com.example.catologodelivros.ui.detail;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.catologodelivros.R;
import com.example.catologodelivros.cart.CartManager;
import com.example.catologodelivros.data.BookRepository;
import com.example.catologodelivros.model.Book;
import com.example.catologodelivros.ui.BaseActivity;
import com.example.catologodelivros.util.FormatUtils;

public class BookDetailActivity extends BaseActivity {

    public static final String EXTRA_BOOK_ID = "extra_book_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        setupToolbar(R.id.detailToolbar, true, R.string.book_detail_title);

        Book book = BookRepository.getBookById(getIntent().getIntExtra(EXTRA_BOOK_ID, -1));
        if (book == null) {
            finish();
            return;
        }

        ((ImageView) findViewById(R.id.detailCoverImage)).setImageResource(book.getImageResId());
        ((TextView) findViewById(R.id.detailTitleText)).setText(book.getTitle());
        ((TextView) findViewById(R.id.detailAuthorText)).setText(getString(R.string.author_label, book.getAuthor()));
        ((TextView) findViewById(R.id.detailCategoryText)).setText(getString(R.string.category_label, book.getCategory()));
        ((TextView) findViewById(R.id.detailSubjectText)).setText(getString(R.string.subject_label, book.getSubject()));
        ((TextView) findViewById(R.id.detailDescriptionText)).setText(book.getDescription());
        ((TextView) findViewById(R.id.detailPriceText)).setText(FormatUtils.formatCurrency(book.getPrice()));
        ((TextView) findViewById(R.id.detailStockText)).setText(getString(R.string.stock_label, book.getStock()));

        TextView availabilityText = findViewById(R.id.detailAvailabilityText);
        availabilityText.setText(book.isAvailable() ? R.string.available : R.string.unavailable);
        availabilityText.setBackgroundResource(book.isAvailable()
                ? R.drawable.bg_status_available
                : R.drawable.bg_status_unavailable);

        Button addToCartButton = findViewById(R.id.addToCartButton);
        addToCartButton.setEnabled(book.isAvailable());
        addToCartButton.setOnClickListener(v -> {
            boolean added = CartManager.getInstance(this).addBook(book);
            if (added) {
                Toast.makeText(this, R.string.book_added_to_cart, Toast.LENGTH_SHORT).show();
                invalidateOptionsMenu();
            } else {
                Toast.makeText(this, R.string.stock_limit_message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
