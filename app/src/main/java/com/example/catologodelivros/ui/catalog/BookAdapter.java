package com.example.catologodelivros.ui.catalog;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.catologodelivros.R;
import com.example.catologodelivros.model.Book;
import com.example.catologodelivros.util.FormatUtils;

import java.util.ArrayList;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    public interface OnBookClickListener {
        void onBookClick(Book book);
    }

    private final List<Book> books = new ArrayList<>();
    private final OnBookClickListener listener;

    public BookAdapter(OnBookClickListener listener) {
        this.listener = listener;
    }

    public void submitList(List<Book> newBooks) {
        books.clear();
        books.addAll(newBooks);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book book = books.get(position);
        holder.bind(book, listener);
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    static class BookViewHolder extends RecyclerView.ViewHolder {
        private final ImageView coverImage;
        private final TextView titleText;
        private final TextView authorText;
        private final TextView categoryText;
        private final TextView priceText;
        private final TextView stockText;
        private final TextView availabilityText;

        BookViewHolder(@NonNull View itemView) {
            super(itemView);
            coverImage = itemView.findViewById(R.id.bookCoverImage);
            titleText = itemView.findViewById(R.id.bookTitleText);
            authorText = itemView.findViewById(R.id.bookAuthorText);
            categoryText = itemView.findViewById(R.id.bookCategoryText);
            priceText = itemView.findViewById(R.id.bookPriceText);
            stockText = itemView.findViewById(R.id.bookStockText);
            availabilityText = itemView.findViewById(R.id.bookAvailabilityText);
        }

        void bind(Book book, OnBookClickListener listener) {
            coverImage.setImageResource(book.getImageResId());
            titleText.setText(book.getTitle());
            authorText.setText(itemView.getContext().getString(R.string.author_label, book.getAuthor()));
            categoryText.setText(itemView.getContext().getString(R.string.category_label, book.getCategory()));
            priceText.setText(FormatUtils.formatCurrency(book.getPrice()));
            stockText.setText(itemView.getContext().getString(R.string.stock_label, book.getStock()));
            availabilityText.setText(book.isAvailable()
                    ? R.string.available
                    : R.string.unavailable);
            availabilityText.setBackgroundResource(book.isAvailable()
                    ? R.drawable.bg_status_available
                    : R.drawable.bg_status_unavailable);
            itemView.setOnClickListener(v -> listener.onBookClick(book));
        }
    }
}
