package com.ijustice.andreea.ijusticelicenta;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class ClientiFragment extends Fragment {
    Button btnAdauga;


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
