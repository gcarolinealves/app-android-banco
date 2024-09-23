package br.ufpe.cin.banco;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

import br.ufpe.cin.banco.conta.ContaRepository;
import br.ufpe.cin.banco.transacoes.Transacao;
import br.ufpe.cin.banco.transacoes.TransacaoViewModel;

//Ver anotações TODO no código
public class CreditarActivity extends AppCompatActivity {
    BancoViewModel viewModel;
    TransacaoViewModel transacaoViewModel;
    EditText numeroContaOrigem;
    EditText valorOperacao;

    private static final String POSITIVE_NUMBER_REGEX = "^\\d+(\\.\\d+)?$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operacoes);
        viewModel = new ViewModelProvider(this).get(BancoViewModel.class);
        transacaoViewModel = new ViewModelProvider(this).get(TransacaoViewModel.class);

        TextView tipoOperacao = findViewById(R.id.tipoOperacao);
        numeroContaOrigem = findViewById(R.id.numeroContaOrigem);
        TextView labelContaDestino = findViewById(R.id.labelContaDestino);
        EditText numeroContaDestino = findViewById(R.id.numeroContaDestino);
        valorOperacao = findViewById(R.id.valor);
        Button btnOperacao = findViewById(R.id.btnOperacao);
        labelContaDestino.setVisibility(View.GONE);
        numeroContaDestino.setVisibility(View.GONE);
        valorOperacao.setHint(valorOperacao.getHint() + " creditado");
        tipoOperacao.setText("CREDITAR");
        btnOperacao.setText("Creditar");

        btnOperacao.setOnClickListener(
                v -> {
                    String numOrigem = numeroContaOrigem.getText().toString();
                    String valor = valorOperacao.getText().toString();
                    //TODO lembrar de implementar validação do número da conta e do valor da operação, antes de efetuar a operação de crédito.
                    //Valida que nenhum campo está vazio
                    if (numOrigem.isEmpty() && valor.isEmpty()) {
                        Toast.makeText(this, "Todos os campos são obrigatórios!", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    //Valida os outros campos antes de realizar a transação
                    if (!validarCampos(numOrigem, numeroContaOrigem) || !validarCampos(valor, valorOperacao)) {
                        return;
                    }

                    // O método abaixo está sendo chamado, mas precisa ser implementado na classe BancoViewModel para funcionar.
                    // Tem que salvar a transação no Banco de Dados também, criando um objeto Transacao que será salvo na tabela transacoes por meio de TransacaoViewModel
                    double valorDouble = Double.valueOf(valor);
                    viewModel.creditar(numOrigem,valorDouble);
                    finish();

                    //Pega a data e hora do sistema e configura como será exibido
                    Date now = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault()); // Formatando a exibição da data
                    String data = sdf.format(now);

                    //Cria um novo objeto Transacao e adiciona na TransacaoViewModel
                    Transacao creditar = new Transacao(0, 'C', numOrigem, valorDouble, data);
                    transacaoViewModel.inserir(creditar);
                    finish();
                }
        );
    }

    //Valida os campos de Creditar
    private boolean validarCampos(String conteudoCampo, EditText campoEditText) {
        if (conteudoCampo.isEmpty()) {
            if (campoEditText == numeroContaOrigem) {
                Toast.makeText(this, "A conta de origem não pode estar vazia.", Toast.LENGTH_SHORT).show();
                return false;
            } else if (campoEditText == valorOperacao) {
                Toast.makeText(this, "O valor não pode estar vazio.", Toast.LENGTH_SHORT).show();
                return false;
            }
        } else {
            if (campoEditText == numeroContaOrigem && !Pattern.matches(POSITIVE_NUMBER_REGEX, conteudoCampo)) {
                Toast.makeText(this, "A conta de origem aceita apenas números.", Toast.LENGTH_SHORT).show();
                return false;
            } else if (campoEditText == valorOperacao && Double.parseDouble(conteudoCampo) < 0) {
                Toast.makeText(this, "O valor não pode ser negativo.", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }
}