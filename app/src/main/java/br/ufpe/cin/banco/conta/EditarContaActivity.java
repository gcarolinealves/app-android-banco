package br.ufpe.cin.banco.conta;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import java.text.DecimalFormat;
import java.util.regex.Pattern;

import br.ufpe.cin.banco.R;

//Ver anotações TODO no código
public class EditarContaActivity extends AppCompatActivity {

    public static final String KEY_NUMERO_CONTA = "numeroDaConta";
    ContaViewModel viewModel;
    EditText campoNome;
    EditText campoNumero;
    EditText campoCPF;
    EditText campoSaldo;

    private static final String POSITIVE_NUMBER_REGEX = "^\\d+(\\.\\d+)?$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_conta);
        viewModel = new ViewModelProvider(this).get(ContaViewModel.class);

        Button btnAtualizar = findViewById(R.id.btnAtualizar);
        Button btnRemover = findViewById(R.id.btnRemover);
        campoNome = findViewById(R.id.nome);
        campoNumero = findViewById(R.id.numero);
        campoCPF = findViewById(R.id.cpf);
        campoSaldo = findViewById(R.id.saldo);

        //Recebe o número da conta enviado pela ContaViewHolder e atualiza os campos do formulário
        Intent i = getIntent();
        String numeroConta = i.getStringExtra(KEY_NUMERO_CONTA);

        Toast.makeText(this, "numero a ser editado: " + numeroConta, Toast.LENGTH_SHORT).show();
        //TODO usar o número da conta passado via Intent para recuperar informações da conta

        campoNumero.setEnabled(false);
        campoNumero.setText(numeroConta);
        viewModel.buscarPeloNumero(numeroConta);

        viewModel.contaAtual.observe(
                this,
                conta -> {
                    campoNome.setText(conta.nomeCliente);
                    campoCPF.setText(conta.cpfCliente);
                    DecimalFormat df = new DecimalFormat("0.00");
                    campoSaldo.setText(df.format(conta.saldo));
                }
        );

        btnAtualizar.setText("Editar");
        btnAtualizar.setOnClickListener(
                v -> {
                    String nomeCliente = campoNome.getText().toString();
                    String cpfCliente = campoCPF.getText().toString();
                    String saldoConta = campoSaldo.getText().toString();

                    //TODO: Incluir validações aqui, antes de criar um objeto Conta. Se todas as validações passarem, aí sim monta um objeto Conta.
                    //Valida que nenhum campo está vazio
                    if (nomeCliente.isEmpty() && cpfCliente.isEmpty() && saldoConta.isEmpty()){
                        Toast.makeText(this, "Todos os campos são obrigatórios!", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    //Executa as outras validações antes de editar uma conta
                    if (!validarCampos(nomeCliente, campoNome) || !validarCampos(cpfCliente, campoCPF) ||
                    !validarCampos(saldoConta, campoSaldo)){
                        return;
                    }

                    //TODO: chamar o método que vai atualizar a conta no Banco de Dados
                    Conta c = new Conta(numeroConta, Double.valueOf(saldoConta), nomeCliente, cpfCliente);
                    viewModel.atualizar(c);
                    finish();



                }
        );

        btnRemover.setOnClickListener(v -> {
            //TODO implementar remoção da conta
            viewModel.remover(viewModel.contaAtual.getValue());
            finish();
        });
    }

    private boolean validarCampos(String conteudoCampo, EditText campoEditText){
        if (conteudoCampo.isEmpty()){
            if (campoEditText == campoNome){
                Toast.makeText(this, "O nome do cliente não pode estar vazio.", Toast.LENGTH_SHORT).show();
                return false;
            } else if (campoEditText == campoCPF){
                Toast.makeText(this, "O cpf do cliente não pode estar vazio.", Toast.LENGTH_SHORT).show();
                return false;
            } else if (campoEditText == campoNumero){
                Toast.makeText(this, "O número da conta não pode estar vazio.", Toast.LENGTH_SHORT).show();
                return false;
            } else if (campoEditText == campoSaldo){
                Toast.makeText(this, "O saldo da conta não pode estar vazio.", Toast.LENGTH_SHORT).show();
                return false;
            }
        } else {
            if (campoEditText == campoNome && conteudoCampo.length() < 5){
                Toast.makeText(this, "O nome precisa conter pelo menos 5 caracteres.", Toast.LENGTH_SHORT).show();
                return false;
            } else if (campoEditText == campoCPF && conteudoCampo.length() != 11){
                Toast.makeText(this, "O CPF deve conter 11 caracteres, sem pontos ou traços.", Toast.LENGTH_SHORT).show();
                return false;
            } else if (campoEditText == campoNumero && !Pattern.matches(POSITIVE_NUMBER_REGEX, conteudoCampo)){
                Toast.makeText(this, "O número da conta aceita apenas números.", Toast.LENGTH_SHORT).show();
                return false;
            } else if (campoEditText == campoSaldo && !Pattern.matches(POSITIVE_NUMBER_REGEX, conteudoCampo)){
                Toast.makeText(this, "O saldo aceita apenas números.", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }
}