package com.ijustice.andreea.ijusticelicenta.models;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

@SuppressLint("ValidFragment")
public class DateDialog extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    EditText etDate;

    public DateDialog(View view){
        etDate=(EditText)view;

    }
    public Dialog onCreateDialog(Bundle savedInstanceState){
        final Calendar c=Calendar.getInstance();
        int an=c.get(Calendar.YEAR);
        int luna=c.get(Calendar.MONTH);
        int zi=c.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(),this,an,luna,zi);

    }



    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String data=dayOfMonth + "-" + (month+1) + "-" + year;
        etDate.setText(data);
    }
}
