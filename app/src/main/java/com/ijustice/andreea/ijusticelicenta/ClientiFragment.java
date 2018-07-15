package com.ijustice.andreea.ijusticelicenta;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ijustice.andreea.ijusticelicenta.models.Client;
import com.ijustice.andreea.ijusticelicenta.models.ClientiAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ClientiFragment extends Fragment {

    Button btnAdauga;
    ListView listView;
    FirebaseDatabase database;
    DatabaseReference reference;
    List<Client> listaClienti;
    ClientiAdapter adapter;
    FirebaseAuth auth;
    String userId;
    TextView tvMesaj;
    String cheie;


    public ClientiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_clienti, container, false);

        btnAdauga=v.findViewById(R.id.clienti_btn_adauga);
        tvMesaj=v.findViewById(R.id.vizualizareclienti_mesaj);
        listView=v.findViewById(R.id.lv_vizualizare_clienti);
        listaClienti =new ArrayList<>();

        database=FirebaseDatabase.getInstance();
        auth=FirebaseAuth.getInstance();
        FirebaseUser user=auth.getCurrentUser();
        userId=user.getUid();
        reference=database.getReference("clienti").child(userId);



        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listaClienti.clear();
                listView.setAdapter(null);
                for(DataSnapshot ds:dataSnapshot.getChildren()) {
                    cheie=ds.getKey();
                    String nume = ds.child("nume").getValue(String.class);
                    String prenume = ds.child("prenume").getValue(String.class);
                    String adresa = ds.child("adresa").getValue(String.class);
                    String oras = ds.child("oras").getValue(String.class);
                    String telefon = ds.child("telefon").getValue(String.class);
                    String email = ds.child("email").getValue(String.class);
                    String precizari = ds.child("precizari").getValue(String.class);

                    Client client=new Client(nume,prenume,adresa,oras,telefon,email,precizari,cheie);

                     listaClienti.add(client);


                }
                if(listaClienti.size()!=0 && getActivity()!=null){

                    adapter = new ClientiAdapter(getContext(), (ArrayList<Client>) listaClienti);
                    listView.setAdapter(adapter);
                    tvMesaj.setText("");

                }else {
                    tvMesaj.setText("Nu ati adaugat niciun client!");
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btnAdauga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),AdaugaClientActivity.class);
                startActivity(intent);
               // getActivity().finish();

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Client client=adapter.getItem(position);
                Intent intent=new Intent(getActivity(),DetaliiClientActivity.class);
                intent.putExtra("cheie",client.getCheie());
                intent.putExtra("Nume", client.getNume());
                intent.putExtra("Prenume",client.getPrenume());
                intent.putExtra("Adresa",client.getAdresa());
                intent.putExtra("Oras",client.getOras());
                intent.putExtra("Telefon",client.getNrTelefon());
                intent.putExtra("Email",client.getAdresaEmail());
                intent.putExtra("Precizari",client.getPrecizari());
                startActivity(intent);
               // getActivity().finish();
            }
        });

        return v;
    }

}
