package com.ijustice.andreea.ijusticelicenta;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class GenerareAvocatiActivity extends AppCompatActivity {
    TextView tvProblema;
    ArrayList<String> listaProbleme;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generare_avocati);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("servicii");
        Intent intent=getIntent();
        String problema=intent.getStringExtra("problemaClient");
        tvProblema=(TextView)findViewById(R.id.generare_tv_problema);
        tvProblema.setText(problema);
        listaProbleme=new ArrayList<String>();
        listaProbleme.add("Agresiune verbala");
        listaProbleme.add("Accident rutier");
        listaProbleme.add("Furt");
        if(problema.equals(listaProbleme.get(0))){
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {



                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }
    }
}
