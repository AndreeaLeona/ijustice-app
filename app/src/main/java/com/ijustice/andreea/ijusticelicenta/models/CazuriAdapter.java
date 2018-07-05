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

public class CazuriAdapter extends ArrayAdapter<Caz> {
    private Context ccontext;
    private List<Caz> cazuri=new ArrayList<>();


    public CazuriAdapter(@NonNull Context context, @SuppressLint("SupportAnnotationUsage") @LayoutRes ArrayList<Caz> lista) {
        super(context,0,lista);
        ccontext=context;
        cazuri=lista;

    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(ccontext).inflate(R.layout.custom_clienti_item,parent,false);

        Caz cazCurent = cazuri.get(position);

        ImageView image = (ImageView)listItem.findViewById(R.id.img_caz);

        TextView obiect = (TextView) listItem.findViewById(R.id.item_lv_caz_texview_obiect);
        obiect.setText(cazCurent.getObiect() );

        TextView nume = (TextView) listItem.findViewById(R.id.item_lv_caz_texview_nume_solicitant);
        nume.setText(cazCurent.getNumeSolicitant());

        return listItem;
    }
}
