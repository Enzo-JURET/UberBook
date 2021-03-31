package com.example.uberbook;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uberbook.activities.CreateAccount;
import com.example.uberbook.activities.ForgottenPassword;
import com.example.uberbook.activities.Home;
import com.example.uberbook.schemas.User;
import com.example.uberbook.utils.Api;
import com.example.uberbook.utils.App;
import com.example.uberbook.utils.SharedPreference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        if (SharedPreference.isLogged()) {
            Intent intent = new Intent(this, Home.class);
            intent.putExtra("user", SharedPreference.getUser());
            startActivity(intent);
        } else {
            Callback<User> loginCallback = new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.code() == 200) {
                        assert response.body() != null;
                        Intent intent = new Intent(MainActivity.this, Home.class);
                        SharedPreference.buildSharedPreference(response.body());
                        intent.putExtra("user", response.body());
                        startActivity(intent);
                    } else {
                        Toast.makeText(context, "Mauvais identifiant ou mot de passe", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    t.printStackTrace();
                    Toast.makeText(context, "Un problème serveur à été rencontré", Toast.LENGTH_LONG).show();
                }
            };

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
                            api.login(((EditText) findViewById(R.id.connectEmail)).getText().toString(), ((EditText) findViewById(R.id.connectPassword)).getText().toString(), loginCallback);
                        }
                    });
        }
    }
}