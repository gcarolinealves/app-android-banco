package br.ufpe.cin.banco.conta;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

//Ver anotações TODO no código
@Dao
public interface ContaDAO {

    @Insert(entity = Conta.class, onConflict = OnConflictStrategy.REPLACE)
    void adicionar(Conta c);

    //Método para atualizar conta
    @Update
    void atualizar (Conta c);

    //Método para remover conta
    @Delete
    void remover (Conta c);

    @Query("SELECT * FROM contas ORDER BY numero ASC")
    LiveData<List<Conta>> contas();

    @Query("SELECT * FROM contas ORDER BY numero ASC")
    List<Conta> todasContas();

    //Método para buscar pelo número da conta
    @Query("SELECT * FROM contas WHERE numero = :numeroProcurado")
    Conta buscarPeloNumero(String numeroProcurado);

    //Método para buscar pelo nome do cliente
    @Query("SELECT * FROM contas WHERE nomeCliente = :nomeProcurado")
    List<Conta> buscarPeloNome(String nomeProcurado);

    //Método para buscar pelo cpf do cliente
    @Query("SELECT * FROM contas WHERE cpfCliente = :cpfProcurado")
    List<Conta> buscarPeloCPF(String cpfProcurado);

    //Buscar o saldo total do banco de todas as contas com saldo positivo
    @Query("SELECT SUM (CASE WHEN saldo > 0 THEN saldo ELSE 0 END) from contas")
    LiveData<Double> getSaldoTotal();

}
