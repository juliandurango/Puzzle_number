package com.example.puzzlenumber;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class TercerNivel extends AppCompatActivity {

    private int vacioX = 4;
    private int vacioY = 4;
    private RelativeLayout group;
    private Button[][] buttons;
    private Button btnReiniciar;
    private Button btnPausar;
    private int[] tiles;
    private TextView moves;
    private int cmoves = 0;
    private TextView tiempo;
    private int ctiempo = 0;
    private Timer timer;
    private boolean isTimeRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tercer_nivel);

        cargarVistas();
        cargarNumeros();
        generarNumeros();
        cargarDatos();
    }

    private void cargarDatos(){
        vacioX = 4;
        vacioY = 4;
        for(int i=0; i<group.getChildCount()-1; i++){
            buttons[i/5][i%5].setText(String.valueOf(tiles[i]));
            buttons[i/5][i%5].setBackgroundResource(android.R.drawable.btn_default);
        }

        buttons[vacioX][vacioY].setText("");
        buttons[vacioX][vacioY].setBackgroundColor(ContextCompat.getColor(this, R.color.colorBtnLibre));
    }

    private void generarNumeros(){
        int n = 24;
        Random random = new Random();
        while(n>1){
            int numeroAle = random.nextInt(n--);
            int aux = tiles[numeroAle];
            tiles[numeroAle] = tiles[n];
            tiles[n] = aux;
        }

        if(!isSolvable()){
            generarNumeros();
        }
    }

    private boolean isSolvable(){
        int cont = 0;
        for(int i=0; i<24; i++){
            for(int j=0; j<i; j++){
                if(tiles[j]>tiles[i]){
                    cont++;
                }
            }
        }
        return cont % 2 == 0;
    }

    private void cargarNumeros(){
        tiles = new int[25];
        for(int i=0; i<group.getChildCount()-1; i++){
            tiles[i] = i + 1;
        }
    }

    private void temporizador(){
        isTimeRunning = true;
        timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run(){
                ctiempo++;
                setTime(ctiempo);
            }
        }, 1000, 1000);
    }

    private void setTime(int ctiempo){
        int seg = ctiempo % 60;
        int hora = ctiempo / 3600;
        int min = (ctiempo - hora * 3600) / 60;

        tiempo.setText(String.format("Time: %02d:%02d:%02d", hora, min, seg));
    }

    private void cargarVistas(){
        group = findViewById(R.id.group);
        moves = findViewById(R.id.moves);
        tiempo = findViewById(R.id.tiempo);
        btnReiniciar = findViewById(R.id.btnReiniciar);
        btnPausar = findViewById(R.id.btnPausar);

        temporizador();
        buttons = new Button[5][5];

        for(int i=0; i<group.getChildCount(); i++){
            buttons[i/5][i%5] = (Button) group.getChildAt(i);
        }

        btnReiniciar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                generarNumeros();
                cargarDatos();
            }
        });

        btnPausar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                if(isTimeRunning){
                    timer.cancel();
                    btnPausar.setText("Pausado");
                    isTimeRunning = false;
                    for(int i=0; i<group.getChildCount(); i++){
                        buttons[i/5][i%5].setClickable(false);
                    }
                }else{
                    temporizador();
                    btnPausar.setText("Pausar");
                    for(int i=0; i<group.getChildCount(); i++){
                        buttons[i/5][i%5].setClickable(true);
                    }
                }
            }
        });
    }

    public void clickear(View view){
        Button button = (Button) view;
        int x = button.getTag().toString().charAt(0)-'0';
        int y = button.getTag().toString().charAt(1)-'0';

        if((Math.abs(vacioX-x) == 1 && vacioY == y) || (Math.abs(vacioY-y) == 1 && vacioX == x)){
            buttons[vacioX][vacioY].setText(button.getText().toString());
            buttons[vacioX][vacioY].setBackgroundResource(android.R.drawable.btn_default);
            button.setText("");
            button.setBackgroundColor(ContextCompat.getColor(this, R.color.colorBtnLibre));
            vacioX = x;
            vacioY = y;
            cmoves++;
            moves.setText("Movimientos: "+cmoves);
            verificar();
        }
    }

    private void verificar(){
        boolean isWin = false;
        if(vacioX == 4 && vacioY == 4){
            for(int i=0; i<group.getChildCount()-1; i++){
                if(buttons[i/5][i%5].getText().toString().equals(String.valueOf(i+1))){
                    isWin=true;
                }else{
                    isWin=false;
                    break;
                }
            }
        }

        if(isWin){
            Toast.makeText(this, "Has ganado", Toast.LENGTH_SHORT).show();
            for(int i=0; i<group.getChildCount(); i++){
                buttons[i/5][i%5].setClickable(false);
            }
            timer.cancel();
            btnReiniciar.setClickable(false);
            btnPausar.setClickable(false);
        }
    }

    public void volver(View view) {
        Intent regreso = new Intent(this, Nivel.class);
        startActivity(regreso);
        finish();
    }
}