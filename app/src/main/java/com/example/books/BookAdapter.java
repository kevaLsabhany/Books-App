package com.example.books;

import android.app.Activity;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class BookAdapter extends ArrayAdapter<Book> {

    public BookAdapter(Activity context, ArrayList<Book> books) {
        super(context, 0, books);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        Book currentBook = getItem(position);

        ImageView imageView = (ImageView) listItemView.findViewById(R.id.book_image);
        if(currentBook.getmImageUrl() != null)
            Picasso.with(getContext()).load(currentBook.getmImageUrl()).into(imageView);

        TextView bookTextView = (TextView) listItemView.findViewById(R.id.book_title);
        bookTextView.setText(currentBook.getmBookName());

        TextView authorTextView = (TextView) listItemView.findViewById(R.id.author_name);
        authorTextView.setText("By "+currentBook.getmAuthorName());

        TextView publisherTextView = (TextView) listItemView.findViewById(R.id.publisher);
        if (currentBook.getmPublisher() == null || currentBook.getmPublisher() == "")
            publisherTextView.setText("Publisher: Unknown");
        else
            publisherTextView.setText("Publisher: "+currentBook.getmPublisher());

        TextView pagesTextView = (TextView) listItemView.findViewById(R.id.pages);
        if (currentBook.getmPages() == 0)
            pagesTextView.setText("Pages: Unknown");
        else
            pagesTextView.setText("Pages: "+currentBook.getmPages());

        return listItemView;
    }
}
