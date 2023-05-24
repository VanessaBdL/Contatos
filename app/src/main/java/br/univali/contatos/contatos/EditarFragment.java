package br.univali.contatos.contatos;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.WriteBatch;
import com.google.firebase.firestore.core.FirestoreClient;

import br.univali.contatos.MainAdapter;
import br.univali.contatos.R;

public class EditarFragment extends Fragment {

    private FirebaseFirestore db;
    private Contatos m;
    private Telefones t;
    private EditText etNome;
    private EditText etCelular;
    RecyclerView recyclerView;
    MainAdapter adapter;

    public EditarFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.contatos_fragment_editar, container, false);
        etNome = v.findViewById(R.id.editTextNome);
        etCelular = v.findViewById(R.id.editTextCelular);
        recyclerView = v.findViewById(R.id.recyclerviewLista);

        // id enviado via parâmetro no ListarFragment
        Bundle b = getArguments();
        String id_contato = b != null ? b.getString("id") : null;


        Bundle a = getArguments();
        String teste = a != null ? a.getString("id") : null;


                    Button btnExcluir = v.findViewById(R.id.buttonExcluir);
                    btnExcluir.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                            builder.setTitle(R.string.dialog_excluir_contato);
                            builder.setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    excluir(id_contato, teste);
                                }
                            });
                            builder.setNegativeButton(R.string.nao, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    // Não faz nada
                                }
                            });
                            AlertDialog alertDialog = builder.create();
                            alertDialog.show();
                        }
                    });

                    db = FirebaseFirestore.getInstance();

                    // Obtém os dados da mãe que será editado
                    assert id_contato != null;
                    DocumentReference documentContato = db.collection("Contatos").document(id_contato);
                    documentContato.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot document = task.getResult();
                                if (document.exists()) {
                                    m = task.getResult().toObject(Contatos.class);
                                    assert m != null;
                                    etNome.setText(m.getNome());
                                } else {
                                    Toast.makeText(getActivity(), "Erro ao buscar o contato!", Toast.LENGTH_LONG).show();
                                    Log.d("ListarContatos", "nenhum documento encontrado");
                                }
                            } else {
                                Toast.makeText(getActivity(), "Erro ao buscar o contato!", Toast.LENGTH_LONG).show();
                                Log.d("ListarContatos", "erro: ", task.getException());
                            }
                        }
                    });

                    return v;
                }

                private void editar (String id){
                    if (etNome.getText().toString().equals("")) {
                        Toast.makeText(getActivity(), "Por favor, informe o nome!", Toast.LENGTH_LONG).show();
                    } else if (etCelular.getText().toString().equals("")) {
                        Toast.makeText(getActivity(), "Por favor, informe o número do celular!", Toast.LENGTH_LONG).show();
                    } else {
                        m.setNome(etNome.getText().toString());
                        t.setIdUsuario(etNome.getText().toString());
                        DocumentReference documentContato = db.collection("Contatos").document(id);
                        documentContato.set(m).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(getActivity(), "Contato atualizada!", Toast.LENGTH_LONG).show();
                                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameContato, new ListarFragment()).commit();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.w("EditarContato", "erro: ", e);
                                    }
                                });
                    }
                }

                private void excluir (String id, String teste){
                    db.collection("Contatos").document(id)
                            .delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                }
                            });

                    db.collection("Telefone").document(teste)
                            .delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                }
                            });
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameContato, new ListarFragment()).commit();
                }
}



