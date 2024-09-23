package br.ufpe.cin.banco.cliente;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import br.ufpe.cin.banco.R;

public class ClientesActivity extends AppCompatActivity {

    ClienteViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);

        viewModel = new ViewModelProvider(this).get(ClienteViewModel.class);

    }
}