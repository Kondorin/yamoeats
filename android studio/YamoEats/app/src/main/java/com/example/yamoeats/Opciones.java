package com.example.yamoeats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class Opciones extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opciones);

    }

    public void sendEmail(View view){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("mailto:"));
        String to[] = {"yamoeats@gmail.com"};
        intent.putExtra(Intent.EXTRA_EMAIL, to);
        intent.putExtra(Intent.EXTRA_SUBJECT, "QUEJA / SUGERENCIA");
        intent.putExtra(Intent.EXTRA_TEXT, "Ejemplo de mi queja o sugerencia");
        intent.setType("message/rfc822");
        Intent chooser = Intent.createChooser(intent, "Email");
        startActivity(chooser);

    }

    public void openApp(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("market://details?id=com.tapblaze.pizzabusiness"));
        Intent chooser = intent.createChooser(intent,"Open App");
        startActivity(chooser);
    }

    public void regresarLogin(View view){
        finish();

    }

    public void manualUsuario(View view){
        Intent i = new Intent(Opciones.this,ManualUsuario.class);
        startActivity(i);
    }
}
