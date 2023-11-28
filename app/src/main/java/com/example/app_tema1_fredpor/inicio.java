package com.example.app_tema1_fredpor;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class inicio extends AppCompatActivity {
    private boolean checkRadio = false;
    private boolean checkCheckbox = false;
    public Usuario usuario;

    @Override
    protected void  onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio);


        Button botonStart = findViewById(R.id.botonEntrar);
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        CheckBox checkBox = findViewById(R.id.check_condiciones);

        EditText textUser = findViewById(R.id.textUser);
        EditText textPass = findViewById(R.id.textPassword);

        String userName = textUser.getText().toString();
        String userPass = textPass.getText().toString();




        final boolean[] checkCheckBox = {false};

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                checkRadio = i != -1;
            }
        });

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                checkCheckbox = b;
            }
        });

        botonStart.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                if (checkCheckbox && checkRadio){
                    int radioCheckedId = radioGroup.getCheckedRadioButtonId();
                    switch (radioCheckedId){
                        case 2131230828: //id del boton1
                            usuario = new Usuario(userName, userPass, "H");
                            Toast.makeText(getApplicationContext(), usuario.toString(), Toast.LENGTH_SHORT).show();
                            break;
                        case 2131230829://id del boton2
                            usuario = new Usuario(userName, userPass, "M");
                            Toast.makeText(getApplicationContext(), usuario.toString(), Toast.LENGTH_SHORT).show();
                            break;
                        case 2131230830://id del boton3
                            usuario = new Usuario(userName, userPass, "O");
                            Toast.makeText(getApplicationContext(), usuario.toString(), Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            Toast.makeText(getApplicationContext(), radioGroup.getCheckedRadioButtonId(), Toast.LENGTH_SHORT).show();
                    }

                    //Intent intent = new Intent(inicio.this, menu.class);
                    //startActivity(intent);

                }else{
                    Toast.makeText(getApplicationContext(), "DEBES SELECCIONAR EL SEXO Y ACEPTAR LAS CONDICICONES", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public Usuario obtenerUser(){
        return usuario;
    }
}
