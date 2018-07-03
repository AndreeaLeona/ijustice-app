package com.ijustice.andreea.ijusticelicenta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ijustice.andreea.ijusticelicenta.models.MessageDetails;

import java.util.ArrayList;

public class ListaEmailuriAvocatiActivity extends AppCompatActivity {

    ArrayList<String> listaNume;
    ArrayAdapter<String> adapter;
    ListView lvNume;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_emailuri_avocati);
        lvNume =(ListView) findViewById(R.id.listviewemailuriavocati);
        listaNume =new ArrayList<String>();
        adapter=new ArrayAdapter<String>(this,R.layout.item_lv_emailuri_avocati,R.id.tv_email_avocat_chat, listaNume);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("users");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                   String nume= ds.child("nume").getValue(String.class);
                   String prenume=ds.child("prenume").getValue(String.class);
                   listaNume.add(nume + " " + prenume);
                }
                lvNume.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        lvNume.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MessageDetails.chatWith= listaNume.get(position);
                startActivity(new Intent(getApplicationContext(),ChatActivity.class));
            }
        });
    }

}
