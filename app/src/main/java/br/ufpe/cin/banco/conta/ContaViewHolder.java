package br.ufpe.cin.banco.conta;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;

import br.ufpe.cin.banco.R;

//Ver anotações TODO no código
public class ContaViewHolder  extends RecyclerView.ViewHolder {
    TextView nomeCliente = null;
    TextView infoConta = null;
    ImageView icone = null;
    ImageView btnEdit = null;
    ImageView btnDelete = null;
    Context context = null;

    public ContaViewHolder(@NonNull View linha) {
        super(linha);
        this.nomeCliente = linha.findViewById(R.id.nomeCliente);
        this.infoConta = linha.findViewById(R.id.infoConta);
        this.icone = linha.findViewById(R.id.icone);
        this.btnEdit = linha.findViewById(R.id.btn_edit);
        this.btnDelete = linha.findViewById(R.id.btn_delete);
        this.context = linha.getContext();
    }

    void bindTo(Conta c) {
        this.nomeCliente.setText(c.nomeCliente);
        this.infoConta.setText(c.numero + " | " + "Saldo atual: " + NumberFormat.getCurrencyInstance().format(c.saldo));
        //TODO Falta atualizar a imagem de acordo com o valor do saldo atual
        //Atualiza a imagem da conta caso o saldo seja negativo
        if (c.saldo < 0){
            this.icone.setImageResource(R.drawable.delete);
        } else {
            this.icone.setImageResource(R.drawable.ok);
        }

        this.btnEdit.setOnClickListener(
                v -> {
                    Toast.makeText(
                            this.context,
                            "clicou no botão de editar conta",
                            Toast.LENGTH_SHORT
                    ).show();
                    Intent i = new Intent(
                            this.context,
                            EditarContaActivity.class
                    );
                    //TODO Está especificando a Activity mas não está passando o número da conta pelo Intent
                    //Está passando o número da conta pelo intent para EditarContaActivity
                    i.putExtra(EditarContaActivity.KEY_NUMERO_CONTA, c.numero);
                    this.context.startActivity(i);
                }
        );
        this.btnDelete.setOnClickListener(
                v -> {
                    Toast.makeText(
                            this.context,
                            "clicou no botão de deletar conta",
                            Toast.LENGTH_SHORT
                    ).show();
                    //TODO implementar aqui a remoção da conta ao clicar!
                    //Remove a conta ao clicar no botão de delete
                    ContaViewModel viewModel = new ViewModelProvider((AppCompatActivity) context).get(ContaViewModel.class);
                    viewModel.remover(c);
                }
        );
    }

}
