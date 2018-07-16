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

public class AdapterConversatii extends ArrayAdapter<UserClient> {
    private Context ccontext;
    private List<UserClient> conversatii=new ArrayList<>();


    public AdapterConversatii(@NonNull Context context, @SuppressLint("SupportAnnotationUsage") @LayoutRes ArrayList<UserClient> lista) {
        super(context,0,lista);
        ccontext=context;
        conversatii=lista;

    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(ccontext).inflate(R.layout.custom_conversatii_item,parent,false);

        UserClient conversatie = conversatii.get(position);

        TextView nume = (TextView) listItem.findViewById(R.id.tv_colaborator_conversatie);
        nume.setText(conversatie.getNume());

        return listItem;
    }


}
