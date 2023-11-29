package com.example.app_tema1_fredpor;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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
    private static Usuario usuario;
    private static String userName;
    private static String userPass;
    private static String userSex = "";

    @Override
    protected void  onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio);


        Button botonStart = findViewById(R.id.botonEntrar);
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        CheckBox checkBox = findViewById(R.id.check_condiciones);

        EditText textUser = findViewById(R.id.textUser);
        EditText textPass = findViewById(R.id.textPassword);





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
                userName = textUser.getText().toString();
                userPass = textPass.getText().toString();
                if (checkCheckbox && checkRadio){
                    if(!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(userPass)){
                        int radioCheckedId = radioGroup.getCheckedRadioButtonId();
                        switch (radioCheckedId){
                            case 2131230828: //id del boton1
                                userSex = "H";
                                break;
                            case 2131230829://id del boton2
                                userSex = "M";

                                break;
                            case 2131230830://id del boton3
                                userSex = "O";
                                break;
                            default:
                                Toast.makeText(getApplicationContext(), radioGroup.getCheckedRadioButtonId(), Toast.LENGTH_SHORT).show();
                        }
                        Intent intent = new Intent(inicio.this, menu.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(getApplicationContext(), "DEBES INTRODUCIR USUARIO Y CONTRASEÑA", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(getApplicationContext(), "DEBES SELECCIONAR EL SEXO Y ACEPTAR LAS CONDICICONES", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public static Usuario getUsuario(){
        return usuario = new Usuario(userName, userPass, userSex);
    }


}
