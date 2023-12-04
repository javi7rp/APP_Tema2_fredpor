package com.example.app_tema1_fredpor


import android.Manifest.permission.CALL_PHONE
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class LLamadaActivity : AppCompatActivity() {

    private lateinit var botonRealizarLLamada: ImageButton
    private val REQUEST_CODE_LLAMADA = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_llamada)

        botonRealizarLLamada = findViewById(R.id.botonRealizarLLamada)
        val volverMenu = findViewById<Button>(R.id.volverMenu)

        botonRealizarLLamada.setOnClickListener {
            val intentLargo = Intent(Intent.ACTION_CALL, Uri.parse("tel:androidx.appcompat.widget.AppCompatTextView{112 app:id/textoLLamada}"))

            if (ContextCompat.checkSelfPermission(this, CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
               startActivity(intentLargo)
            } else {
                ActivityCompat.requestPermissions(this, arrayOf(CALL_PHONE), REQUEST_CODE_LLAMADA)
            }
        }
        volverMenu.setOnClickListener {
            val intent = Intent(this@LLamadaActivity, Menu::class.java)
            startActivity(intent)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == REQUEST_CODE_LLAMADA) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                val intentLargo = Intent(Intent.ACTION_CALL, Uri.parse("tel:androidx.appcompat.widget.AppCompatTextView{112 app:id/textoLLamada}"))
                startActivity(intentLargo)
            } else {
                Toast.makeText(this, "Los permisos de llamada son necesarios para realizar una llamada.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}