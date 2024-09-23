package br.ufpe.cin.banco.conta;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.ufpe.cin.banco.R;

//Ver anotações TODO no código
public class ContasActivity extends AppCompatActivity {
    ContaAdapter adapter;
    ContaViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contas);
        viewModel = new ViewModelProvider(this).get(ContaViewModel.class);
        RecyclerView recyclerView = findViewById(R.id.rvContas);
        adapter = new ContaAdapter(getLayoutInflater());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        Button adicionarConta = findViewById(R.id.btn_Adiciona);
        adicionarConta.setOnClickListener(
                v -> {
                    startActivity(new Intent(this, AdicionarContaActivity.class));
                }
        );

        //TODO Ainda falta implementar o código que atualiza a lista de contas automaticamente na tela
        this.viewModel.contas.observe(
                this,
                todasContas -> {
                    //Código atual apenas lista no log todas as contas salvas no banco de dados, útil para depuração
                    for (Conta c : todasContas) {
                        Log.i("TESTANDO", c.toString());
                    }
                    Log.d("BANCODEDADOS", String.valueOf(todasContas.size()));
                    //Exibe uma lista com todos os objetos do tipo Conta
                    List<Conta> novaListaContas = new ArrayList<>(todasContas);
                    adapter.submitList(novaListaContas);
                }
        );
    }
}