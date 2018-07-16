package com.ijustice.andreea.ijusticelicenta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ijustice.andreea.ijusticelicenta.models.AdapterConversatii;
import com.ijustice.andreea.ijusticelicenta.models.AdapterConversatiiClient;
import com.ijustice.andreea.ijusticelicenta.models.MessageDetails;
import com.ijustice.andreea.ijusticelicenta.models.UserAvocat;
import com.ijustice.andreea.ijusticelicenta.models.UserClient;

import java.util.ArrayList;

public class ListaEmailuriAvocatiActivity extends AppCompatActivity {

    ArrayList<UserAvocat> lista;
    AdapterConversatiiClient adapter;
    ListView lvConversatii;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth auth;
    DatabaseReference databaseReference;
    String userId;
    String cheie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_emailuri_avocati);
        lvConversatii =(ListView) findViewById(R.id.listviewemailuriavocati);
        lista =new ArrayList<UserAvocat>();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        FirebaseUser user=auth.getCurrentUser();
        userId=user.getUid();
        databaseReference.child("colaboratori").child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    cheie=ds.getKey();
                    databaseReference.child("users").child(cheie).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            final String cheieClient=dataSnapshot.getKey();
                            String nume=dataSnapshot.child("nume").getValue(String.class);
                            String prenume=dataSnapshot.child("prenume").getValue(String.class);
                            String telefon=dataSnapshot.child("numarTelefon").getValue(String.class);
                            String email=dataSnapshot.child("email").getValue(String.class);
                            int cazuriRezolvate=dataSnapshot.child("cazuriRezolvate").getValue(int.class);
                            int cazuriPierdute=dataSnapshot.child("cazuriPierdute").getValue(int.class);
                            String specializare=dataSnapshot.child("specializare").getValue(String.class);
                            String speializarePrecizared=dataSnapshot.child("specializarePrecizare").getValue(String.class);
                            String oras=dataSnapshot.child("oras").getValue(String.class);
                            String strada=dataSnapshot.child("strada").getValue(String.class);
                            int nr=dataSnapshot.child("nr").getValue(int.class);
                            UserAvocat avocat=new UserAvocat(nume,prenume,email,telefon,cazuriRezolvate,cazuriPierdute,
                                    oras,strada,nr,
                                    specializare,speializarePrecizared,cheie);


                            lista.add(avocat);


                            if(lista.size()!=0){
                                adapter=new AdapterConversatiiClient(getApplicationContext(), (ArrayList<UserAvocat>) lista);
                                lvConversatii.setAdapter(adapter);

                            }else{
                                Toast.makeText(getApplicationContext(),
                                        "Nu ti-a fost acceptată încă solicitarea de colaborare",Toast.LENGTH_SHORT).show();
                            }
                            lvConversatii.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    UserAvocat avocat=adapter.getItem(position);
                                    Intent intent=new Intent(getApplicationContext(),ChatClientActivity.class);
                                    intent.putExtra("cheie",avocat.getCheie());
                                    startActivity(intent);
                                }
                            });
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

}
