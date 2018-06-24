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

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ClientActivity extends AppCompatActivity {
    Spinner probleme;
    TextView tvDetalii;
    private FirebaseAuth auth;

    static List<String> listaProbleme;
    String problemaCurenta;
    Button btnGaseste;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
        probleme=(Spinner)findViewById(R.id.client_spinner);
        btnGaseste=(Button)findViewById(R.id.client_btn_gaseste_avocat);
        tvDetalii=(TextView)findViewById(R.id.client_tv_detalii_problema);
        auth=FirebaseAuth.getInstance();
        FirebaseUser user=auth.getCurrentUser();
        if(auth.getCurrentUser()==null){
            finish();
            startActivity(new Intent(getApplicationContext(),LogInActivity.class));

        }
        getSupportActionBar().setTitle("");
         listaProbleme=new ArrayList<String>();
        listaProbleme.add("Agresiune verbala");
        listaProbleme.add("Accident rutier");
        listaProbleme.add("Furt");
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,listaProbleme);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        probleme.setAdapter(adapter);
        probleme.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                problemaCurenta=listaProbleme.get(position);
                if(problemaCurenta.equals(listaProbleme.get(0))){
                    tvDetalii.setText("Pentru aceasta situatie este nevoie de un avocat care sa ofere servicii pe drpet PENAL");


                }else if(problemaCurenta.equals(listaProbleme.get(1))){
                    tvDetalii.setText("Pentru aceasta situatie este nevoie de un avocat care sa ofere servicii pe drpet CIVIL");

                }else{
                    tvDetalii.setText("Pentru aceasta situatie este nevoie de un avocat care sa ofere servicii pe drpet PENAL");

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
                intent.putExtra("problemaClient",problemaCurenta);
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
            startActivity(new Intent(getApplicationContext(),DespreClientActivity.class));
        }else if(id==R.id.meniu_chat){
            startActivity(new Intent(getApplicationContext(),ListaEmailuriAvocatiActivity.class));

        }
        return super.onOptionsItemSelected(item);
    }
}
