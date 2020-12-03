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
import com.example.travelapp.Classes.Task;
import com.example.travelapp.Fragments.FlightFragment;
import com.example.travelapp.R;
import com.example.travelapp.TaskDBHelper;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FlightResult extends AppCompatActivity {
    RecyclerView rvFlightResult;
    static List <Flight> result_flights;
    FlightAdapter.OneClickToAdd octa_instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_result);

        //populate flights result:
        result_flights=new ArrayList<>();

        ////create add button listener for each view holder => so selected flight can be added to calendar
        //in this case, the vhIndex (viewholder position is not necessary as long as we have the flight object)
        octa_instance=new FlightAdapter.OneClickToAdd() {
            @Override
            public void shortClicked(int vhIndex, Flight f) {
                Log.i("FlightResult", "provide_vhPos_flight_info: "+vhIndex+"   "+ f.getPrice());
                //create a new task (of taking a flight) to save in Calendar db
                Task add_flight_task;

                try{
                    String taskname= "From "+ f.getDepCity() +" to "+f.getArrCity()+ " with "+ f.getCarrier();
                    //id for task doesn't matter because will autopopulated by the db system.
                    add_flight_task=new Task(taskname,f.getDeparture_date(),-1);
                    Toast.makeText(FlightResult.this, add_flight_task.getTaskName(), Toast.LENGTH_SHORT).show();

                }catch (Exception e){
                    add_flight_task=new Task("Task for flight error",f.getDeparture_date(),-1);
                }

                boolean success= MainActivity.db_helper_instance.addTask(add_flight_task);
                MainActivity.task_collection.add(add_flight_task);
            }
        };


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
        FlightAdapter fa_instance=new FlightAdapter(result_flights,this,octa_instance);

        rvFlightResult=findViewById(R.id.rvFlightResult);
        rvFlightResult.setAdapter(fa_instance);
        rvFlightResult.setLayoutManager(new LinearLayoutManager(this));

    }
}