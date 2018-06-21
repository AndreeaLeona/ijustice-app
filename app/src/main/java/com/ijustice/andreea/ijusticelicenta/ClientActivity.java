package com.ijustice.andreea.ijusticelicenta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ClientActivity extends AppCompatActivity {
    Spinner probleme;
    TextView tvDetalii;
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
        getSupportActionBar().setTitle("Justice");
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
}
