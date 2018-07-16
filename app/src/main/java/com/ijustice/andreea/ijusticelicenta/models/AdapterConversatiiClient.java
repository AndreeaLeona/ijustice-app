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

public class AdapterConversatiiClient extends ArrayAdapter<UserAvocat> {
    private Context ccontext;
    private List<UserAvocat> conversatii=new ArrayList<>();


    public AdapterConversatiiClient(@NonNull Context context, @SuppressLint("SupportAnnotationUsage") @LayoutRes ArrayList<UserAvocat> lista) {
        super(context,0,lista);
        ccontext=context;
        conversatii=lista;

    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(ccontext).inflate(R.layout.custom_conv_client,parent,false);

        UserAvocat conversatie = conversatii.get(position);

        TextView nume = (TextView) listItem.findViewById(R.id.textView_nume_avocat_conv);
        nume.setText(conversatie.getNume());

        return listItem;
    }

}
