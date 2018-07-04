package com.ijustice.andreea.ijusticelicenta.models;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ijustice.andreea.ijusticelicenta.R;

import java.util.ArrayList;
import java.util.List;

public class ClientiAdapter extends ArrayAdapter<Client> {

    private Context ccontext;
    private List<Client> clienti=new ArrayList<>();


    public ClientiAdapter(@NonNull Context context, @SuppressLint("SupportAnnotationUsage") @LayoutRes ArrayList<Client> lista) {
        super(context,0,lista);
        ccontext=context;
        clienti=lista;

    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(ccontext).inflate(R.layout.custom_clienti_item,parent,false);

        Client clientCurent = clienti.get(position);

        ImageView image = (ImageView)listItem.findViewById(R.id.img_clienti);

        TextView nume = (TextView) listItem.findViewById(R.id.item_lv_clienti_texview_nume);
        nume.setText(clientCurent.getNume() + " " + clientCurent.getPrenume() );

        TextView oras = (TextView) listItem.findViewById(R.id.item_lv_clienti_texview_oras);
        oras.setText(clientCurent.getOras());

        return listItem;
    }
}
