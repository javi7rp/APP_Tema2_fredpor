package com.example.app_tema1_fredpor;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;
import java.util.Random;

public class chistes extends AppCompatActivity implements TextToSpeech.OnInitListener {
    private TextToSpeech textToSpeech;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chistes);

        textToSpeech = new TextToSpeech(this, this);

        Button volverMenu = findViewById(R.id.volverMenu);
        Button generarChiste = findViewById(R.id.generarChiste);
        TextView texto_chiste = findViewById(R.id.text_chistes);
        Spinner spinner = findViewById(R.id.elegirTema);

        volverMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(chistes.this, menu.class);
                startActivity(intent);
            }
        });

        generarChiste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temaSeleccionado = spinner.getSelectedItem().toString();
                String[] chistes = {};
                switch (temaSeleccionado){
                    case "JAIMITO":
                        chistes = new String[]{
                                "Jaimito, te has copiado del examen de Pedro?\nNo maestra\nY entonces porque en la pregunta 4, donde Pedro contesta 'No lo se' tu pones 'Yo tampoco'?",
                                "Jaimito, cual es la definicio de telepatía?\nUna televisión pa la hermana de mi madre",
                                "Jaimito, la M con la A?\nMA\nAhora repetido\nMAMA\nahora con tilde\nMAtilde",
                                "Jaimito, dime 5 animales africanos\n 5 elefantes profe",
                                "Profe, como se dice nariz en inglés?\nnose jaimito\njoe pues vaya profesora...",
                                "Papá, donde esta Rusia?\nNo se preguntale a tu madre",
                                "Jaimito, cuantas anclas tiene un barco?\n once profe\n porque once?\n porque siempre dicen eleven anclas...",
                                "Jaimito, que es una orilla?\n Sesenta minutillos"
                        };

                        break;
                    case "INFORMATICOS":
                        chistes = new String[] {
                                "¿Me puede decir la contraseña de wifi de su bar, por favor?\nTómate un agua por lo menos, ¿no?\n¿Todo junto?",
                                "Mi mujer me dijo que necesitaba más espacio.\n¿Y qué hiciste?\nLe regalé un disco duro de 2Tb.",
                                "¿Por qué los programadores prefieren el verano?\nPorque les gusta tener ventanas abiertas.",
                                "¿Cuántos programadores se necesitan para cambiar una bombilla?\nNinguno, es un problema de hardware.",
                                "Hola, llamo porque no me funciona el módem de internet.\n¿Qué luces tiene encendidas?\nLa del pasillo y la del salón\nVale, ahora mismo le envío a un informático.",
                                "¿Cuál es el lenguaje favorito de los programadores?\nEl lenguaje de señas.",
                                "¿Cómo se dice 'hola' en lenguaje binario?\n01001000 01101111 01101100 01100001.",
                                "¿Cómo arreglan los programadores la lámpara del techo?\nLa apagan y la vuelven a encender.",
                                "Google es como un cuñado: no te deja acabar la frase y ya te está dando sugerencias",
                                "Me pone que mi contraseña es incorrecta, pero escribo 'incorrecta' y no me deja entrar"
                        };
                        break;
                    case "DOCTOR":
                        chistes = new String[]{
                                "Mire doctor, esque solo oigo por un oido, por el otro no\nNo oye por un oido? a ver diga 100\n50",
                                "Doctor, doctor, tengo paperas\n Pues toma 2euros y ya tienes paplatanos",




                        };
                        break;
                    case "RANDOM":
                        chistes = new String[]{
                                "Papá, papá, porque me echas güacamole por encima?\nque te calles nacho",
                                "Manuel, tu coche es automatico?\nEs manual\nManual, tu coche es automatico?.",
                                "Papa hace 4 meses te robaron la tarjeta y no lo has denunciao\nYa hijo, esque el ladron gasta menos que tu madre.",
                                "Hola guapa, si adivinas un numero del 1 al 5 nos tomamos algo juntos\nel 3\nPOR UNO TIO, JODER, POR UNO",
                                "Van dos ciegos y uno le dice a otro\nOjala lloviera\nOjala yo tambien...",
                                "En que se parecen una bala y una cueva?\nEn que en la cueva hay humedad\ny la bala humedad u no meda",
                                "Mira una serpiente reptando por el pasillo!!!\n5-2=3, 9-5=4...",
                                "Entra un huerfano a una pizzeria\nBuenas, quisiera dos familiares...",
                        };
                        break;
                }
                String chisteElegido = elegirChiste(chistes);

                texto_chiste.setText(chisteElegido);

                speakTexto(chisteElegido);


            }
        });

    }

    private String elegirChiste(String[] chistes) {
        Random random = new Random();
        int i = random.nextInt(chistes.length);
        return chistes[i];
    };

        public void onInit(int status) {
            if (status == TextToSpeech.SUCCESS) {
                int result = textToSpeech.setLanguage(new Locale("es", "ES"));

                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                }
            } else {
            }
        }

        private void speakTexto(String texto) {
            if (textToSpeech != null) {
                Bundle params = new Bundle();
                params.putString(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "ID");
                textToSpeech.speak(texto, TextToSpeech.QUEUE_FLUSH, params, "ID");
            }
        }


    protected void onDestroy() {
        super.onDestroy();
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
    }
}
