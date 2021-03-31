package com.example.uberbook.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uberbook.MainActivity;
import com.example.uberbook.R;
import com.example.uberbook.schemas.Book;
import com.example.uberbook.utils.Api;
import com.example.uberbook.utils.Navigation;
import com.example.uberbook.utils.SharedPreference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddBook extends AppCompatActivity {

    Api api;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        api = new Api();
        context = this;

        if (SharedPreference.getUser().getUser().getRole().getId() != 3) {
            finish();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        Navigation.init(findViewById(R.id.bmb), this);

        Callback<Book> callbackAddBook = new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                if (response.code() == 200) {
                    assert response.body() != null;
                    Toast.makeText(context, "Livre ajouté avec succès", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(context, "Un problème avec vos identifiants s'est produit veuillez réessayer", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                Toast.makeText(context, "Un problème serveur est survenu", Toast.LENGTH_LONG).show();
            }
        };

        ((Button) findViewById(R.id.buttonAddBook)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
                book.setTitle(((EditText) findViewById(R.id.addBookTitle)).getText().toString());
                book.setDescription(((EditText) findViewById(R.id.addBookDescription)).getText().toString());
                book.setAuthor(((EditText) findViewById(R.id.addBookAuthor)).getText().toString());
                book.setIsbn(Integer.parseInt(((EditText) findViewById(R.id.addBookIsbn)).getText().toString()));

                api.addBooks(SharedPreference.getUser(), book, callbackAddBook);
            }
        });

        ((ImageView) findViewById(R.id.buttonCancel))
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
    }
}