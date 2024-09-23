package br.ufpe.cin.banco.transacoes;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import br.ufpe.cin.banco.R;

//ESTA CLASSE NAO PRECISA SER MODIFICADA!
public class TransacaoAdapter extends ListAdapter<Transacao, TransacaoViewHolder> {
    LayoutInflater inflater;

    public TransacaoAdapter(LayoutInflater layoutInflater) {
        super(DIFF_CALLBACK);
        this.inflater = layoutInflater;
    }

    @NonNull
    @Override
    public TransacaoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TransacaoViewHolder(inflater.inflate(R.layout.linha_transacao, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TransacaoViewHolder holder, int position) {
        holder.bindTo(getItem(position));
    }

    private static final DiffUtil.ItemCallback<Transacao> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Transacao>() {
                @Override
                public boolean areItemsTheSame(@NonNull Transacao oldItem, @NonNull Transacao newItem) {
                    return oldItem.getId() == newItem.getId();
                }

                @Override
                public boolean areContentsTheSame(@NonNull Transacao oldItem, @NonNull Transacao newItem) {
                    return oldItem.tipoTransacao == newItem.tipoTransacao &&
                            oldItem.numeroConta.equals(newItem.numeroConta) &&
                            oldItem.valorTransacao == newItem.valorTransacao &&
                            oldItem.dataTransacao.equals(newItem.dataTransacao);
                }
            };
}
