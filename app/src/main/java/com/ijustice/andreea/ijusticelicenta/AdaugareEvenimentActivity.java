package com.ijustice.andreea.ijusticelicenta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ijustice.andreea.ijusticelicenta.models.NotaAvocat;

import java.util.ArrayList;

public class AdaugareEvenimentActivity extends AppCompatActivity {
  Button btnAdaugaEveniment;
  ListView listView;
  FirebaseDatabase database;
  DatabaseReference reference;
  static ArrayList<NotaAvocat> lista;
  ArrayAdapter<NotaAvocat> adapter;
  FirebaseAuth auth;
  String userId;
  TextView tvMesaj;
    public boolean verificaList(ArrayList<NotaAvocat> lista,int id){
        boolean ok=true;
        for(NotaAvocat n:lista){
            if(n.getId()==id){
                ok=false;
            }
        }
        return ok;


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adaugare_eveniment);
        Intent intent=getIntent();
        final String d=intent.getStringExtra("data");
        btnAdaugaEveniment=(Button)findViewById(R.id.adgeveniment_btn_adauga);
        tvMesaj=(TextView)findViewById(R.id.adgeveniment_tv_mesaj) ;
        listView=(ListView)findViewById(R.id.adgeveniment_lv_evenimente) ;
        database=FirebaseDatabase.getInstance();
        auth=FirebaseAuth.getInstance();
        FirebaseUser user=auth.getCurrentUser();
        userId=user.getUid();
        reference=database.getReference("notite").child(userId);
        lista=new ArrayList<>();
        adapter=new ArrayAdapter<NotaAvocat>(this,R.layout.adapter_listview_notite,R.id.layout_tv_notita,lista);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                lista.clear();
                listView.setAdapter(null);
                for(DataSnapshot ds:dataSnapshot.getChildren()) {
                    String titlu = ds.child("titlu").getValue(String.class);
                    String data = ds.child("data").getValue(String.class);
                    String detalii = ds.child("detalii").getValue(String.class);
                    NotaAvocat notita=new NotaAvocat(titlu,data,detalii);

                    if(data.equals(d) && verificaList(lista,notita.getId())==true){

                        lista.add(notita);
                    }

                }
                if(lista.size()!=0){

                listView.setAdapter(adapter);
                tvMesaj.setText("");

            }else {
                    tvMesaj.setText("Nu exista notite adaugate pentru aceasta data!");
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        btnAdaugaEveniment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent i= new Intent(getApplicationContext(),DetaliiEvenimentActivity.class);
               i.putExtra("dataCalendar",d);
                startActivity(i);
            }
        });
    }

}
