package br.ufpe.cin.banco.transacoes;

import androidx.annotation.WorkerThread;
import androidx.lifecycle.LiveData;

import java.util.List;

import br.ufpe.cin.banco.conta.Conta;

//Ver anotações TODO no código
public class TransacaoRepository {
    private TransacaoDAO dao;
    private LiveData<List<Transacao>> transacoes;

    public TransacaoRepository(TransacaoDAO dao) {
        this.dao = dao;
        this.transacoes = dao.transacoes();
    }

    public LiveData<List<Transacao>> getTransacoes() {
        return this.transacoes;
    }

    @WorkerThread
    public void inserir(Transacao t) {
        dao.inserir(t);
    }

    //TODO implementar métodos de busca de transações
    //Método para buscar pelo número da conta
    @WorkerThread
    public List<Transacao> buscarPeloNumero(String numeroConta) { return dao.buscarPeloNumero(numeroConta); }

    //Método para buscar pela data da transacao
    @WorkerThread
    public List<Transacao> buscarPelaData(String dataTransacao) { return dao.buscarPelaData(dataTransacao); }

    //Método para buscar por transacoes de crédito e data
    @WorkerThread
    public List<Transacao> buscarPorCreditoeData(Character tipoTransacao, String dataTransacao) { return dao.buscarPorCreditoeData(tipoTransacao, dataTransacao); }

    //Método para buscar por transacoes de crédito e numero
    @WorkerThread
    public List<Transacao> buscarPorCreditoeNumero(Character tipoTransacao, String numeroConta) { return dao.buscarPorCreditoeNumero(tipoTransacao, numeroConta); }

    //Método para buscar por transacoes de débito e data
    @WorkerThread
    public List<Transacao> buscarPorDebitoeData(Character tipoTransacao, String dataTransacao) { return dao.buscarPorDebitoeData(tipoTransacao, dataTransacao); }

    //Método para buscar por transacoes de dédito e numero
    @WorkerThread
    public List<Transacao> buscarPorDebitoeNumero(Character tipoTransacao, String numeroConta) { return dao.buscarPorDebitoeNumero(tipoTransacao, numeroConta); }
}
