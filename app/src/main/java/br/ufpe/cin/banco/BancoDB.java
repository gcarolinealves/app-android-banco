package br.ufpe.cin.banco;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.List;
import java.util.concurrent.Executors;

import br.ufpe.cin.banco.conta.Conta;
import br.ufpe.cin.banco.conta.ContaDAO;
import br.ufpe.cin.banco.transacoes.Transacao;
import br.ufpe.cin.banco.transacoes.TransacaoDAO;

//ESTA CLASSE NAO PRECISA SER MODIFICADA, SE NAO FOR IMPLEMENTAR A FUNCIONALIDADE DE CLIENTES!
@Database(entities = {Conta.class, Transacao.class}, version = 1)
public abstract class BancoDB extends RoomDatabase {
    public abstract ContaDAO contaDAO();

    public abstract TransacaoDAO transacaoDAO();

    public static final String DB_NAME = "banco.db";
    private static volatile BancoDB INSTANCE;

    public synchronized static BancoDB getDB(Context c) {
        if (INSTANCE == null) {
            Builder<BancoDB> dbBuilder = Room.databaseBuilder(
                    c,
                    BancoDB.class,
                    DB_NAME
            );
            dbBuilder.setQueryCallback(new QueryCallback() {
                @Override
                public void onQuery(@NonNull String s, @NonNull List<?> list) {
                    System.out.println("SQL Query: " + s + " SQL Args:" + list.toString());
                }
            }, Executors.newSingleThreadExecutor());

            INSTANCE = dbBuilder.build();
        }
        return INSTANCE;
    }

}
