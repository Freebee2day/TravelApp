package com.example.travelapp;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.Date;

public class FlightFragment extends Fragment {

    //todo: no need to declare if no activity done on the item?
    //TextView tvGreeting;
    //TextView tvDepart;
    //TextView tvArrive;
    DatePickerDialog d_selector;
    DatePickerDialog a_selector;
    EditText etDepCity;
    EditText etDepDate;
    EditText etArrCity;
    EditText etArrDate;
    Button btnSearch;


  public FlightFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_flight, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etDepDate= view.findViewById(R.id.etDepDate);
        etDepCity=view.findViewById(R.id.etDepCity);
        etArrCity=view.findViewById(R.id.etArrCity);
        etArrDate=view.findViewById(R.id.etArrDate);
        btnSearch=view.findViewById(R.id.btnSearch);

        etDepDate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                final Calendar c1=Calendar.getInstance();
                int d= c1.get(Calendar.DAY_OF_MONTH);
                int m=c1.get(Calendar.MONTH);
                int y=c1.get(Calendar.YEAR);
                d_selector=new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        etDepDate.setText( (month + 1)+ "/" + dayOfMonth +"/"+ year);
                    }
                },y,m,d);
                d_selector.show();
            }
        });

        etArrDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c1=Calendar.getInstance();
                int d= c1.get(Calendar.DAY_OF_MONTH);
                int m=c1.get(Calendar.MONTH);
                int y=c1.get(Calendar.YEAR);
                a_selector=new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        etArrDate.setText( (month + 1)+ "/" + dayOfMonth +"/"+ year);
                    }
                },y,m,d);
                a_selector.show();

            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "About to search flight!!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}