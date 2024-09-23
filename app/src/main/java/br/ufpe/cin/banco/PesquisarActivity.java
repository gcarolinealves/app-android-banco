package br.ufpe.cin.banco;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.ufpe.cin.banco.conta.Conta;
import br.ufpe.cin.banco.conta.ContaAdapter;
import br.ufpe.cin.banco.conta.ContaViewModel;
import br.ufpe.cin.banco.transacoes.Transacao;

//Ver anotações TODO no código
public class PesquisarActivity extends AppCompatActivity {
    BancoViewModel viewModel;
    ContaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisar);
        viewModel = new ViewModelProvider(this).get(BancoViewModel.class);
        EditText aPesquisar = findViewById(R.id.pesquisa);
        Button btnPesquisar = findViewById(R.id.btn_Pesquisar);
        RadioGroup tipoPesquisa = findViewById(R.id.tipoPesquisa);
        RecyclerView rvResultado = findViewById(R.id.rvResultado);
        adapter = new ContaAdapter(getLayoutInflater());
        rvResultado.setLayoutManager(new LinearLayoutManager(this));
        rvResultado.setAdapter(adapter);

        btnPesquisar.setOnClickListener(
                v -> {
                    String oQueFoiDigitado = aPesquisar.getText().toString();
                    //TODO implementar a busca de acordo com o tipo de busca escolhido pelo usuário
                    //Valida que o campo não é vazio
                    if (oQueFoiDigitado.isEmpty()){
                        Toast.makeText(this, "O campo de pesquisa não pode estar vazio!", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    int idRadioButtonPesquisa = tipoPesquisa.getCheckedRadioButtonId();

                    //Chama o método correspondente de acordo com o id do RadioButton
                    if (idRadioButtonPesquisa == R.id.peloNomeCliente){
                        viewModel.buscarContasPeloNome(oQueFoiDigitado);
                    } else if (idRadioButtonPesquisa == R.id.peloCPFcliente){
                        viewModel.buscarContasPeloCPF(oQueFoiDigitado);
                    } else if (idRadioButtonPesquisa == R.id.peloNumeroConta){
                        viewModel.buscarContaPeloNumero(oQueFoiDigitado);
                    }
                }
        );

        //TODO atualizar o RecyclerView com resultados da busca na medida que encontrar
        //Exibe uma lista com todas as contas filtradas
        this.viewModel.contas.observe(
                this,
                todasContas -> {
                    List<Conta> novaListaContas = new ArrayList<>(todasContas);
                    adapter.submitList(novaListaContas);
                }
        );

        this.viewModel.contaAtual.observe(
                this,
                contaAtual -> {
                    List<Conta> novaConta = new ArrayList<>();
                    novaConta.add(contaAtual);
                    adapter.submitList(novaConta);
                }
        );

    }
}