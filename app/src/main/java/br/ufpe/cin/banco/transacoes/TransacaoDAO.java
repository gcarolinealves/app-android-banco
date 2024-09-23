package br.ufpe.cin.banco.transacoes;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import br.ufpe.cin.banco.conta.Conta;

//Ver anotações TODO no código
@Dao
public interface TransacaoDAO {

    @Insert
    void inserir(Transacao t);

    //não deve ser possível editar ou remover uma transação

    @Query("SELECT * FROM transacoes ORDER BY dataTransacao DESC")
    LiveData<List<Transacao>> transacoes();

    //TODO incluir métodos para buscar transações pelo (1) número da conta, (2) pela data, filtrando pelo tipo da transação (crédito, débito, ou todas)
    //Método para buscar pelo número da conta
    @Query("SELECT * FROM transacoes WHERE numeroConta = :numeroProcurado")
    List<Transacao> buscarPeloNumero(String numeroProcurado);

    //Método para buscar pela data da transacao
    @Query("SELECT * FROM transacoes WHERE dataTransacao LIKE :dataProcurada")
    List<Transacao> buscarPelaData(String dataProcurada);

    //Método para buscar por transacoes de crédito e data
    @Query("SELECT * FROM transacoes WHERE tipoTransacao = :tipoProcurado AND dataTransacao LIKE :dataProcurada")
    List<Transacao> buscarPorCreditoeData(Character tipoProcurado, String dataProcurada);

    //Método para buscar por transacoes de crédito e numero
    @Query("SELECT * FROM transacoes WHERE tipoTransacao = :tipoProcurado AND numeroConta = :numeroProcurado")
    List<Transacao> buscarPorCreditoeNumero(Character tipoProcurado, String numeroProcurado);

    //Método para buscar por transacoes de débito e data
    @Query("SELECT * FROM transacoes WHERE tipoTransacao = :tipoProcurado AND dataTransacao LIKE :dataProcurada")
    List<Transacao> buscarPorDebitoeData(Character tipoProcurado, String dataProcurada);

    //Método para buscar por transacoes de dédito e numero
    @Query("SELECT * FROM transacoes WHERE tipoTransacao = :tipoProcurado AND numeroConta = :numeroProcurado")
    List<Transacao> buscarPorDebitoeNumero(Character tipoProcurado, String numeroProcurado);
}
