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
import com.ijustice.andreea.ijusticelicenta.models.Caz;
import com.ijustice.andreea.ijusticelicenta.models.CazuriAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CazuriFragment extends Fragment {
    Button btnAdaugaCaz;
    ListView listView;
    FirebaseDatabase database;
    DatabaseReference reference;
    List<Caz> listaCazuri;
    CazuriAdapter adapter;
    FirebaseAuth auth;
    String cheie;
    String userId;
    TextView tvMesaj;


    public CazuriFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_cazuri2, container, false);


        btnAdaugaCaz=v.findViewById(R.id.cazuri_btn_adauga);
        tvMesaj=v.findViewById(R.id.vizualizarecazuri_mesaj);
        listView=v.findViewById(R.id.lv_vizualizare_cazuri);
        listaCazuri =new ArrayList<>();
        database=FirebaseDatabase.getInstance();
        auth=FirebaseAuth.getInstance();
        FirebaseUser user=auth.getCurrentUser();
        userId=user.getUid();
        reference=database.getReference("cazuri").child(userId);


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listaCazuri.clear();
                listView.setAdapter(null);
                for(DataSnapshot ds:dataSnapshot.getChildren()) {
                    cheie=ds.getKey();
                    String obiect = ds.child("obiect").getValue(String.class);
                    int numar = ds.child("numar").getValue(int.class);
                    String numeSolicitant = ds.child("numeSolicitant").getValue(String.class);
                    String data = ds.child("data").getValue(String.class);
                    String descriere = ds.child("descriere").getValue(String.class);


                    Caz caz=new Caz(obiect,numar,numeSolicitant,data,descriere,cheie);

                    listaCazuri.add(caz);


                }
                if(listaCazuri.size()!=0 && getActivity()!=null){

                    adapter = new CazuriAdapter(getContext(), (ArrayList<Caz>) listaCazuri);
                    listView.setAdapter(adapter);
                    tvMesaj.setText("");

                }else {
                    tvMesaj.setText("Nu ai adaugat niciun caz!");
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        btnAdaugaCaz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent=new Intent(getContext(),AdaugaCazActivity.class);
                startActivity(myIntent);
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Caz caz=adapter.getItem(position);
                Intent intent=new Intent(getActivity(),DetaliiCazActivity.class);
                intent.putExtra("cheie",caz.getCheie());
                intent.putExtra("obiect", caz.getObiect());
                intent.putExtra("numar",caz.getNrOrdine());
                intent.putExtra("numeSolicitant",caz.getNumeSolicitant());
                intent.putExtra("data",caz.getData());
                intent.putExtra("descriere",caz.getDescriere());

                startActivity(intent);
            }
        });

        return v;
    }

}
