package com.ijustice.andreea.ijusticelicenta;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ijustice.andreea.ijusticelicenta.models.Client;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ClientiFragment extends Fragment {

    Button btnAdauga;
    ListView listView;
    FirebaseDatabase database;
    DatabaseReference reference;
    static ArrayList<Client> listaClient;
    ArrayAdapter<Client> adapter;
    FirebaseAuth auth;
    String userId;
    TextView tvMesaj;


    public ClientiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_clienti, container, false);
        btnAdauga=v.findViewById(R.id.clienti_btn_adauga);
        btnAdauga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),AdaugaClientActivity.class);
                startActivity(intent);
            }
        });

        return v;
    }

}
