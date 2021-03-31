package com.example.uberbook;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uberbook.activities.CreateAccount;
import com.example.uberbook.activities.ForgottenPassword;
import com.example.uberbook.activities.Home;
import com.example.uberbook.schemas.Book;
import com.example.uberbook.schemas.User;
import com.example.uberbook.utils.Api;
import com.example.uberbook.utils.Navigation;
import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.SimpleCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Api api;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        api = new Api();
        context = this;

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Navigation.init(this);

        /*Callback<Book> addBookCallback = new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                assert response.body() != null;
                Toast.makeText(context, "Logged in", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(context, "Echoué", Toast.LENGTH_LONG).show();
            }
        };

        Callback<User> loginCallback = new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                assert response.body() != null;

                Book newBook = new Book();
                newBook.setTitle("La semaine de 4 heures");
                newBook.setAuthor("Timoty FERRIS");
                newBook.setDescription("null");
                newBook.setIsbn(6358);
                api.addBooks(response.body(), newBook, addBookCallback);
                Toast.makeText(context, "Ajout du livre ok", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(context, "Ajout echoué", Toast.LENGTH_LONG).show();
            }
        };

        api.login("test", "test123", loginCallback);*/

        ((TextView) findViewById(R.id.linkCreateAccount))
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, CreateAccount.class);
                        startActivity(intent);
                    }
                });

        ((TextView) findViewById(R.id.linkForgottenPasword))
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, ForgottenPassword.class);
                        startActivity(intent);
                    }
                });

        ((Button) findViewById(R.id.buttonLogin))
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, Home.class);
                        startActivity(intent);
                    }
                });
    }
}