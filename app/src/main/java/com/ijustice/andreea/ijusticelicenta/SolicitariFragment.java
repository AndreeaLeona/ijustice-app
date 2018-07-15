package com.ijustice.andreea.ijusticelicenta;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class SolicitariFragment extends Fragment {

    ListView lvSolicitari;
    //Firebase_method firebase_method;
    FirebaseAuth auth;
    FirebaseAuth.AuthStateListener authStateListener;
    FirebaseDatabase database;
    DatabaseReference reference;
    DatabaseReference reference2;
    String userId;




    public SolicitariFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_solicitari, container, false);
        database=FirebaseDatabase.getInstance();
        reference=database.getReference("solicitari").child(userId);
        reference2=database.getReference("users");
        FirebaseUser user=auth.getCurrentUser();
        userId=user.getUid();

        lvSolicitari=v.findViewById(R.id.lv_solicitari);
        lista_de_solicitari();


        return v;
    }
    public void lista_de_solicitari(){



    }

}
