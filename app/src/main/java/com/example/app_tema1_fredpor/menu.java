package com.example.app_tema1_fredpor;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.AlarmClock;
import android.service.autofill.UserData;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class menu extends AppCompatActivity {

    private ProgressBar ProgressBar;
    private int progressStatus = 0;
    private Handler handler = new Handler();
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_main);
        ImageView imagenPerfil = findViewById(R.id.imagenPerfil);
        TextView textoBienvenida = findViewById(R.id.textoBienvenida);

        //inicio ini = new inicio();
        //Usuario user = ini.getUsuario();


        String sexo = usuario.getUserSex();
        String sexo = "H";


        //a partir de aqui funciona
        switch (sexo){
            case "H":
                imagenPerfil.setImageResource(R.drawable.avatar_messi);
                break;
            case "M":
                imagenPerfil.setImageResource(R.drawable.mujer);
                break;
            case "O":
                imagenPerfil.setImageResource(R.drawable.otro);
                break;
        }
        //String userName = user.getUserName();
        //textoBienvenida.setText(userName.toUpperCase());





        ImageButton botonDados = findViewById(R.id.botonDados);
        ImageButton botonAlarma = findViewById(R.id.botonAlarma);
        ImageButton botonModdle = findViewById(R.id.botonModdle);
        ImageButton botonMaps = findViewById(R.id.botonMaps);
        ImageButton botonLLamada = findViewById(R.id.botonLLamada);
        ImageButton botonChistes = findViewById(R.id.botonChistes);
        TableLayout tableLayout = findViewById(R.id.tableLayout);
        ProgressBar = findViewById(R.id.ProgressBar);


        new Thread(new Runnable() {
            public void run() {
                while (progressStatus < 100) {
                    progressStatus += 1;
                    handler.post(new Runnable() {
                        public void run() {
                            ProgressBar.setProgress(progressStatus);
                        }
                    });
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                tableLayout.post(new Runnable() {
                    @Override
                    public void run() {
                        ProgressBar.setVisibility(View.INVISIBLE);
                       tableLayout.setVisibility(View.VISIBLE);
                    }
                });
            }
        }).start();


        botonDados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(menu.this, dados.class);
                startActivity(intent);
            }
        });
        botonLLamada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(menu.this, LLamadaActivity.class);
                startActivity(intent);
            }
        });

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
