package br.ufpe.cin.banco.conta;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import br.ufpe.cin.banco.R;
//ESTA CLASSE NAO PRECISA SER MODIFICADA!
public class ContaAdapter extends ListAdapter<Conta, ContaViewHolder> {
    LayoutInflater inflater;

    public ContaAdapter(LayoutInflater layoutInflater) {
        super(DIFF_CALLBACK);
        this.inflater = layoutInflater;
    }

    @NonNull
    @Override
    public ContaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ContaViewHolder(inflater.inflate(R.layout.linha_conta, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ContaViewHolder holder, int position) {
        holder.bindTo(getItem(position));
    }

    private static final DiffUtil.ItemCallback<Conta> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Conta>() {
                @Override
                public boolean areItemsTheSame(@NonNull Conta oldItem, @NonNull Conta newItem) {
                    return oldItem.numero.equals(newItem.numero);
                }

                @Override
                public boolean areContentsTheSame(@NonNull Conta oldItem, @NonNull Conta newItem) {
                    return oldItem.nomeCliente.equals(newItem.nomeCliente) &&
                            oldItem.cpfCliente.equals(newItem.cpfCliente) &&
                            oldItem.numero.equals(newItem.numero) &&
                            oldItem.saldo == newItem.saldo;
                }
            };
}
