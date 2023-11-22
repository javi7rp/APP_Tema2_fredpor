package com.example.app_tema1_fredpor;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.AlarmClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class dados extends AppCompatActivity{

    private int sumaDadosIzq;
    private int sumaDadosDer;
    private ImageView solizq;
    private ImageView solder;
    private ImageView dado1izq;
    private ImageView dado2izq;
    private ImageView dado3izq;
    private ImageView dado1der;
    private ImageView dado2der;
    private ImageView dado3der;
    final Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dados);

        dado1izq = findViewById(R.id.dado1Izq);
        dado2izq = findViewById(R.id.dado2Izq);
        dado3izq = findViewById(R.id.dado3Izq);
        dado1der = findViewById(R.id.dado1Der);
        dado2der = findViewById(R.id.dado2Der);
        dado3der = findViewById(R.id.dado3Der);
        solizq = findViewById(R.id.solucionIzq);
        solder = findViewById(R.id.solucionDer);
        Button volverMenu = findViewById(R.id.volverMenu);
        Button rollButton = findViewById(R.id.rollButton);

        // Establecer un listener para el botón de lanzamiento
        rollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sumaDadosDer = 0;
                sumaDadosIzq = 0;
                dado1izq.setImageResource(R.drawable.dado1inicio);
                dado2izq.setImageResource(R.drawable.dado1inicio);
                dado3izq.setImageResource(R.drawable.dado1inicio);
                dado1der.setImageResource(R.drawable.dado2inicio);
                dado2der.setImageResource(R.drawable.dado2inicio);
                dado3der.setImageResource(R.drawable.dado2inicio);
                solizq.setVisibility(View.INVISIBLE);
                solder.setVisibility(View.INVISIBLE);

                handler.postDelayed(new Runnable(){
                    @Override
                    public void run() {
                        lanzarDadoIzq(dado1izq);
                    }
                }, 1000);
                handler.postDelayed(new Runnable(){
                    @Override
                    public void run() {
                        lanzarDadoDer(dado1der);
                    }
                }, 2000);
                handler.postDelayed(new Runnable(){
                    @Override
                    public void run() {
                        lanzarDadoIzq(dado2izq);
                    }
                }, 3000);
                handler.postDelayed(new Runnable(){
                    @Override
                    public void run() {
                        lanzarDadoDer(dado2der);
                    }
                }, 4000);
                handler.postDelayed(new Runnable(){
                    @Override
                    public void run() {
                        lanzarDadoIzq(dado3izq);
                    }
                }, 5000);
                handler.postDelayed(new Runnable(){
                    @Override
                    public void run() {
                        lanzarDadoDer(dado3der);
                    }
                }, 6000);

                handler.postDelayed(new Runnable(){
                    @Override
                    public void run() {
                        if (sumaDadosIzq > sumaDadosDer){
                            solizq.setImageResource(R.drawable.trofeo);
                            solder.setImageResource(R.drawable.perdedor);
                        } else if (sumaDadosDer > sumaDadosIzq) {
                            solder.setImageResource(R.drawable.trofeo);
                            solizq.setImageResource(R.drawable.perdedor);
                        } else if (sumaDadosDer == sumaDadosIzq) {
                            solizq.setImageResource(R.drawable.igual);
                            solder.setImageResource(R.drawable.igual);
                        }
                        solizq.setVisibility(View.VISIBLE);
                        solder.setVisibility(View.VISIBLE);
                    }
                }, 7000);
            }
        });

        volverMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dados.this, menu.class);
                startActivity(intent);
            }
        });
    }


    private void lanzarDadoIzq(ImageView diceImageView) {
        // Generar un número aleatorio entre 1 y 6
        Random random = new Random();
        int resultadoDado = random.nextInt(6) + 1;
        sumaDadosIzq = sumaDadosIzq + resultadoDado;

        // Obtener el recurso de la imagen correspondiente al resultado del dado
        int imagenId = obtenerImagenIzq(resultadoDado);

        // Establecer la imagen en el ImageView
        diceImageView.setImageResource(imagenId);

    }
    private void lanzarDadoDer(ImageView diceImageView) {
        // Generar un número aleatorio entre 1 y 6
        Random random = new Random();
        int resultadoDado = random.nextInt(6) + 1;
        sumaDadosDer = sumaDadosDer + resultadoDado;

        // Obtener el recurso de la imagen correspondiente al resultado del dado
        int imagenId = obtenerImagenDer(resultadoDado);

        // Establecer la imagen en el ImageView
        diceImageView.setImageResource(imagenId);

    }

    private int obtenerImagenIzq(int numeroDado) {
        // Determinar el recurso de la imagen según el número del dado
        switch (numeroDado) {
            case 1:
                return R.drawable.dado1cara1;
            case 2:
                return R.drawable.dado1cara2;
            case 3:
                return R.drawable.dado1cara3;
            case 4:
                return R.drawable.dado1cara4;
            case 5:
                return R.drawable.dado1cara5;
            case 6:
                return R.drawable.dado1cara6;
            default:
                return R.drawable.dado1cara1; // En caso de un valor no esperado, mostrar la cara del dado 1
        }
    }

    private int obtenerImagenDer(int numeroDado) {
        // Determinar el recurso de la imagen según el número del dado
        switch (numeroDado) {
            case 1:
                return R.drawable.dado2cara1;
            case 2:
                return R.drawable.dado2cara2;
            case 3:
                return R.drawable.dado2cara3;
            case 4:
                return R.drawable.dado2cara4;
            case 5:
                return R.drawable.dado2cara5;
            case 6:
                return R.drawable.dado2cara6;
            default:
                return R.drawable.dado2cara1; // En caso de un valor no esperado, mostrar la cara del dado 1
        }
    }



}
