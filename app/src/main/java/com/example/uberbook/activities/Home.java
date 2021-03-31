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
import com.example.uberbook.utils.Navigation;
import com.nightonke.boommenu.BoomMenuButton;

public class Home extends AppCompatActivity {

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Navigation.init(findViewById(R.id.bmb), this);

        ((ImageView) findViewById(R.id.imageSearch))
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText isbn = (EditText) findViewById(R.id.searchIsbn);
                        String searchIsbn = isbn.getText().toString();
                        Toast.makeText(context, "Recherche du livre : "+searchIsbn, Toast.LENGTH_LONG).show();
                    }
                });
    }
}