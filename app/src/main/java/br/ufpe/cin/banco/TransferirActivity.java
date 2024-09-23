package br.ufpe.cin.banco;

import static java.text.DateFormat.getDateInstance;

import android.os.Bundle;
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

import br.ufpe.cin.banco.transacoes.Transacao;
import br.ufpe.cin.banco.transacoes.TransacaoViewModel;


//Ver anotações TODO no código
public class TransferirActivity extends AppCompatActivity {

    BancoViewModel viewModel;
    TransacaoViewModel transacaoViewModel;

    EditText numeroContaOrigem;
    EditText numeroContaDestino;
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
        numeroContaDestino = findViewById(R.id.numeroContaDestino);
        valorOperacao = findViewById(R.id.valor);
        Button btnOperacao = findViewById(R.id.btnOperacao);

        valorOperacao.setHint(valorOperacao.getHint() + " transferido");
        tipoOperacao.setText("TRANSFERIR");
        btnOperacao.setText("Transferir");

        btnOperacao.setOnClickListener(
                v -> {
                    String numOrigem = numeroContaOrigem.getText().toString();
                    String numDestino = numeroContaDestino.getText().toString();
                    String valor = valorOperacao.getText().toString();
                    //TODO lembrar de implementar validação dos números das contas e do valor da operação, antes de efetuar a operação de transferência.
                    //Valida que nenhum campo está vazio
                    if (numOrigem.isEmpty() && numDestino.isEmpty() && valor.isEmpty()) {
                        Toast.makeText(this, "Todos os campos são obrigatórios!", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    //Valida os outros campos antes de realizar a transação
                    if (!validarCampos(numOrigem, numeroContaOrigem) || !validarCampos(numDestino, numeroContaDestino) ||
                            !validarCampos(valor, valorOperacao)) {
                        return;
                    }

                    // O método abaixo está sendo chamado, mas precisa ser implementado na classe BancoViewModel para funcionar.
                    // Tem que salvar a transação no Banco de Dados também, criando um objeto Transacao que será salvo na tabela transacoes por meio de TransacaoViewModel
                    double valorDouble = Double.valueOf(valor);
                    viewModel.transferir(numOrigem, numDestino, valorDouble);
                    finish();

                    //Pega a data e hora do sistema e configura como será exibido
                    Date now = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault()); // Formatando a exibição da data
                    String data = sdf.format(now);

                    //Cria os objetos Transacao e adiciona na TransacaoViewModel
                    Transacao creditar = new Transacao(0, 'C', numDestino, valorDouble, data);
                    Transacao debitar = new Transacao (0, 'D', numOrigem, valorDouble, data);

                    transacaoViewModel.inserir(creditar);
                    transacaoViewModel.inserir(debitar);
                    finish();
                }
        );

    }

    //Valida os campos de Transferir
    private boolean validarCampos(String conteudoCampo, EditText campoEditText) {
        if (conteudoCampo.isEmpty()) {
            if (campoEditText == numeroContaOrigem) {
                Toast.makeText(this, "A conta de origem não pode estar vazia.", Toast.LENGTH_SHORT).show();
                return false;
            } else if (campoEditText == numeroContaDestino) {
                Toast.makeText(this, "A conta de destino não pode estar vazia.", Toast.LENGTH_SHORT).show();
                return false;
            } else if (campoEditText == valorOperacao) {
                Toast.makeText(this, "O valor da transferência não pode estar vazio.", Toast.LENGTH_SHORT).show();
                return false;
            }
        } else {
            if (campoEditText == numeroContaOrigem && !Pattern.matches(POSITIVE_NUMBER_REGEX, conteudoCampo)) {
                Toast.makeText(this, "A conta de origem aceita apenas números.", Toast.LENGTH_SHORT).show();
                return false;
            } else if (campoEditText == numeroContaDestino && !Pattern.matches(POSITIVE_NUMBER_REGEX, conteudoCampo)) {
                Toast.makeText(this, "A conta de destino aceita apenas números.", Toast.LENGTH_SHORT).show();
                return false;
            } else if (campoEditText == valorOperacao && Double.parseDouble(conteudoCampo) < 0) {
                Toast.makeText(this, "O valor da transferência não pode ser negativo.", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }
}