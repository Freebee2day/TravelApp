package com.example.travelapp;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

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



        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url("https://skyscanner-skyscanner-flight-search-v1.p.rapidapi.com/apiservices/browsedates/v1.0/US/USD/en-US/SFO-sky/LAX-sky/2021-03-01?inboundpartialdate=2021-03-12")
                        .get()
                        .addHeader("x-rapidapi-key", "4770880e42msha6850af97acf908p19dc77jsnecae3a323fc1")
                        .addHeader("x-rapidapi-host", "skyscanner-skyscanner-flight-search-v1.p.rapidapi.com")
                        .build();

                try  {

                    Response response = client.newCall(request).execute();
                    String jsonData= response.body().string();
                    JSONObject local_j= new JSONObject(jsonData);
                    Log.i("FlightFragment", "working well!!!!!!");
                    JSONArray arr_quote= local_j.getJSONArray("Quotes");
                    //todo: need to check if array is empty.
                    JSONObject object_a = arr_quote.getJSONObject(0);
                    int min_price= object_a.getInt("MinPrice");

                    JSONObject outbound_obj= object_a.getJSONObject("OutboundLeg");

                    String depart_date= outbound_obj.getString("DepartureDate");
                    System.out.println(depart_date);
                    JSONArray carrier_arr= outbound_obj.getJSONArray("CarrierIds");
                    System.out.println(carrier_arr.get(0));
                    System.out.println(min_price);

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("FlightFragment", "run: fail to get object", e);
                }
            }
        });

        thread.start();



/*        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        //need to change the request URL
                        .url("https://skyscanner-skyscanner-flight-search-v1.p.rapidapi.com/apiservices/browsequotes/v1.0/US/USD/en-US/SFO-sky/JFK-sky/2021-01-08")
                        .get()
                        .addHeader("x-rapidapi-key", "4770880e42msha6850af97acf908p19dc77jsnecae3a323fc1")
                        .addHeader("x-rapidapi-host", "skyscanner-skyscanner-flight-search-v1.p.rapidapi.com")
                        .build();
                try  {

                    Response response = client.newCall(request).execute();
                    String jsonData= response.body().string();
                    JSONObject local_j= new JSONObject(jsonData);
                    JSONArray arr_quote= local_j.getJSONArray("Quotes");
                    JSONObject object_a = arr_quote.getJSONObject(0);
                    int min_price= object_a.getInt("MinPrice");
                    System.out.println(min_price);

                } catch (Exception e) {
                    Log.e("FlightFragment", "run: pheobe is dumb", e);
                }
            }
        });

        thread.start();*/
    }


}