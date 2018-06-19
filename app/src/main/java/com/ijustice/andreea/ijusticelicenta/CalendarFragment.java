package com.ijustice.andreea.ijusticelicenta;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;


/**
 * A simple {@link Fragment} subclass.
 */
public class CalendarFragment extends Fragment {
    CalendarView calendarView;


    public CalendarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_calendar, container, false);

        calendarView= v.findViewById(R.id.calendar_calendarview);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String date= dayOfMonth + "/" + month + "/" + year;
                Intent intent=new Intent(getActivity(),AdaugareEvenimentActivity.class);
                intent.putExtra("data",date);
                startActivity(intent);
            }
        });
        return v;
    }

}
