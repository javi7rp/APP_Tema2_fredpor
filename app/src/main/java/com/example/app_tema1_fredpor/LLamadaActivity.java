package com.example.app_tema1_fredpor;


import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import android.Manifest;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class LLamadaActivity extends AppCompatActivity {

    private ImageButton botonRealizarLLamada;
    private final int REQUEST_CODE_LLAMADA = 1;
    private final String telefono = "112";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_llamada);
        botonRealizarLLamada = findViewById(R.id.botonRealizarLLamada);
        Button volverMenu = findViewById(R.id.volverMenu);
        botonRealizarLLamada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(LLamadaActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CODE_LLAMADA);
                } else {
                    hacerLLamada();
                }
            }
        });

        volverMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LLamadaActivity.this, menu.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE_LLAMADA) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                hacerLLamada();
            } else {
                Toast.makeText(this, "No se aceptaron los permisos para realizar la llamada", Toast.LENGTH_SHORT).show();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
    private void hacerLLamada(){
        Intent intentLlamada = new Intent(Intent.ACTION_CALL);
        intentLlamada.setData(Uri.parse("tel:" + telefono));
        try {
            startActivity(intentLlamada);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "Error no se ha podido llamar", Toast.LENGTH_SHORT).show();
        }
    }
}
