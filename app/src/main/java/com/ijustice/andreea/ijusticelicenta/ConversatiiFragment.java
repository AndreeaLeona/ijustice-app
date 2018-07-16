package com.ijustice.andreea.ijusticelicenta;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ijustice.andreea.ijusticelicenta.models.AdapterConversatii;
import com.ijustice.andreea.ijusticelicenta.models.Client;
import com.ijustice.andreea.ijusticelicenta.models.UserClient;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ConversatiiFragment extends Fragment {
    ListView lvConversatii;
    TextView tvMesaj;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth auth;
    AdapterConversatii adapter;
    List<UserClient> lista;
    String cheie;
    private String userId;


    public ConversatiiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_conversatii, container, false);
        lvConversatii=v.findViewById(R.id.lv_conversatii);
        tvMesaj=v.findViewById(R.id.tv_mesaj_conversatii);
        auth=FirebaseAuth.getInstance();
        lista=new ArrayList<UserClient>();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
        FirebaseUser user=auth.getCurrentUser();
        userId=user.getUid();
        databaseReference.child("colaboratori").child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    cheie=ds.getKey();
                    databaseReference.child("users_clienti").child(cheie).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                                final String cheieClient=dataSnapshot.getKey();
                                String nume=dataSnapshot.child("nume").getValue(String.class);
                                String adresa=dataSnapshot.child("adresa").getValue(String.class);
                                String telefon=dataSnapshot.child("telefon").getValue(String.class);
                                String email=dataSnapshot.child("email").getValue(String.class);

                                UserClient client=new UserClient(nume,adresa,telefon,email,cheieClient);
                                    lista.add(client);


                            if(lista.size()!=0){
                                adapter=new AdapterConversatii(getContext(), (ArrayList<UserClient>) lista);
                                lvConversatii.setAdapter(adapter);
                                tvMesaj.setText("");

                            }else{
                                tvMesaj.setText("Nu aveți colaboratori pentru a putea începe o conversație");
                            }
                            lvConversatii.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    UserClient client=adapter.getItem(position);
                                    Intent intent=new Intent(getContext(),ChatActivity.class);
                                    intent.putExtra("cheie",client.getCheie());
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

        return v;
    }

}
