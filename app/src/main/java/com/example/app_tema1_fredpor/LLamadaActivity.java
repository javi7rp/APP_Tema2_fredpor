package com.example.app_tema1_fredpor;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class LLamadaActivity extends AppCompatActivity {
    private ImageButton botonRealizarLLamada;
    private final int REQUEST_CODE_LLAMADA = 1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_llamada);
        botonRealizarLLamada = findViewById(R.id.botonRealizarLLamada);

        botonRealizarLLamada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                realizarLLamada();
            }
        });
    }
    private void realizarLLamada() {
        String telefono = "112";
        Intent intentLlamada = new Intent(Intent.ACTION_CALL);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            Uri uri = Uri.parse("tel:" + telefono);
            intentLlamada.setData(uri);
            try {
                startActivity(intentLlamada);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(this, "Error no se ha podido llamar al número introducido", Toast.LENGTH_SHORT).show();
            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CODE_LLAMADA);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode){
            case REQUEST_CODE_LLAMADA:
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "Se procederá a realizar la llamada ", Toast.LENGTH_SHORT).show();
                    String telefono = "112"; //teléfono de emergencias
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    Uri uri = Uri.parse("tel:" + telefono);
                    intent.setData(uri);
                    try {
                        startActivity(intent);
                    }catch (ActivityNotFoundException e){
                        e.printStackTrace();
                        Log.d("ERROR", "No he podido marcar la llamada");
                    }

                }else {
                    Toast.makeText(this, "No se aceptó los permisos", Toast.LENGTH_SHORT).show();
                }
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}
