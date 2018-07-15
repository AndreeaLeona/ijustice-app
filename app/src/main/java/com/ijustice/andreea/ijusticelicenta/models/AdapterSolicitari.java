package com.ijustice.andreea.ijusticelicenta.models;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ijustice.andreea.ijusticelicenta.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterSolicitari extends ArrayAdapter<UserClient>{
    private Context ccontext;
    private List<UserClient> solicitari=new ArrayList<>();


    public AdapterSolicitari(@NonNull Context context, @SuppressLint("SupportAnnotationUsage") @LayoutRes ArrayList<UserClient> lista) {
        super(context,0,lista);
        ccontext=context;
        solicitari=lista;

    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(ccontext).inflate(R.layout.item_solicitari,parent,false);

        UserClient solicitareCurenta = solicitari.get(position);

        TextView nume = (TextView) listItem.findViewById(R.id.tv_nume_solicitant_solicitare);
        nume.setText(solicitareCurenta.getNume() + " " + "dorește să colaboreze cu tine în vederea rezolvării unei probleme");

        return listItem;
    }

}
