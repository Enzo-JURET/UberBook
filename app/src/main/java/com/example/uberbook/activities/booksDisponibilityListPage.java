package com.example.uberbook.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Space;
import android.widget.Switch;
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

    // Liste des livres. Contient toute les données relative à chaque livre
    List<Book> bookList;

    // Filtre switch / Filtre texte
    String filterType = "title";
    String textFilter = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        api = new Api();
        context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_disponibility_list_page);

        // Gestion du changement d'affichage du filtre switch titre/auteur
        Switch filterSwitch = ((Switch) findViewById(R.id.switch_titleAuthor));
        filterSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // do something, the isChecked will be
                // true if the switch is in the On position
                if(isChecked)
                {
                    buttonView.setText("Auteur");
                    filterType = "author";
                    displayBooksByFilter();
                }
                else {
                    buttonView.setText("Titre ");
                    filterType = "title";
                    displayBooksByFilter();
                }
            }
        });

        // Gestion du filtre textuel
        EditText yourEditText = (EditText) findViewById(R.id.editText_BookSearchBar);

        yourEditText.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                textFilter = s.toString();
                displayBooksByFilter();
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });



        getBookList();

    }

    void displayBooksByFilter()
    {
        Log.d("texte",textFilter);
        LinearLayout bookContainerLayout = ((LinearLayout) findViewById(R.id.linearLayout_BooksContainer));
        bookContainerLayout.removeAllViews();

        for(Book book : bookList)
        {
            if((filterType == "title" || filterType == "author") && (book.getAuthor().contains(textFilter) || book.getTitle().contains(textFilter)))
            {
                LinearLayout layout  = new LinearLayout(context);
                layout.setOrientation(LinearLayout.HORIZONTAL);

                LinearLayout.LayoutParams layoutParamsDisponibility = new LinearLayout.LayoutParams(140,398);
                layoutParamsDisponibility.setMargins(100,0,0,0);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    layoutParamsDisponibility.setMarginStart(400);
                }

                TextView bookTitleAuthor = new TextView(context);

                if(filterType == "author")
                {
                    bookTitleAuthor.setText(book.getAuthor());
                }
                else {
                    bookTitleAuthor.setText(book.getTitle());
                }

                TextView bookDisponibility = new TextView(context);
                bookDisponibility.setText("Non disponible");
                if(book.getBorrower() != null)
                {
                    bookDisponibility.setText("Disponible");
                }

                layout.addView(bookTitleAuthor);
                layout.addView(bookDisponibility,layoutParamsDisponibility);

                bookContainerLayout.addView(layout);
            }

        }
    }

    void getBookList()
    {
        Callback<List<Book>> getBooksCallback = new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                assert response.body() != null;
                bookList = response.body();
                displayBooksByFilter();
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
            }
        };

        Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra("User");
        Log.d("User",user.getUser().getEmail());
        api.getBooks(user,getBooksCallback);
    }

}