package com.ijustice.andreea.ijusticelicenta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ijustice.andreea.ijusticelicenta.models.CustomAdapterSpinner;
import com.ijustice.andreea.ijusticelicenta.models.Situatie;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ClientActivity extends AppCompatActivity {
    Spinner spinner;
    TextView tvDetalii;
    private FirebaseAuth auth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    ArrayList<Situatie> listaProbleme;
    CustomAdapterSpinner adapter;
    Situatie problemaCurenta;
    Button btnGaseste;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        spinner=(Spinner)findViewById(R.id.client_spinner);
        btnGaseste=(Button)findViewById(R.id.client_btn_gaseste_avocat);
        tvDetalii=(TextView)findViewById(R.id.client_tv_detalii_problema);
        auth=FirebaseAuth.getInstance();
        getSupportActionBar().setTitle("");

        FirebaseUser user=auth.getCurrentUser();
        if(auth.getCurrentUser()==null){
            finish();
            startActivity(new Intent(getApplicationContext(),LogInActivity.class));

        }
         firebaseDatabase = FirebaseDatabase.getInstance();
         databaseReference = firebaseDatabase.getReference("situatii");
         getSupportActionBar().setTitle("");
         listaProbleme=new ArrayList<Situatie>();


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    String situatie=ds.child("situatie").getValue(String.class);
                    String solutie=ds.child("solutie").getValue(String.class);
                    String specializare=ds.child("specializare").getValue(String.class);
                    Situatie situatie1=new Situatie(situatie,solutie,specializare);
                    listaProbleme.add(situatie1);

              }
                adapter=new CustomAdapterSpinner(getApplicationContext(),listaProbleme);
                spinner.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                problemaCurenta=listaProbleme.get(position);
                if(problemaCurenta.getSpecializare().equals("penal")){
                    tvDetalii.setText(problemaCurenta.getSolutie().toString());


                }else if(problemaCurenta.getSpecializare().equals("comercial")){
                    tvDetalii.setText(problemaCurenta.getSolutie().toString());

                }else if(problemaCurenta.getSpecializare().equals("administrativ")){
                    tvDetalii.setText(problemaCurenta.getSolutie());
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnGaseste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),GenerareAvocatiActivity.class);
                intent.putExtra("specializare",problemaCurenta.getSpecializare());
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.meniu_client,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id= item.getItemId();
        if(id==R.id.meniu_out){
            auth.signOut();
            finish();
            startActivity(new Intent(getApplicationContext(),LogInActivity.class));
        }else if(id==R.id.meniu_despre_tine){
            startActivity(new Intent(getApplicationContext(),ProfilClientActivity.class));
        }else if(id==R.id.meniu_chat){
            startActivity(new Intent(getApplicationContext(),ListaEmailuriAvocatiActivity.class));

        }
        return super.onOptionsItemSelected(item);
    }
}
