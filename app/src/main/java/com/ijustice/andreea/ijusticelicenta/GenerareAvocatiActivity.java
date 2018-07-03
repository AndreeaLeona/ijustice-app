package com.ijustice.andreea.ijusticelicenta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ijustice.andreea.ijusticelicenta.models.UserAvocat;

import java.util.ArrayList;

public class GenerareAvocatiActivity extends AppCompatActivity {
    TextView tvProblema;
    ArrayList<String> listaProbleme;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ListView lvAvocati;
    ArrayList<UserAvocat> listaAvocati;
    ArrayAdapter<UserAvocat> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generare_avocati);
        firebaseDatabase = FirebaseDatabase.getInstance();
        listaAvocati=new ArrayList<UserAvocat>();
        lvAvocati=(ListView)findViewById(R.id.generare_lv_avocati);
        adapter=new ArrayAdapter<UserAvocat>(this,R.layout.adapater_listview_avocati,R.id.adapter_tv_avocati,listaAvocati);
        databaseReference = firebaseDatabase.getReference();
        Intent intent=getIntent();
        String problema=intent.getStringExtra("problemaClient");
        tvProblema=(TextView)findViewById(R.id.generare_tv_problema);
        tvProblema.setText(problema);
        listaProbleme=new ArrayList<String>();
        listaProbleme.add("Agresiune verbala");
        listaProbleme.add("Accident rutier");
        listaProbleme.add("Furt");
        if(problema.equals(listaProbleme.get(0))){
            databaseReference.child("users").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                    for (DataSnapshot ds : children) {
                        if(ds.child("specializare").getValue(String.class).equals("Penal")){
                            String nume=ds.child("nume").getValue(String.class);
                            String prenume=ds.child("prenume").getValue(String.class);
                            String email=ds.child("email").getValue(String.class);
                            String nrTel=ds.child("numarTelefon").getValue(String.class);
                            int cazuriPierdute=ds.child("cazuriPierdute").getValue(int.class);
                            int cazuriRezolvate=ds.child("cazuriRezolvate").getValue(int.class);
                            String oras=ds.child("oras").getValue(String.class);
                            String strada=ds.child("strada").getValue(String.class);
                            int nr=ds.child("nr").getValue(int.class);
                            String specializare=ds.child("specializare").getValue(String.class);
                            UserAvocat avocat=new UserAvocat(nume,prenume,email,nrTel,cazuriRezolvate,cazuriPierdute,oras,strada,nr,specializare);
                            listaAvocati.add(avocat);

                        }
                    }
                    lvAvocati.setAdapter(adapter);
                }


                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }
    }
}
