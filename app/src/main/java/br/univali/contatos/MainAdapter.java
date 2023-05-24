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

/*public class MainAdapter
        extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    // Initialize variable
    ArrayList<String> arrayList;
    ItemClickListener itemClickListener;
    int selectedPosition = -1;

    // Create constructor
    public MainAdapter(ArrayList<String> arrayList,ItemClickListener itemClickListener)
    {
        this.arrayList = arrayList;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder
    onCreateViewHolder(@NonNull ViewGroup parent,
                       int viewType)
    {
        // Initialize view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_main, parent,
                        false);
        // Pass holder view
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,
                                 int position)
    {
        // Set text on radio button
        holder.radioButton.setText(arrayList.get(position));

        // Checked selected radio button
        holder.radioButton.setChecked(position
                == selectedPosition);

        // set listener on radio button
        holder.radioButton.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(
                            CompoundButton compoundButton,
                            boolean b)
                    {
                        // check condition
                        if (b) {
                            // When checked
                            // update selected position
                            selectedPosition
                                    = holder.getAdapterPosition();
                            // Call listener
                            itemClickListener.onClick(
                                    holder.radioButton.getText()
                                            .toString());
                        }
                    }
                });
    }

    @Override public long getItemId(int position)
    {
        // pass position
        return position;
    }
    @Override public int getItemViewType(int position)
    {
        // pass position
        return position;
    }

    @Override public int getItemCount()
    {
        // pass total list size
        return arrayList.size();
    }

    public class ViewHolder
            extends RecyclerView.ViewHolder {
        // Initialize variable
        RadioButton radioButton;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);

            // Assign variable
            radioButton = itemView.findViewById(R.id.radio_button);
        }
    }
}
*/
class GFG {
    public static void main(String[] args)
    {
        System.out.println("GFG!");
    }
}


//TODO OUTRA VERSAO
/*
*
* public class MainAdapter
        extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    ArrayAdapter<String> telefone;
    Button btnEditar;
    Button btnRemover;
    public MainAdapter(ArrayAdapter<String> telefone, Button btnEditar, Button btnRemover){
        this.telefone = telefone;
        this.btnEditar = btnEditar;
        this.btnRemover = btnRemover;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        View view = LayoutInflater.from()
    }

    @Override
    public int getItemCount() {
        return this.telefone.getCount();
    }

    public class ViewHolder
            extends RecyclerView.ViewHolder {


        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);

        }
    }
}
* */


//TODO VERSAO ANTIGA
/*
*

public class MainAdapter
        extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    // Initialize variable
    ArrayList<String> telefone;
    Button btnEditar;
    Button btnRemover;

    public MainAdapter(ArrayList<String> telefone, Button btnEditar, Button btnRemover){
        this.telefone = telefone;
        this.btnEditar = btnEditar;
        this.btnRemover = btnRemover;
    }
    int selectedPosition = -1;

    @NonNull
    @Override
    public ViewHolder
    onCreateViewHolder(@NonNull ViewGroup parent,
                       int viewType)
    {
        // Initialize view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main, parent, false);
        // Pass holder view
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,
                                 int position)
    {
        // Set text on radio button
        holder.btnEditar.setText(telefone.get(position));

        // Checked selected radio button
        holder.btnEditar.(position == selectedPosition);

        // set listener on radio button
        holder.btnEditar.setOnClickListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton,boolean b)
                    {
                        // check condition
                        if (b) {
                            // When checked
                            // update selected position
                            selectedPosition
                                    = holder.getAdapterPosition();
                            // Call listener
                            itemClickListener.onClick(
                                    holder.radioButton.getText()
                                            .toString());
                        }
                    }
                });
    }

    @Override public long getItemId(int position)
    {
        // pass position
        return position;
    }
    @Override public int getItemViewType(int position)
    {
        // pass position
        return position;
    }

    @Override public int getItemCount()
    {
        // pass total list size
        return telefone.size();
    }

    public class ViewHolder
            extends RecyclerView.ViewHolder {
        // Initialize variable
        Button btnEditar;
        Button btnRemover;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);

            // Assign variable
            btnEditar = itemView.findViewById(R.id.buttonEditar);
            btnRemover =  itemView.findViewById(R.id.buttonExcluir);
        }
    }
}

*/