package br.ufpe.cin.banco.conta;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//ESTA CLASSE NAO PRECISA SER MODIFICADA!
@Entity(tableName = "contas")
public class Conta {
    @PrimaryKey
    @NonNull
    public String numero;
    public double saldo;
    @NonNull
    public String nomeCliente;
    @NonNull
    public String cpfCliente;

    public Conta(@NonNull String numero, double saldo, @NonNull String nomeCliente, @NonNull String cpfCliente) {
        this.numero = numero;
        this.saldo = saldo;
        this.nomeCliente = nomeCliente;
        this.cpfCliente = cpfCliente;
    }

    public void creditar(double valor) {
        saldo = saldo + valor;
    }

    public void transferir(Conta c, double v) {
        this.debitar(v);
        c.creditar(v);
    }

    public void debitar(double valor) {
        saldo = saldo - valor;
    }

    @Override
    public String toString() {
        return "Conta{" +
                "numero='" + numero + '\'' +
                ", saldo=" + saldo +
                ", nomeCliente='" + nomeCliente + '\'' +
                ", cpfCliente='" + cpfCliente + '\'' +
                '}';
    }
}
