package br.ufpe.cin.banco.cliente;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import br.ufpe.cin.banco.R;

public class AdicionarClienteActivity extends AppCompatActivity {

    ClienteViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_cliente);
    }
}