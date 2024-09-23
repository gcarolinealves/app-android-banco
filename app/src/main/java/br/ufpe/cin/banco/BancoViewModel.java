package br.ufpe.cin.banco;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import br.ufpe.cin.banco.conta.Conta;
import br.ufpe.cin.banco.conta.ContaRepository;
import br.ufpe.cin.banco.transacoes.Transacao;
import br.ufpe.cin.banco.transacoes.TransacaoRepository;
import android.os.Handler;
import android.os.Looper;

import java.util.List;

//Ver anotações TODO no código
public class BancoViewModel extends AndroidViewModel {
    private ContaRepository contaRepository;
    private TransacaoRepository transacaoRepository;
    private MutableLiveData<List<Conta>> _contas = new MutableLiveData<>();
    public LiveData<List<Conta>> contas = _contas;
    private MutableLiveData<Conta> _contaAtual = new MutableLiveData<>();
    public LiveData<Conta> contaAtual = _contaAtual;
    private MutableLiveData<List<Transacao>> _transacoes = new MutableLiveData<>();
    public LiveData<List<Transacao>> transacoes = _transacoes;
    private LiveData<Double> saldoTotal;

    public BancoViewModel(@NonNull Application application) {
        super(application);
        this.contaRepository = new ContaRepository(BancoDB.getDB(application).contaDAO());
        this.transacaoRepository = new TransacaoRepository(BancoDB.getDB(application).transacaoDAO());
    }

    void transferir(String numeroContaOrigem, String numeroContaDestino, double valor) {
        //TODO implementar transferência entre contas (lembrar de salvar no BD os objetos Conta modificados)
        rodarEmBackground(() -> {
            Conta contaOrigem = contaRepository.buscarPeloNumero(numeroContaOrigem);
            Conta contaDestino = contaRepository.buscarPeloNumero(numeroContaDestino);

            if (contaOrigem == null || contaDestino == null){
                runOnUiThread(() ->
                        Toast.makeText(this.getApplication(), "As contas de origem ou destino são inválidas.", Toast.LENGTH_SHORT).show()
                );
                return;
            }

            assert contaOrigem != null;
            if (contaOrigem.numero.equals(contaDestino.numero)){
                runOnUiThread(() ->
                        Toast.makeText(this.getApplication(), "As contas de origem e destino são iguais.", Toast.LENGTH_SHORT).show()
                );
                return;
            }
            contaOrigem.transferir(contaDestino, valor);
            contaRepository.atualizar(contaOrigem);
            contaRepository.atualizar(contaDestino);
        });
    }

    void creditar(String numeroConta, double valor) {
        //TODO implementar creditar em conta (lembrar de salvar no BD o objeto Conta modificado)
        rodarEmBackground(() -> {
            Conta c = contaRepository.buscarPeloNumero(numeroConta);
            if (c == null){
                runOnUiThread(() ->
                        Toast.makeText(this.getApplication(), "Conta inválida.", Toast.LENGTH_SHORT).show()
                );
                return;
            }
            assert c != null;
            c.creditar(valor);
            contaRepository.atualizar(c);
        });
    }

    void debitar(String numeroConta, double valor) {
        //TODO implementar debitar em conta (lembrar de salvar no BD o objeto Conta modificado)
        rodarEmBackground(() -> {
            Conta c = contaRepository.buscarPeloNumero(numeroConta);
            if (c == null){
                runOnUiThread(() ->
                        Toast.makeText(this.getApplication(), "Conta inválida.", Toast.LENGTH_SHORT).show()
                );
                return;
            }
            assert c != null;
            c.debitar(valor);
            contaRepository.atualizar(c);
        });
    }

    void buscarContasPeloNome(String nomeCliente) {
        //TODO implementar busca pelo nome do Cliente
        rodarEmBackground(() -> {
            List<Conta> c = contaRepository.buscarPeloNome(nomeCliente);
            _contas.postValue(c);
        });
    }

    void buscarContasPeloCPF(String cpfCliente) {
        //TODO implementar busca pelo CPF do Cliente
        rodarEmBackground(() -> {
            List<Conta> c = contaRepository.buscarPeloCPF(cpfCliente);
            _contas.postValue(c);
        });
    }

    void buscarContaPeloNumero(String numeroConta) {
        //TODO implementar busca pelo número da Conta
        rodarEmBackground(() -> {
            Conta c = contaRepository.buscarPeloNumero(numeroConta);
            if (c != null){
                _contaAtual.postValue(c);
            }
        });
    }

    public void buscarTransacoesPorCreditoeData(Character tipoTransacao, String dataTransacao) {
        //TODO implementar
        rodarEmBackground(() -> {
            List<Transacao> t = transacaoRepository.buscarPorCreditoeData(tipoTransacao, dataTransacao);
            _transacoes.postValue(t);
        });

    }

    public void buscarTransacoesPorCreditoeNumero(Character tipoTransacao, String numeroConta) {
        //TODO implementar
        rodarEmBackground(() -> {
            List<Transacao> t = transacaoRepository.buscarPorCreditoeNumero(tipoTransacao, numeroConta);
            _transacoes.postValue(t);
        });
    }

    public void buscarTransacoesPorDebitoeData(Character tipoTransacao, String dataTransacao) {
        //TODO implementar
        rodarEmBackground(() -> {
            List<Transacao> t = transacaoRepository.buscarPorDebitoeData(tipoTransacao, dataTransacao);
            _transacoes.postValue(t);
        });
    }

    public void buscarTransacoesPorDebitoeNumero(Character tipoTransacao, String numeroConta) {
        //TODO implementar
        rodarEmBackground(() -> {
            List<Transacao> t = transacaoRepository.buscarPorDebitoeNumero(tipoTransacao, numeroConta);
            _transacoes.postValue(t);
        });
    }

    public void buscarTransacoesPorData(String dataTransacao) {
        //TODO implementar
        rodarEmBackground(() -> {
            List<Transacao> t = transacaoRepository.buscarPelaData(dataTransacao);
            _transacoes.postValue(t);
        });
    }

    public void buscarTransacoesPorNumero(String numeroConta) {
        //TODO implementar
        rodarEmBackground(() -> {
            List<Transacao> t = transacaoRepository.buscarPeloNumero(numeroConta);
            _transacoes.postValue(t);
        });
    }

    public LiveData<Double> getSaldoTotal(){
        return contaRepository.getSaldoTotal();
    }

    public LiveData<List<Conta>> getContas() {
        return contaRepository.getContas();
    }

    private void rodarEmBackground(Runnable r){ new Thread(r).start(); }

    private void runOnUiThread(Runnable tarefa) { new Handler(Looper.getMainLooper()).post(tarefa); }
}
