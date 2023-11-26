package com.example.app_tema1_fredpor;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class menu extends AppCompatActivity {

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        /*
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_main, null);
        EditText text_user = view.findViewById(R.id.textUser);
        String user = text_user.getText().toString();
        Toast.makeText(getApplicationContext(), "Este es un mensaje por pantalla" + user, Toast.LENGTH_SHORT).show();
        String txtBienvenida = "BIENVENIDO A LA PAGINA PRINCIPAL ";
        String txtFinal = txtBienvenida + user;
        TextView textView = findViewById(R.id.textoBienvenida);
        textView.setText(user);

         */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_main);

        ImageButton botonDados = findViewById(R.id.botonDados);
        ImageButton botonAlarma = findViewById(R.id.botonAlarma);
        ImageButton botonModdle = findViewById(R.id.botonModdle);
        ImageButton botonMaps = findViewById(R.id.botonMaps);
        ImageButton botonLLamada = findViewById(R.id.botonLLamada);
        ImageButton botonChistes = findViewById(R.id.botonChistes);
        botonDados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(menu.this, dados.class);
                startActivity(intent);
            }
        });
/*
        botonLLamada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "BOTON DE LLAMADA PULSADO", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(menu.this, LLamadaActivity.class);
                startActivity(intent);
            }
        });*/

        botonChistes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(menu.this, chistes.class);
                startActivity(intent);
            }
        });


        botonAlarma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int horaActual = cal.get(Calendar.HOUR_OF_DAY);
                int minActual = cal.get(Calendar.MINUTE);

                minActual = minActual + 2;
                if (minActual >= 60){
                    minActual = minActual - 60;
                    horaActual++;
                    if (horaActual >= 24){
                        horaActual = horaActual -24;
                    }
                }
                Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
                intent.putExtra(AlarmClock.EXTRA_MESSAGE, "ALARMA DE PRUEBA DE 2 MIN");
                intent.putExtra(AlarmClock.EXTRA_DAYS, Calendar.SUNDAY);
                intent.putExtra(AlarmClock.EXTRA_HOUR, horaActual);
                intent.putExtra(AlarmClock.EXTRA_MINUTES, minActual);

                try {
                    startActivity(intent);
                }catch (ActivityNotFoundException e){
                    e.printStackTrace();
                    Log.d("Error ", "no se ha podido crear la alarma");
                }
            }
        });

        botonModdle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url =  "https://educacionadistancia.juntadeandalucia.es/centros/jaen/";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });

        botonMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double latitud = 40.4165;
                double longitud = -3.70256;
                String label = "Madrid";

                Uri IntentUri = Uri.parse("geo: " + latitud + "," + longitud + "?q=" + latitud + "," + longitud + "(" + label + ")");
                Intent map = new Intent(Intent.ACTION_VIEW, IntentUri );
                map.setPackage("com.google.android.apps.maps");
                startActivity(map);
            }
        });
    }
}
