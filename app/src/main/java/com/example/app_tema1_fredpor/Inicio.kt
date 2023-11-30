package com.example.app_tema1_fredpor

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Inicio : AppCompatActivity() {
    private var checkRadio = false
    private var checkCheckbox = false
    private lateinit var textUser: EditText
    private lateinit var textPass: EditText
    private var userSex = ""

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.inicio)

        val botonStart = findViewById<Button>(R.id.botonEntrar)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val checkBox = findViewById<CheckBox>(R.id.check_condiciones)

        textUser = findViewById(R.id.textUser)
        textPass = findViewById(R.id.textPassword)

        radioGroup.setOnCheckedChangeListener { _, i ->
            checkRadio = i != -1
        }

        checkBox.setOnCheckedChangeListener { _, b ->
            checkCheckbox = b
        }

        botonStart.setOnClickListener {

            var userName = textUser.text.toString()
            var userPass = textPass.text.toString()
            if (checkCheckbox && checkRadio) {
                if (!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(userPass)) {
                    val radioCheckedId = radioGroup.checkedRadioButtonId
                    when (radioCheckedId) {
                        R.id.btr_hombre -> {
                            userSex = "H"
                            Log.e("TAG", "Este es un mensaje de error")
                        }
                        R.id.btr_mujer -> {
                            userSex = "M"
                            Log.e("TAG", "Este es un mensaje de error")
                        }
                        R.id.btr_otro -> {
                            userSex = "O"
                            Log.e("TAG", "Este es un mensaje de error")
                        }
                        else -> {
                            Toast.makeText(applicationContext, "Error, los id han cambiado", Toast.LENGTH_SHORT).show()
                        }
                    }
                    ListarUser.annadirUsuario(Usuario(userName,userPass,userSex))
                    val intent = Intent(this@Inicio, Menu::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(applicationContext, "DEBES INTRODUCIR USUARIO Y CONTRASEÑA", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(applicationContext, "DEBES SELECCIONAR EL SEXO Y ACEPTAR LAS CONDICICONES", Toast.LENGTH_SHORT).show()
            }
        }
    }

}