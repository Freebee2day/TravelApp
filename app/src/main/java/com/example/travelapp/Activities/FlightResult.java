package com.example.travelapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import com.example.travelapp.Adapter.FlightAdapter;
import com.example.travelapp.Classes.Flight;
import com.example.travelapp.Fragments.FlightFragment;
import com.example.travelapp.R;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FlightResult extends AppCompatActivity {
    RecyclerView rvFlightResult;
    static List <Flight> result_flights;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_result);


        //populate flights result:
        result_flights=new ArrayList<>();


        String query_URL_from_FlightFragment= getIntent().getStringExtra(FlightFragment.QUERY_URL);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url(query_URL_from_FlightFragment)
                        .get()
                        .addHeader("x-rapidapi-key", "4770880e42msha6850af97acf908p19dc77jsnecae3a323fc1")
                        .addHeader("x-rapidapi-host", "skyscanner-skyscanner-flight-search-v1.p.rapidapi.com")
                        .build();

                try  {

                    Response response = client.newCall(request).execute();
                    String jsonData= response.body().string();
                    JSONObject local_j= new JSONObject(jsonData);
                    if(local_j!=null) {
                        //clear result collection in FlightResult class
                        result_flights.clear();
                        FlightResult.result_flights.addAll(Flight.fromJobjToFlightArr(local_j));
                        //clear result collection in Flight class (clean for future use)
                        Flight.result_collection.clear();

                    }else{
                        Log.i("FlightResult", "run: no JsonObject fetched or no flight available");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("FlightResult", "run: fail to get object", e);
                }


        Log.i("FlightResult", "run: "+result_flights.size());

        if(result_flights.size()==0){
            Toast.makeText(this, "No flight meeting requirement available", Toast.LENGTH_LONG).show();
        }
        FlightAdapter fa_instance=new FlightAdapter(result_flights,this);

        rvFlightResult=findViewById(R.id.rvFlightResult);
        rvFlightResult.setAdapter(fa_instance);
        rvFlightResult.setLayoutManager(new LinearLayoutManager(this));

    }
}