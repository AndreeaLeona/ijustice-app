package com.ijustice.andreea.ijusticelicenta;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ijustice.andreea.ijusticelicenta.models.ServiceAdapter;
import com.ijustice.andreea.ijusticelicenta.models.Serviciu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class AdaugaServiciiFragment extends Fragment {
    ArrayList<Serviciu> lista;
    ServiceAdapter adapter;
    FirebaseAuth auth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private String userId;


    public AdaugaServiciiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_adauga_servicii, container, false);
        auth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
        FirebaseUser user=auth.getCurrentUser();
        userId=user.getUid();
        initList();
        Spinner spinner=v.findViewById(R.id.spinner2);
        adapter=new ServiceAdapter(getContext(),lista);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Serviciu serviciu=(Serviciu)parent.getItemAtPosition(position);
                String numeServiciuSelectat=serviciu.getDenumire();
                String idServiuciu=String.valueOf(serviciu.getId());
                Map servicii = new HashMap<>();
                servicii.put("nume", numeServiciuSelectat);
                try {

                    databaseReference.child("servicii").child(userId).child("nume").setValue(numeServiciuSelectat);
                    Toast.makeText(getContext(), "Serviciul tau a fost salvat!!", Toast.LENGTH_SHORT).show();

                } catch (Exception ex) {
                    Toast.makeText(getContext(), "Datele tale nu au fost salvate, te rog sa verifici conexiunea la internet", Toast.LENGTH_SHORT).show();


                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return v;
    }
    public void initList(){
        lista=new ArrayList<Serviciu>();
        lista.add(new Serviciu("Penal",R.drawable.simbol));
        lista.add(new Serviciu("Civil",R.drawable.simbol));
        lista.add(new Serviciu("Constitutional",R.drawable.simbol));
        lista.add(new Serviciu("Public",R.drawable.simbol));

    }

}
