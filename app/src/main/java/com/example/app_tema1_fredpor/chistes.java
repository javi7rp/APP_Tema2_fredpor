package com.example.app_tema1_fredpor;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
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
                String[] chistes = {
                        "¿Cuál es el animal más antiguo?\nLa cebra, porque está en blanco y negro.",
                        "PACO, dime algo que me haga sentir muy perra\nSIT MARIA, SIT",
                        "¿Por qué los programadores prefieren el verano?\nPorque les gusta tener ventanas abiertas.",
                        "¿Cuántos programadores se necesitan para cambiar una bombilla?\nNinguno, es un problema de hardware.",
                        "Papa, papa, porque me echas güacamole por encima?\nque te calles nacho",
                        "¿Cuál es el lenguaje favorito de los programadores?\nEl lenguaje de señas.",
                        "POR EL CULO NO PACO, POR EL CULO NO\nPues ya me diras tu manolo...",
                        "Mire doctor, esque solo oigo por un oido, por el otro no\nNo oye por un oido? a ver diga 100\n50",
                        "Manuel, tu coche es automatico?\nEs manual\nManual, tu coche es automatico?.",
                        "Papa hace 4 meses te robaron la tarjeta y no lo has denunciao\nYa hijo, esque el ladron gasta menos que tu madre.",
                        "¿Cómo se dice 'hola' en lenguaje binario?\n01001000 01101111 01101100 01100001.",
                        "Hola guapa, si adivinas un numero del 1 al 5 nos tomamos algo juntos\nel 3\nPOR UNO TIO, JODER, POR UNO",
                        "¿Cómo arreglan los programadores la lámpara del techo?\nLa apagan y la vuelven a encender.",
                        "Van dos ciegos y uno le dice a otro\nOjala lloviera\nOjala yo tambien...",
                        "En que se parecen una bala y una cueva?\nEn que en la cueva hay humedad\ny la bala humedad u no meda",
                        "Mira una serpiente reptando por el pasillo!!!\n5-2=3, 9-5=4...",
                        "Entra un huerfano a una pizzeria\nBuenas, quisiera dos familiares...",

                };

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
