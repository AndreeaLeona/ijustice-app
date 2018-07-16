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
import android.widget.TextView;

import com.ijustice.andreea.ijusticelicenta.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterMesaje extends ArrayAdapter<Mesaj> {
    private Context ccontext;
    private List<Mesaj> mesaje=new ArrayList<>();


    public AdapterMesaje(@NonNull Context context, @SuppressLint("SupportAnnotationUsage") @LayoutRes ArrayList<Mesaj> lista) {
        super(context,0,lista);
        ccontext=context;
        mesaje=lista;

    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(ccontext).inflate(R.layout.custom_message_layout,parent,false);

        Mesaj mesaj= mesaje.get(position);

        TextView textMesaj = (TextView) listItem.findViewById(R.id.textView_mesaj_chat);
        TextView nume=(TextView) listItem.findViewById(R.id.textView_nume_utilizator_chat);
        textMesaj.setText(mesaj.getText());
        nume.setText(mesaj.getNumeTrimitator());



        return listItem;
    }


}
