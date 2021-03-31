package com.example.uberbook.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.uberbook.MainActivity;
import com.example.uberbook.R;

public class ForgottenPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotten_password);

        ((TextView) findViewById(R.id.linkLogin2))
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ForgottenPassword.this, MainActivity.class);
                        startActivity(intent);
                    }
                });
    }
}