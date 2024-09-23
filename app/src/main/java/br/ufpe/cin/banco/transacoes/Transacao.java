package br.ufpe.cin.banco.transacoes;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//ESTA CLASSE NAO PRECISA SER MODIFICADA!
@Entity(tableName = "transacoes")
public class Transacao {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    public char tipoTransacao; //'C' para crédito, 'D' para débito
    public String numeroConta;
    public double valorTransacao;
    public String dataTransacao;

    //Todas as vezes que for criar um objeto Transacao,
    // informe 0 como o valor do primeiro atributo (int id),
    // pois Room irá gerar um número único como identificador de chave primária.
    public Transacao(int id, char tipoTransacao, String numeroConta, double valorTransacao, String dataTransacao) {
        this.id = id;
        this.tipoTransacao = tipoTransacao;
        this.numeroConta = numeroConta;
        this.valorTransacao = valorTransacao;
        this.dataTransacao = dataTransacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int i) {
        this.id = i;
    }

    @Override
    public String toString() {
        return "Transacao{" +
                "id=" + id +
                ", tipoTransacao=" + tipoTransacao +
                ", numeroConta='" + numeroConta + '\'' +
                ", valorTransacao=" + valorTransacao +
                ", dataTransacao='" + dataTransacao + '\'' +
                '}';
    }
}
