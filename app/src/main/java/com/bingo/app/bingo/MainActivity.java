package com.bingo.app.bingo;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {


    private TextView numerosSorteadosTextView;
    private TextView numeroSorteadoTextView;
    private Random randomico;
    private ArrayList<Integer> listaNumeroSorteados;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaNumeroSorteados = new ArrayList<>();
        numeroSorteadoTextView = findViewById(R.id.numeroSorteadoTextView);
        randomico = new Random(System.currentTimeMillis());
        numerosSorteadosTextView = findViewById(R.id.numerosSorteadosTextView);

    }

    public void Sortear(View view) {
        if (view.getId() == R.id.btnSortear) {
            if (listaNumeroSorteados.size() < 75) {
                Integer numero = randomico.nextInt(75) + 1;
                if(listaNumeroSorteados.contains(numero)) {
                    numero = randomico.nextInt(75) + 1;
                }
                listaNumeroSorteados.add(numero);
                numerosSorteadosTextView.setText(numerosSorteadosTextView.getText() + numero.toString() + "  ");

                String letra = "";

                if (numero >= 1 && numero <= 15) {
                    letra = "B";
                }
                if (numero >= 16 && numero <= 30) {
                    letra = "I";
                }
                if (numero >= 31 && numero <= 45) {
                    letra = "N";
                }
                if (numero >= 46 && numero <= 60) {
                    letra = "G";
                }
                if (numero >= 61 && numero <= 75) {
                    letra = "O";
                }

                numeroSorteadoTextView.setText(letra + numero);
            } else {
                Toast.makeText(this, "Não há mais números à serem sorteados", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void Reiniciar(View botao) {
        if (botao.getId() == R.id.btnReiniciar) {
            listaNumeroSorteados.clear();
            numerosSorteadosTextView.setText(null);
            numeroSorteadoTextView.setText(null);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("NumeroSorteado", numeroSorteadoTextView.getText().toString());
        outState.putString("NumerosSorteados", numerosSorteadosTextView.getText().toString());
        outState.putIntegerArrayList("listaNumeroSorteados", listaNumeroSorteados);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            numeroSorteadoTextView.setText(savedInstanceState.getString("NumeroSorteado",null));
            numerosSorteadosTextView.setText(savedInstanceState.getString("NumerosSorteados", null));
            listaNumeroSorteados = savedInstanceState.getIntegerArrayList("listaNumeroSorteados");
        }
    }
}
