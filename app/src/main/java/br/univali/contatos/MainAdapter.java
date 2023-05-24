package br.univali.contatos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AdapterListUpdateCallback;
import androidx.recyclerview.widget.RecyclerView;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import br.univali.contatos.contatos.Telefones;

public class MainAdapter
        extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    List<Telefones> telefone;
    public MainAdapter(List<Telefones> telefone){
        this.telefone = telefone;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main, parent, false);
        ViewHolder userViewHolder = new ViewHolder(view);
        return userViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String a = telefone.get(position).getCelular();
        String b = telefone.get(position).getTipo();
        holder.textViewTelefoneTipo.setText(b);
        holder.textViewTelefone.setText(a);
        holder.telefone = a;
        //holder = position;
    }

    @Override
    public int getItemCount() {
        return this.telefone.size();
    }

    public static class ViewHolder
            extends RecyclerView.ViewHolder {

        TextView textViewTelefone;
        TextView textViewTelefoneTipo;
        String telefone;
        View rootview;
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            rootview = itemView;
            textViewTelefone = itemView.findViewById(R.id.textViewTelefone);
            textViewTelefoneTipo = itemView.findViewById(R.id.textViewTelefoneTipo);

            itemView.findViewById(R.id.buttonEditar).setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Toast.makeText(itemView.getContext(), "Editar : " + telefone,Toast.LENGTH_SHORT).show();
                }
            });

            itemView.findViewById(R.id.buttonExcluir).setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Toast.makeText(itemView.getContext(), "Excluir : " + telefone,Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
}

