package com.example.puzzlenumber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Instruccion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruccion);
    }

    public void volver(View view) {
        Intent regreso = new Intent(this, Inicio.class);
        startActivity(regreso);
        finish();
    }
}