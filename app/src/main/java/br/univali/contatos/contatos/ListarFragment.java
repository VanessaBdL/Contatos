package br.univali.contatos.contatos;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import br.univali.contatos.R;

public class ListarFragment extends Fragment {

    public ListarFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.contatos_fragment_listar, container, false);

       FirebaseFirestore db = FirebaseFirestore.getInstance();

        RecyclerView recyclerViewContatos = v.findViewById(R.id.recyclerViewContatos);

        CollectionReference collectionContato = db.collection("Contatos");
        collectionContato.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            LinearLayoutManager manager = new LinearLayoutManager(v.getContext());
                            recyclerViewContatos.setLayoutManager(manager);
                            recyclerViewContatos.addItemDecoration(new DividerItemDecoration(v.getContext(), LinearLayoutManager.VERTICAL));
                            recyclerViewContatos.setHasFixedSize(true);
                            List<Contatos> contatos = task.getResult().toObjects(Contatos.class);
                            List<String> contatosIds = new ArrayList<String>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                contatosIds.add(document.getId());
                            }
                            ContatosAdapter adapterContatos = new ContatosAdapter(contatos, contatosIds, getActivity());
                            recyclerViewContatos.setAdapter(adapterContatos);
                        } else {
                            Toast.makeText(getActivity(), "Erro ao buscar os contatos!", Toast.LENGTH_LONG).show();
                            Log.d("ListarContato", "mensagem de erro: ", task.getException());
                        }
                    }
                });
        return v;
    }
}