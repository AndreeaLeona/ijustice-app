package com.ijustice.andreea.ijusticelicenta.models;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ijustice.andreea.ijusticelicenta.R;

import java.util.ArrayList;

import static com.ijustice.andreea.ijusticelicenta.R.layout.spinner_item;

public class CustomAdapterSpinner extends ArrayAdapter<Situatie> {
    Context context;
    ArrayList<Situatie> situatii;

    public CustomAdapterSpinner(Context context, ArrayList<Situatie> situatii) {
        super(context, R.layout.spinner_item,situatii);
        this.context=context;
        this.situatii=situatii;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row=inflater.inflate(spinner_item,null);
            TextView tvSituatie=(TextView)row.findViewById(R.id.item_spinner_tv);
            tvSituatie.setText(situatii.get(position).getSituatie().toString());
        return row;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row=inflater.inflate(spinner_item,null);
        TextView tvSituatie=(TextView)row.findViewById(R.id.item_spinner_tv);
        tvSituatie.setText(situatii.get(position).getSituatie().toString());
        return row;
    }
}
