package br.ufpe.cin.banco.conta;

import androidx.annotation.WorkerThread;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

//Ver anotações TODO no código
public class ContaRepository {
    private ContaDAO dao;
    private LiveData<List<Conta>> contas;

    public ContaRepository(ContaDAO dao) {
        this.dao = dao;
        this.contas = dao.contas();
    }

    public LiveData<List<Conta>> getContas() {
        return contas;
    }

    public LiveData<Double> getSaldoTotal(){
        return dao.getSaldoTotal();
    }

    @WorkerThread
    public void inserir(Conta c) {
        dao.adicionar(c);
    }

    //Método para atualizar conta
    @WorkerThread
    public void atualizar(Conta c) { dao.atualizar(c); }

    //Método para remover conta
    @WorkerThread
    public void remover(Conta c) { dao.remover(c); }

    //Método para buscar conta pelo nome
    @WorkerThread
    public List<Conta> buscarPeloNome(String nomeCliente) { return dao.buscarPeloNome(nomeCliente); }

    //Método para buscar conta pelo cpf
    @WorkerThread
    public List<Conta> buscarPeloCPF(String cpfCliente) {
        return dao.buscarPeloCPF(cpfCliente);
    }

    //Método para buscar conta pelo número
    @WorkerThread
    public Conta buscarPeloNumero(String numeroConta) { return dao.buscarPeloNumero(numeroConta); }

}
