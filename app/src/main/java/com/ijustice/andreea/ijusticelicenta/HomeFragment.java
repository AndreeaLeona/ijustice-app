package com.ijustice.andreea.ijusticelicenta;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

TextView tvEmail;
FirebaseAuth auth;
    String userEmail;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View v=inflater.inflate(R.layout.fragment_home, container, false);
        tvEmail=v.findViewById(R.id.home_tv_email);
        auth=FirebaseAuth.getInstance();
        FirebaseUser user=auth.getCurrentUser();
        userEmail=user.getEmail();
        tvEmail.setText(userEmail);



        return v;
    }

}
