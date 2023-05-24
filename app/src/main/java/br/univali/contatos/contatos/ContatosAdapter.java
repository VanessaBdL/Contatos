package br.univali.contatos.contatos;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.univali.contatos.R;

public class ContatosAdapter extends RecyclerView.Adapter<ContatosAdapter.ContatoViewHolder>{
    private final List<Contatos> contatos;
    private final List<String> contatosIds;
    private final FragmentActivity activity;

    ContatosAdapter(List<Contatos> contatos, List<String> contatosIds, FragmentActivity activity){
        this.contatos = contatos;
        this.contatosIds = contatosIds;
        this.activity = activity;
    }

    static class ContatoViewHolder extends RecyclerView.ViewHolder {
        private final TextView nomeView;
        private final TextView celularView;

        ContatoViewHolder(@NonNull View itemView) {
            super(itemView);
            nomeView = itemView.findViewById(R.id.tvListContatoNome);
            celularView = itemView.findViewById(R.id.tvListContatoCelular);
        }
    }

    @NonNull
    @Override
    public ContatoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.contatos_item, parent, false);
        return new ContatoViewHolder(v);
    }
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ContatoViewHolder viewHolder, int i) {
        final String id = contatosIds.get(i);
        viewHolder.nomeView.setText(contatos.get(i).getNome());
        //viewHolder.celularView.setText(contatos.get(i).getId());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b = new Bundle();
                b.putString("id", id);

                EditarFragment editarFragment = new EditarFragment();
                FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
                editarFragment.setArguments(b);
                ft.replace(R.id.frameContato, editarFragment).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return contatos.size();
    }
}
