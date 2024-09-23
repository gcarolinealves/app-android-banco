package br.ufpe.cin.banco.cliente;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "clientes")
public class Cliente {
    @PrimaryKey
    @NonNull
    public String cpf;
    @NonNull
    public String nome;

}