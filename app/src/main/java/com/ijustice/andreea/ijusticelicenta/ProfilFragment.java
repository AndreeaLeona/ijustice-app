package com.ijustice.andreea.ijusticelicenta;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ijustice.andreea.ijusticelicenta.models.UserAvocat;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfilFragment extends Fragment {
    TextView tvNume, tvPrenume,tvEmail,tvTelefon,tvRezolvate,tvPierdute,tvSpecializare,tvOras,tvStrada,tvNr;
    FirebaseAuth auth;
    ImageButton btnEditeaza;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private String userId;


    public ProfilFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_profil, container, false);
        btnEditeaza=v.findViewById(R.id.img_btn_profil);
        tvNume=v.findViewById(R.id.tv_profil_nume);
        tvPrenume=v.findViewById(R.id.tv_profil_prenume);
        tvEmail=v.findViewById(R.id.tv_profil_email);
        tvTelefon=v.findViewById(R.id.tv_profil_telefon);
        tvRezolvate=v.findViewById(R.id.tv_profil_rezolvate);
        tvPierdute=v.findViewById(R.id.tv_profil_pierdute);
        tvSpecializare=v.findViewById(R.id.tv_profil_specializare);
        tvOras=v.findViewById(R.id.tv_profil_oras);
        tvStrada=v.findViewById(R.id.tv_profil_strada);
        tvNr=v.findViewById(R.id.tv_profil_nrcasa);
        auth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("users");
        FirebaseUser user = auth.getCurrentUser();
        userId = user.getUid();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String nume = dataSnapshot.child(userId).child("nume").getValue(String.class);
                String prenume = dataSnapshot.child(userId).child("prenume").getValue(String.class);
                String email = dataSnapshot.child(userId).child("email").getValue(String.class);
                String nrTelefon = dataSnapshot.child(userId).child("numarTelefon").getValue(String.class);
                int cazuriRezolvate = dataSnapshot.child(userId).child("cazuriRezolvate").getValue(int.class);
                int cazuriPierdute = dataSnapshot.child(userId).child("cazuriPierdute").getValue(int.class);
                String oras = dataSnapshot.child(userId).child("oras").getValue(String.class);
                String strada = dataSnapshot.child(userId).child("strada").getValue(String.class);
                int nr = dataSnapshot.child(userId).child("nr").getValue(int.class);
                String specializare=dataSnapshot.child(userId).child("specializare").getValue(String.class);
                UserAvocat u = new UserAvocat(nume, prenume, email, nrTelefon, cazuriRezolvate, cazuriPierdute, oras, strada, nr,specializare);




                tvNume.setText(u.getNume().toString());
                tvPrenume.setText(u.getPrenume().toString());
                tvEmail.setText(u.getEmail().toString());
                tvTelefon.setText(u.getNumarTelefon().toString());
                tvRezolvate.setText(String.valueOf(u.getCazuriRezolvate()));
                tvSpecializare.setText(u.getSpecializare());
                tvPierdute.setText(String.valueOf(u.getCazuriPierdute()));
                tvOras.setText(u.getOras().toString());
                tvStrada.setText(u.getStrada().toString());
                tvNr.setText(String.valueOf(u.getNr()));

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        btnEditeaza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),EditareInformatiiActivity.class));
            }
        });


        return v;
    }

}
