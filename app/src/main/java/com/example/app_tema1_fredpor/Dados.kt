package com.example.app_tema1_fredpor

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class Dados : AppCompatActivity() {

    private var sumaDadosIzq = 0
    private var sumaDadosDer = 0
    private lateinit var solizq: ImageView
    private lateinit var solder: ImageView
    private lateinit var dado1izq: ImageView
    private lateinit var dado2izq: ImageView
    private lateinit var dado3izq: ImageView
    private lateinit var dado1der: ImageView
    private lateinit var dado2der: ImageView
    private lateinit var dado3der: ImageView
    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dados)

        dado1izq = findViewById(R.id.dado1Izq)
        dado2izq = findViewById(R.id.dado2Izq)
        dado3izq = findViewById(R.id.dado3Izq)
        dado1der = findViewById(R.id.dado1Der)
        dado2der = findViewById(R.id.dado2Der)
        dado3der = findViewById(R.id.dado3Der)
        solizq = findViewById(R.id.solucionIzq)
        solder = findViewById(R.id.solucionDer)
        val volverMenu = findViewById<Button>(R.id.volverMenu)
        val rollButton = findViewById<Button>(R.id.rollButton)

        rollButton.setOnClickListener {
            sumaDadosDer = 0
            sumaDadosIzq = 0
            dado1izq.setImageResource(R.drawable.dado1inicio)
            dado2izq.setImageResource(R.drawable.dado1inicio)
            dado3izq.setImageResource(R.drawable.dado1inicio)
            dado1der.setImageResource(R.drawable.dado2inicio)
            dado2der.setImageResource(R.drawable.dado2inicio)
            dado3der.setImageResource(R.drawable.dado2inicio)
            solizq.visibility = ImageView.INVISIBLE
            solder.visibility = ImageView.INVISIBLE

            handler.postDelayed({
                lanzarDadoIzq(dado1izq)
            }, 1000)
            handler.postDelayed({
                lanzarDadoDer(dado1der)
            }, 2000)
            handler.postDelayed({
                lanzarDadoIzq(dado2izq)
            }, 3000)
            handler.postDelayed({
                lanzarDadoDer(dado2der)
            }, 4000)
            handler.postDelayed({
                lanzarDadoIzq(dado3izq)
            }, 5000)
            handler.postDelayed({
                lanzarDadoDer(dado3der)
            }, 6000)

            handler.postDelayed({
                if (sumaDadosIzq > sumaDadosDer) {
                    solizq.setImageResource(R.drawable.trofeo)
                    solder.setImageResource(R.drawable.perdedor)
                } else if (sumaDadosDer > sumaDadosIzq) {
                    solder.setImageResource(R.drawable.trofeo)
                    solizq.setImageResource(R.drawable.perdedor)
                } else if (sumaDadosDer == sumaDadosIzq) {
                    solizq.setImageResource(R.drawable.igual)
                    solder.setImageResource(R.drawable.igual)
                }
                solizq.visibility = ImageView.VISIBLE
                solder.visibility = ImageView.VISIBLE
            }, 7000)
        }

        volverMenu.setOnClickListener {
            val intent = Intent(this@Dados, Menu::class.java)
            startActivity(intent)
        }
    }

    private fun lanzarDadoIzq(diceImageView: ImageView) {
        val random = Random()
        val resultadoDado = random.nextInt(6) + 1
        sumaDadosIzq += resultadoDado
        val imagenId = obtenerImagenIzq(resultadoDado)
        diceImageView.setImageResource(imagenId)
    }

    private fun lanzarDadoDer(diceImageView: ImageView) {
        val random = Random()
        val resultadoDado = random.nextInt(6) + 1
        sumaDadosDer += resultadoDado
        val imagenId = obtenerImagenDer(resultadoDado)
        diceImageView.setImageResource(imagenId)
    }

    private fun obtenerImagenIzq(numeroDado: Int): Int {
        return when (numeroDado) {
            1 -> R.drawable.dado1cara1
            2 -> R.drawable.dado1cara2
            3 -> R.drawable.dado1cara3
            4 -> R.drawable.dado1cara4
            5 -> R.drawable.dado1cara5
            6 -> R.drawable.dado1cara6
            else -> R.drawable.dado1cara1
        }
    }

    private fun obtenerImagenDer(numeroDado: Int): Int {
        return when (numeroDado) {
            1 -> R.drawable.dado2cara1
            2 -> R.drawable.dado2cara2
            3 -> R.drawable.dado2cara3
            4 -> R.drawable.dado2cara4
            5 -> R.drawable.dado2cara5
            6 -> R.drawable.dado2cara6
            else -> R.drawable.dado2cara1
        }
    }
}