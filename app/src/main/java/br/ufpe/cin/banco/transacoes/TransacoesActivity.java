package br.ufpe.cin.banco.transacoes;

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

import br.ufpe.cin.banco.BancoViewModel;
import br.ufpe.cin.banco.R;
import br.ufpe.cin.banco.conta.Conta;

//Ver anotações TODO no código
public class TransacoesActivity extends AppCompatActivity {
    BancoViewModel bancoViewModel;
    TransacaoViewModel transacaoViewModel;
    TransacaoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transacoes);
        bancoViewModel = new ViewModelProvider(this).get(BancoViewModel.class);
        transacaoViewModel = new ViewModelProvider(this).get(TransacaoViewModel.class);
        EditText aPesquisar = findViewById(R.id.pesquisa);
        Button btnPesquisar = findViewById(R.id.btn_Pesquisar);
        RadioGroup tipoTransacao = findViewById(R.id.tipoTransacao);
        RadioGroup tipoPesquisa = findViewById(R.id.tipoPesquisa);
        RecyclerView rvResultado = findViewById(R.id.rvResultado);
        adapter = new TransacaoAdapter(getLayoutInflater());
        rvResultado.setLayoutManager(new LinearLayoutManager(this));
        rvResultado.setAdapter(adapter);

        btnPesquisar.setOnClickListener(
                v -> {
                    String oQueFoiDigitado = aPesquisar.getText().toString();
                    String oQueFoiDigitadoComLike = oQueFoiDigitado + "%";
                    //Valida que o campo não é vazio
                    if (oQueFoiDigitado.isEmpty()){
                        Toast.makeText(this, "O campo de pesquisa não pode estar vazio!", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    //TODO implementar o filtro de transações com o tipo de busca escolhido pelo usuário
                    int idRadioButtonTransacao = tipoTransacao.getCheckedRadioButtonId();
                    int idRadioButtonPesquisa = tipoPesquisa.getCheckedRadioButtonId();

                    //Chama o método correspondente de acordo com o id do RadioButtonTransacao e RadioButtonPesquisa
                    if (idRadioButtonTransacao == R.id.peloTipoCredito){
                        if (idRadioButtonPesquisa == R.id.pelaData){
                            bancoViewModel.buscarTransacoesPorCreditoeData('C', oQueFoiDigitadoComLike);
                        } else if (idRadioButtonPesquisa == R.id.peloNumeroConta){
                            bancoViewModel.buscarTransacoesPorCreditoeNumero('C', oQueFoiDigitado);
                        }
                    } else if (idRadioButtonTransacao == R.id.peloTipoDebito){
                         if (idRadioButtonPesquisa == R.id.pelaData){
                             bancoViewModel.buscarTransacoesPorDebitoeData('D', oQueFoiDigitadoComLike);
                         } else if (idRadioButtonPesquisa == R.id.peloNumeroConta){
                             bancoViewModel.buscarTransacoesPorDebitoeNumero('D', oQueFoiDigitado);
                         }
                    } else if (idRadioButtonTransacao == R.id.peloTipoTodos){
                        if (idRadioButtonPesquisa == R.id.pelaData){
                            bancoViewModel.buscarTransacoesPorData(oQueFoiDigitadoComLike);
                        } else if (idRadioButtonPesquisa == R.id.peloNumeroConta){
                            bancoViewModel.buscarTransacoesPorNumero(oQueFoiDigitado);
                        }
                    }
                }
        );

        //TODO atualizar o RecyclerView com resultados da busca na medida que encontrar
        // inicialmente deve exibir a lista de todas as transações
        //Exibe uma lista com todas as contas filtradas
        this.transacaoViewModel.transacoes.observe(
                this,
                todasTransacoes -> {
                    List<Transacao> novaListaTransacoes = new ArrayList<>(todasTransacoes);
                    adapter.submitList(novaListaTransacoes);
                }
        );

        this.bancoViewModel.transacoes.observe(
                this,
                todasTransacoes -> {
                    List<Transacao> novaListaTransacoes = new ArrayList<>(todasTransacoes);
                    adapter.submitList(novaListaTransacoes);
                }
        );

    }
}