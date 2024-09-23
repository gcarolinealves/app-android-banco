package br.ufpe.cin.banco.transacoes;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import br.ufpe.cin.banco.BancoDB;
import br.ufpe.cin.banco.conta.Conta;

//Ver anotações TODO no código
public class TransacaoViewModel extends AndroidViewModel {

    private TransacaoRepository repository;
    public LiveData<List<Transacao>> transacoes;
    private MutableLiveData<List<Transacao>> _transacaoAtual = new MutableLiveData<>();
    public LiveData<List<Transacao>> transacaoAtual = _transacaoAtual;

    public TransacaoViewModel(@NonNull Application application) {
        super(application);
        this.repository = new TransacaoRepository(BancoDB.getDB(application).transacaoDAO());
        this.transacoes = repository.getTransacoes();
    }

    public void inserir(Transacao t) { rodarEmBackground(() -> repository.inserir(t)); }
    //TODO implementar métodos de busca de transações

    //Método para buscar pelo número da conta
    void buscarPeloNumero(String numeroConta) {
        rodarEmBackground(() -> {
            List<Transacao> t = this.repository.buscarPeloNumero(numeroConta);
            _transacaoAtual.postValue(t);
        });
    }

    //Método para buscar pela data da transacao
    void buscarPelaData(String dataTransacao) {
        rodarEmBackground(() -> {
            List<Transacao> t = this.repository.buscarPelaData(dataTransacao);
            _transacaoAtual.postValue(t);
        });
    }

    //Método para buscar por transacoes de crédito e data
    void buscarPorCreditoeData(Character tipoTransacao, String dataTransacao) {
        rodarEmBackground(() -> {
            List<Transacao> t = this.repository.buscarPorCreditoeData(tipoTransacao, dataTransacao);
            _transacaoAtual.postValue(t);
        });
    }

    //Método para buscar por transacoes de crédito e numero
    void buscarPorCreditoeNumero(Character tipoTransacao, String numeroConta) {
        rodarEmBackground(() -> {
            List<Transacao> t = this.repository.buscarPorCreditoeNumero(tipoTransacao, numeroConta);
            _transacaoAtual.postValue(t);
        });
    }

    //Método para buscar por transacoes de débito e data
    void buscarPorDebitoeData(Character tipoTransacao, String dataTransacao) {
        rodarEmBackground(() -> {
            List<Transacao> t = this.repository.buscarPorDebitoeData(tipoTransacao, dataTransacao);
            _transacaoAtual.postValue(t);
        });
    }

    //Método para buscar por transacoes de dédito e numero
    void buscarPorDebitoeNumero(Character tipoTransacao, String numeroConta) {
        rodarEmBackground(() -> {
            List<Transacao> t = this.repository.buscarPorDebitoeNumero(tipoTransacao, numeroConta);
            _transacaoAtual.postValue(t);
        });
    }


    private void rodarEmBackground(Runnable r){ new Thread(r).start(); }
}
