package com.example.app_tema1_fredpor;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class inicio extends AppCompatActivity {

    @Override
    protected void  onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio);


        Button botonStart = findViewById(R.id.botonEntrar);

        botonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(inicio.this, menu.class);
                startActivity(intent);
            }
        });
    }
}
