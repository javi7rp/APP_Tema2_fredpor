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
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class LLamadaActivity : AppCompatActivity() {

    private lateinit var botonRealizarLLamada: ImageButton
    private val REQUEST_CODE_LLAMADA = 101


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_llamada)

        botonRealizarLLamada = findViewById(R.id.botonRealizarLLamada)
        val volverMenu = findViewById<Button>(R.id.volverMenu)

        botonRealizarLLamada.setOnClickListener {
            val telefono = "tel: 112"
            val intent = Intent(Intent.ACTION_CALL, Uri.parse(telefono))

            if (ContextCompat.checkSelfPermission(this, CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                startActivity(intent)
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
                val telefono = "tel:112"
                val intent = Intent(Intent.ACTION_CALL, Uri.parse(telefono))
                startActivity(intent)
            } else {
                Toast.makeText(this, "Los permisos de llamada son necesarios para realizar una llamada.", Toast.LENGTH_SHORT).show()
            }
        }
    }



}