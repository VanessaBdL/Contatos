package br.univali.contatos.contatos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;


import java.util.List;

import br.univali.contatos.MainAdapter;
import br.univali.contatos.R;

public class AdicionarFragment extends Fragment {
    private Spinner spinnerSeries;
    private EditText etNome;
    private EditText etCelular;
    private FirebaseFirestore db;

    String[] series = new String[] {"Casa", "Celular", "Trabalho"};

    RecyclerView recyclerView;
    MainAdapter adapter;

    public AdicionarFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.contatos_fragment_adicionar, container, false);


        db = FirebaseFirestore.getInstance();

        spinnerSeries = v.findViewById(R.id.spinnerTipoTelefone);
        etNome = v.findViewById(R.id.editTextNome);
        etCelular = v.findViewById(R.id.editTextNumero);

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, series);

        spinnerSeries.setAdapter(spinnerAdapter);

        Button btnSalvar = v.findViewById(R.id.buttonAdicionar);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adicionar();
            }
        });



        return v;
    }


        private void adicionar () {


            CollectionReference collectionTelefone = db.collection("Telefone");
            CollectionReference collectionContato = db.collection("Contatos");


            collectionContato.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    boolean adicionavel = true;

                    if (task.isSuccessful()) {
                        List<Contatos> Contatos = task.getResult().toObjects(Contatos.class);

                        for (int i = 0; i < Contatos.size(); i++) {
                            if (etNome.getText().toString().equals(Contatos.get(i).getNome())) {
                                collectionTelefone.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {


                                        boolean adicionavel = true;

                                        if (task.isSuccessful()) {
                                            List<Telefones> Telefones = task.getResult().toObjects(Telefones.class);

                                            for (int i = 0; i < Telefones.size(); i++) {
                                                if (etCelular.getText().toString().equals(Telefones.get(i).getCelular())) {
                                                    Toast.makeText(getActivity(), "Telefone Já Cadastrado", Toast.LENGTH_LONG).show();
                                                    adicionavel = false;
                                                }
                                            }


                                        }

                                        if (adicionavel){
                                            if (etNome.getText().toString().equals("")) {
                                                Toast.makeText(getActivity(), "Por favor, informe o nome!", Toast.LENGTH_LONG).show();
                                            } else if (etCelular.getText().toString().equals("")) {
                                                Toast.makeText(getActivity(), "Por favor, informe o número do celular!", Toast.LENGTH_LONG).show();
                                            } else {


                                                Telefones t = new Telefones();

                                                t.setCelular(etCelular.getText().toString());

                                                int spinner_pos = spinnerSeries.getSelectedItemPosition();

                                                String tipo = series[spinner_pos];

                                                t.setTipo(tipo);


                                                t.setIdUsuario(etNome.getText().toString());
                                                collectionTelefone.add(t).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                                    @Override
                                                    public void onSuccess(DocumentReference documentReference) {
                                                        //  Toast.makeText(getActivity(), "Telefone salvo!", Toast.LENGTH_LONG).show();

                                                    }
                                                }).addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        // Toast.makeText(getActivity(), "Erro ao salvar o Telefone", Toast.LENGTH_LONG).show();
                                                        // Log.d("AdicionarTelefone", "mensagem de erro: ", e);
                                                    }
                                                });
                                                //getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameContato, new ListarFragment()).commit();
                                            }
                                        }

                                    }
                                });


                                adicionavel = false;
                            }
                        }

                        if (adicionavel){
                            collectionTelefone.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {


                                    boolean adicionavel = true;

                                    if (task.isSuccessful()) {
                                        List<Telefones> Telefones = task.getResult().toObjects(Telefones.class);

                                        for (int i = 0; i < Telefones.size(); i++) {
                                            if (etCelular.getText().toString().equals(Telefones.get(i).getCelular())) {
                                                Toast.makeText(getActivity(), "Telefone Já Cadastrado", Toast.LENGTH_LONG).show();
                                                adicionavel = false;
                                            }
                                        }


                                    }

                                    if (adicionavel){
                                        if (etNome.getText().toString().equals("")) {
                                            Toast.makeText(getActivity(), "Por favor, informe o nome!", Toast.LENGTH_LONG).show();
                                        } else if (etCelular.getText().toString().equals("")) {
                                            Toast.makeText(getActivity(), "Por favor, informe o número do celular!", Toast.LENGTH_LONG).show();
                                        } else {
                                            Contatos m = new Contatos();
                                            m.setNome(etNome.getText().toString());

                                            collectionContato.add(m).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                                @Override
                                                public void onSuccess(DocumentReference documentReference) {
                                                    //Toast.makeText(getActivity(), "Contato salvo!", Toast.LENGTH_LONG).show();
                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    //Toast.makeText(getActivity(), "Erro ao salvar o contato", Toast.LENGTH_LONG).show();
                                                    //Log.d("AdicionarContato", "mensagem de erro: ", e);
                                                }
                                            });


                                            Telefones t = new Telefones();

                                            t.setCelular(etCelular.getText().toString());

                                            int spinner_pos = spinnerSeries.getSelectedItemPosition();

                                            String tipo = series[spinner_pos];

                                            t.setTipo(tipo);


                                            t.setIdUsuario(etNome.getText().toString());
                                            collectionTelefone.add(t).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                                @Override
                                                public void onSuccess(DocumentReference documentReference) {
                                                    //  Toast.makeText(getActivity(), "Telefone salvo!", Toast.LENGTH_LONG).show();

                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    // Toast.makeText(getActivity(), "Erro ao salvar o Telefone", Toast.LENGTH_LONG).show();
                                                    // Log.d("AdicionarTelefone", "mensagem de erro: ", e);
                                                }
                                            });
                                            //getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameContato, new ListarFragment()).commit();
                                        }
                                    }

                                }
                            });
                        };




                    }
                }

            });
    }

}
