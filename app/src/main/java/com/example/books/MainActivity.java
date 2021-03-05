package com.example.books;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView booksListView;
    private EditText editText;
    private ImageView searchImageView;
    private BookAdapter adapter;
    private TextView emptyView;
    private RelativeLayout relativeLayout;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.GONE);
        relativeLayout = (RelativeLayout) findViewById(R.id.explore);
        relativeLayout.setVisibility(View.VISIBLE);
        emptyView = (TextView) findViewById(R.id.empty_view);
        emptyView.setVisibility(View.GONE);
        booksListView = (ListView) findViewById(R.id.list);
        adapter = new BookAdapter(this, new ArrayList<Book>());
        booksListView.setAdapter(adapter);
        booksListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Book b = (Book) parent.getItemAtPosition(position);
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(b.getmSelfUrl()));
                        startActivity(i);
                    }
                }
        );
        searchImageView = (ImageView) findViewById(R.id.search_icon);
        searchImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relativeLayout.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
                editText = (EditText) findViewById(R.id.book_name);
                String name = editText.getText().toString().replace(" ", "+");

                new BooksAsyncTask().execute("https://www.googleapis.com/books/v1/volumes?q="+name);
            }
        });
    }

    private class BooksAsyncTask extends AsyncTask<String, Void, ArrayList<Book>> {
        @Override
        protected void onPostExecute(ArrayList<Book> books) {
            adapter.clear();
            progressBar.setVisibility(View.GONE);
            if(!books.isEmpty()) {
                emptyView.setVisibility(View.GONE);
                adapter.addAll(books);
            } else {
                emptyView.setVisibility(View.VISIBLE);
            }
        }

        @Override
        protected ArrayList<Book> doInBackground(String... strings) {
            return QueryUtils.extractDataFromApi(strings[0]);
        }
    }
}