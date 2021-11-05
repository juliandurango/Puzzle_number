package com.example.puzzlenumber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Nivel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nivel);
    }

    public void primerNivel(View view) {
        Intent primero = new Intent(this, PrimerNivel.class);
        startActivity(primero);
        finish();
    }

    public void segundoNivel(View view) {
        Intent segundo = new Intent(this, SegundoNivel.class);
        startActivity(segundo);
        finish();
    }

    public void tercerNivel(View view) {
        Intent tercero = new Intent(this, TercerNivel.class);
        startActivity(tercero);
        finish();
    }

    public void volver(View view) {
        Intent regreso = new Intent(this, Inicio.class);
        startActivity(regreso);
        finish();
    }
}