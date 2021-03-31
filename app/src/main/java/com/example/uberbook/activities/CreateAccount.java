package com.example.uberbook.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uberbook.MainActivity;
import com.example.uberbook.R;

public class CreateAccount extends AppCompatActivity {

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = this;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

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
                        Intent intent = new Intent(CreateAccount.this, MainActivity.class);
                        Toast.makeText(context, "Votre compte a bien été créé !", Toast.LENGTH_LONG).show();
                        startActivity(intent);
                    }
                });
    }
}