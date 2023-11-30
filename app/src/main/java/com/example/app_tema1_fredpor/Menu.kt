package com.example.app_tema1_fredpor

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.provider.AlarmClock
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class Menu : AppCompatActivity() {

    private lateinit var progressBar: ProgressBar
    private var progressStatus = 0
    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_main)

        val imagenPerfil = findViewById<ImageView>(R.id.imagenPerfil)
        val imagenFooter = findViewById<ImageView>(R.id.imagenFooter)
        val textoBienvenida = findViewById<TextView>(R.id.textoBienvenida)
        val textoFooter = findViewById<TextView>(R.id.textoFooter)

        val usuario = ListarUser.obtenerUsuario()
        val user = usuario.firstOrNull()
        val userName = user?.userName
        val userSex = user?.userSex


        when (userSex) {
            "H" -> {
                imagenPerfil.setImageResource(R.drawable.avatar_messi)
                imagenFooter.setImageResource(R.drawable.messi_store)
                textoFooter.text = "THE MESSI STORE"
            }
            "M" -> {
                imagenPerfil.setImageResource(R.drawable.mujer)
                imagenFooter.setImageResource(R.drawable.shein)
                textoFooter.text = "SHEIN"
            }
            "O" -> {
                imagenPerfil.setImageResource(R.drawable.otro)
                imagenFooter.setImageResource(R.drawable.orapma)
                textoFooter.text = "ORAPMA PELUQUEROS DIGAME"
            }
        }
        textoBienvenida.text = userName?.toUpperCase()

        val botonDados = findViewById<ImageButton>(R.id.botonDados)
        val botonAlarma = findViewById<ImageButton>(R.id.botonAlarma)
        val botonModdle = findViewById<ImageButton>(R.id.botonModdle)
        val botonMaps = findViewById<ImageButton>(R.id.botonMaps)
        val botonLlamada = findViewById<ImageButton>(R.id.botonLLamada)
        val botonChistes = findViewById<ImageButton>(R.id.botonChistes)
        val tableLayout = findViewById<TableLayout>(R.id.tableLayout)
        progressBar = findViewById(R.id.ProgressBar)

        Thread {
            while (progressStatus < 100) {
                progressStatus += 1
                handler.post {
                    progressBar.progress = progressStatus
                }
                try {
                    Thread.sleep(50)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }

            tableLayout.post {
                progressBar.visibility = View.INVISIBLE
                tableLayout.visibility = View.VISIBLE
            }
        }.start()

        botonDados.setOnClickListener {
            val intent = Intent(this@Menu, Dados::class.java)
            startActivity(intent)
        }

        botonLlamada.setOnClickListener {
            val intent = Intent(this@Menu, LLamadaActivity::class.java)
            startActivity(intent)
        }

        botonChistes.setOnClickListener {
            val intent = Intent(this@Menu, Chistes::class.java)
            startActivity(intent)
        }

        botonAlarma.setOnClickListener {
            val cal = Calendar.getInstance()
            var horaActual = cal[Calendar.HOUR_OF_DAY]
            var minActual = cal[Calendar.MINUTE]

            minActual += 2
            if (minActual >= 60) {
                minActual -= 60
                horaActual++
                if (horaActual >= 24) {
                    horaActual -= 24
                }
            }
            val intent = Intent(AlarmClock.ACTION_SET_ALARM)
            intent.putExtra(AlarmClock.EXTRA_MESSAGE, "ALARMA DE PRUEBA DE 2 MIN")
            intent.putExtra(AlarmClock.EXTRA_DAYS, Calendar.SUNDAY)
            intent.putExtra(AlarmClock.EXTRA_HOUR, horaActual)
            intent.putExtra(AlarmClock.EXTRA_MINUTES, minActual)

            try {
                startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                e.printStackTrace()
                Log.d("Error ", "no se ha podido crear la alarma")
            }
        }

        botonModdle.setOnClickListener {
            val url = "https://educacionadistancia.juntadeandalucia.es/centros/jaen/"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }

        botonMaps.setOnClickListener {
            val latitud = 40.4165
            val longitud = -3.70256
            val label = "Madrid"

            val intentUri = Uri.parse("geo: $latitud,$longitud?q=$latitud,$longitud($label)")
            val map = Intent(Intent.ACTION_VIEW, intentUri)
            map.`package` = "com.google.android.apps.maps"
            startActivity(map)
        }
    }
}