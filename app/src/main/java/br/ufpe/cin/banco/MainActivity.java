package br.ufpe.cin.banco;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.List;

import br.ufpe.cin.banco.cliente.ClientesActivity;
import br.ufpe.cin.banco.conta.Conta;
import br.ufpe.cin.banco.conta.ContasActivity;
import br.ufpe.cin.banco.transacoes.TransacoesActivity;

//Ver anotações TODO no código
public class MainActivity extends AppCompatActivity {
    BancoViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(BancoViewModel.class);

        Button contas = findViewById(R.id.btnContas);
        Button clientes = findViewById(R.id.btnClientes);
        Button transferir = findViewById(R.id.btnTransferir);
        Button debitar = findViewById(R.id.btnDebitar);
        Button creditar = findViewById(R.id.btnCreditar);
        Button pesquisar = findViewById(R.id.btnPesquisar);
        Button transacoes = findViewById(R.id.btnTransacoes);

        TextView totalBanco = findViewById(R.id.totalDinheiroBanco);

        //Remover a linha abaixo se for implementar a parte de Clientes
        clientes.setEnabled(false);

        contas.setOnClickListener(
                v -> startActivity(new Intent(this, ContasActivity.class))
        );
        clientes.setOnClickListener(
                v -> startActivity(new Intent(this, ClientesActivity.class))
        );
        transferir.setOnClickListener(
                v -> startActivity(new Intent(this, TransferirActivity.class))
        );
        creditar.setOnClickListener(
                v -> startActivity(new Intent(this, CreditarActivity.class))
        );
        debitar.setOnClickListener(
                v -> startActivity(new Intent(this, DebitarActivity.class))
        );
        pesquisar.setOnClickListener(
                v -> startActivity(new Intent(this, PesquisarActivity.class))
        );
        transacoes.setOnClickListener(
                v -> startActivity(new Intent(this, TransacoesActivity.class))
        );

        //TODO Neste arquivo ainda falta a atualização automática do valor total de dinheiro armazenado no banco
        viewModel.getSaldoTotal().observe(
                this,
                saldoTotal -> {
                    if (saldoTotal != null) {
                        totalBanco.setText(String.format("$%.2f", saldoTotal));
                    } else {
                        totalBanco.setText(String.format("$0.00"));
                    }
                }
        );
    }
}