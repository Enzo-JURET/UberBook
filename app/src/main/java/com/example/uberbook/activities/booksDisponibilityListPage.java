package com.example.uberbook.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uberbook.R;
import com.example.uberbook.schemas.Book;
import com.example.uberbook.schemas.User;
import com.example.uberbook.utils.Api;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class booksDisponibilityListPage extends AppCompatActivity {

    Api api;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        api = new Api();
        context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_disponibility_list_page);

        Callback<List<Book>> getBooksCallback = new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                assert response.body() != null;
                List<Book> books = response.body();

                /* Affichage */
                LinearLayout bookContainerLayout = ((LinearLayout) findViewById(R.id.linearLayout_BooksContainer));

                LinearLayout layout = new LinearLayout(context);
                layout.setOrientation(LinearLayout.HORIZONTAL);
                for(Book book : books)
                {
                    TextView bookTitle = new TextView(context);
                    bookTitle.setText(book.getTitle());
                    TextView bookDisponibility = new TextView(context);
                    bookDisponibility.setText("Non disponible");
                    if(book.getBorrower() != null)
                    {
                        bookDisponibility.setText("Disponible");
                    }

                    layout.addView(bookTitle);
                    layout.addView(bookDisponibility);
                    bookContainerLayout.addView(layout);
                    layout = null;
                }
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                Toast.makeText(context, "Error on getBooks.",
                        Toast.LENGTH_SHORT).show();
            }
        };
        Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra("User");

        api.getBooks(user,getBooksCallback);
    }
}