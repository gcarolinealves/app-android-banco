package br.ufpe.cin.banco.conta;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import br.ufpe.cin.banco.BancoDB;

//Ver métodos anotados com TODO
public class ContaViewModel extends AndroidViewModel {

    private ContaRepository repository;
    public LiveData<List<Conta>> contas;
    private MutableLiveData<Conta> _contaAtual = new MutableLiveData<>();
    public LiveData<Conta> contaAtual = _contaAtual;

    public ContaViewModel(@NonNull Application application) {
        super(application);
        this.repository = new ContaRepository(BancoDB.getDB(application).contaDAO());
        this.contas = repository.getContas();
    }

    void inserir(Conta c) { rodarEmBackground(() -> repository.inserir(c));}

    void atualizar(Conta c) { rodarEmBackground(() -> repository.atualizar(c)); }

    void remover(Conta c) { rodarEmBackground(() -> repository.remover(c)); }

    //Método para buscar conta pelo número
    void buscarPeloNumero(String numeroConta) {
        rodarEmBackground(() -> {
            Conta c = this.repository.buscarPeloNumero(numeroConta);
            _contaAtual.postValue(c);
        });
    }

    //Método para buscar conta pelo nome
    void buscarPeloNome(String nomeCliente) {
        rodarEmBackground(() -> {
            Conta c = this.repository.buscarPeloNumero(nomeCliente);
            _contaAtual.postValue(c);
        });
    }

    //Método para buscar conta pelo cpf
    void buscarPeloCPF(String cpfCliente) {
        rodarEmBackground(() -> {
            Conta c = this.repository.buscarPeloNumero(cpfCliente);
            _contaAtual.postValue(c);
        });
    }

    private void rodarEmBackground(Runnable r){ new Thread(r).start(); }
}
