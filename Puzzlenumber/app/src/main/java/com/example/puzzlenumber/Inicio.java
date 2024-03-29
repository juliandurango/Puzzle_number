package com.example.puzzlenumber;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;

public class Inicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
    }

    public void comenzarJuego(View view) {
        Intent juego = new Intent(this, Nivel.class);
        startActivity(juego);
        finish();
    }

    public void verInstrucciones(View view) {
        Intent instruccion = new Intent(this, Instruccion.class);
        startActivity(instruccion);
        finish();
    }
}