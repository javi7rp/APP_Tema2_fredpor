package com.example.app_tema1_fredpor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class menu extends AppCompatActivity {

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_main);

        ImageButton botonDados = findViewById(R.id.botonDados);
        botonDados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(menu.this, dados.class);
                startActivity(intent);
            }
        });
       /* botonRealizarLLamada = findViewById(R.id.botonRealizarLLamada);

        botonRealizarLLamada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                realizarLLamada();
            }
        });

        */
    }
}
