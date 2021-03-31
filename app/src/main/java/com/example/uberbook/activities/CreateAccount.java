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
import com.example.uberbook.schemas.Register;
import com.example.uberbook.schemas.User;
import com.example.uberbook.utils.Api;
import com.example.uberbook.utils.SharedPreference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateAccount extends AppCompatActivity {

    Context context;
    Api api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = this;
        api = new Api();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        Callback<User> callbackRegister = new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.code() == 200) {
                    assert response.body() != null;
                    Toast.makeText(context, "Votre compte a bien été créé !", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(context, "Vueillez remplir tous les champs", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(context, "Un problème serveur à été rencontré", Toast.LENGTH_LONG).show();
            }
        };

        ((ImageView) findViewById(R.id.linkBack))
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });

        ((TextView) findViewById(R.id.linkLogin))
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });

        ((Button) findViewById(R.id.buttonCreateAccount))
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Register register = new Register();
                        register.setEmail(((EditText) findViewById(R.id.createEmail)).getText().toString());
                        register.setPassword(((EditText) findViewById(R.id.createPassword)).getText().toString());
                        register.setUsername(((EditText) findViewById(R.id.createUsername)).getText().toString());
                        api.register(register, callbackRegister);
                    }
                });
    }
}