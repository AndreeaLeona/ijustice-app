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
import com.ijustice.andreea.ijusticelicenta.models.AdapterSolicitari;
import com.ijustice.andreea.ijusticelicenta.models.Caz;
import com.ijustice.andreea.ijusticelicenta.models.CazuriAdapter;
import com.ijustice.andreea.ijusticelicenta.models.UserClient;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class SolicitariFragment extends Fragment {

    ListView lvSolicitari;
    FirebaseAuth auth;
    FirebaseAuth.AuthStateListener authStateListener;
    FirebaseDatabase database;
    DatabaseReference reference;
    DatabaseReference reference2;
    ArrayList<UserClient> listaSolicitariClienti;
    AdapterSolicitari adapter;
    TextView tvMesaj;
    String userId;




    public SolicitariFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_solicitari, container, false);
        database=FirebaseDatabase.getInstance();
        auth=FirebaseAuth.getInstance();
        FirebaseUser user=auth.getCurrentUser();
        userId=user.getUid();
        reference=database.getReference("solicitari").child(userId);
        listaSolicitariClienti=new ArrayList<UserClient>();
        reference2=database.getReference("users_clienti");
        tvMesaj=v.findViewById(R.id.tv_mesaj_solicitari);


        lvSolicitari=v.findViewById(R.id.lv_solicitari);
        lista_de_solicitari();



        return v;
    }
    public void lista_de_solicitari(){
         reference.addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(DataSnapshot dataSnapshot) {
                 for(DataSnapshot ds :dataSnapshot.getChildren()){
                     String cheie= ds.getKey();
                     reference2.child(cheie).addValueEventListener(new ValueEventListener() {
                         @Override
                         public void onDataChange(DataSnapshot dataSnapshot) {
                             listaSolicitariClienti.clear();
                             lvSolicitari.setAdapter(null);
                                final String nume=dataSnapshot.child("nume").getValue(String.class);
                                final String adresa=dataSnapshot.child("adresa").getValue(String.class);
                                final String telefon=dataSnapshot.child("telefon").getValue(String.class);
                                 final String email=dataSnapshot.child("email").getValue(String.class);


                                 UserClient client=new UserClient(nume,adresa,telefon,email);
                                 listaSolicitariClienti.add(client);

                             if(listaSolicitariClienti.size()!=0 && getActivity()!=null){

                                 adapter = new AdapterSolicitari(getContext(), (ArrayList<UserClient>) listaSolicitariClienti);
                                 lvSolicitari.setAdapter(adapter);
                                 tvMesaj.setText("");

                             }else {
                                 tvMesaj.setText("Nu ai adaugat niciun caz!");
                             }
                             lvSolicitari.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                 @Override
                                 public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                     Intent intent=new Intent(getActivity(),VizualizareSolicitareClientActivity.class);
                                     intent.putExtra("Nume",nume);
                                     intent.putExtra("Adresa",adresa);
                                     intent.putExtra("Telefon",telefon);
                                     intent.putExtra("Email",email);
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
