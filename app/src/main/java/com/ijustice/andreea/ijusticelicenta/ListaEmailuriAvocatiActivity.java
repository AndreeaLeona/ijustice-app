package com.ijustice.andreea.ijusticelicenta;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListaEmailuriAvocatiActivity extends AppCompatActivity {

    ArrayList<String> listaEmailuri;
    ArrayAdapter<String> adapter;
    ListView lvEmailuri;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_emailuri_avocati);
        lvEmailuri=(ListView) findViewById(R.id.listviewemailuriavocati);
        listaEmailuri=new ArrayList<String>();
        adapter=new ArrayAdapter<String>(this,R.layout.item_lv_emailuri_avocati,R.id.tv_email_avocat_chat,listaEmailuri);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("users");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                   String email= ds.child("email").getValue(String.class);
                   listaEmailuri.add(email);
                }
                lvEmailuri.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
