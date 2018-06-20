package com.ijustice.andreea.ijusticelicenta.models;

import android.content.Context;
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

public class ServiceAdapter extends ArrayAdapter<Serviciu> {
    public ServiceAdapter(Context context, ArrayList<Serviciu> listaServicii){
        super(context,0,listaServicii);
    }

    public ServiceAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position,convertView,parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position,convertView,parent);

    }
    private View initView(int position, View convertView, ViewGroup parent){
        if(convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.serviciu_spinner_row,parent,false);
        }
        ImageView image=convertView.findViewById(R.id.spinner_layout_img);
        TextView textView=convertView.findViewById(R.id.spinner_layout_tv_serviciu);
         Serviciu serviuciuCurent=getItem(position);
         if(serviuciuCurent!=null){
             image.setImageResource(serviuciuCurent.getImagine());
             textView.setText(serviuciuCurent.getDenumire());


         }
         return  convertView;

    }


}
