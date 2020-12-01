package com.example.travelapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Flight {

    static ArrayList<Flight> result_collection;

    //String carrier;
    String departure_date;
    int price;

    public Flight(int price,String departure_date) {
        this.price = price;
        //this.carrier=carrier;
        this.departure_date=departure_date;
    }

    public static ArrayList<Flight> fromJarrToFlightArr(JSONArray jarr) throws JSONException {
        result_collection =new ArrayList<>();
        //todo: static method okay? what happen to next search?

        for(int i=0;i<jarr.length();i++){
            //todo: get all parameter ready to be constructed into a Flight object
            //get price info
            JSONObject individual_flight= jarr.getJSONObject(i);
            int price= individual_flight.getInt("MinPrice");

            JSONObject outbound_obj= individual_flight.getJSONObject("OutboundLeg");
            //get departure date
            String departure_date= outbound_obj.getString("DepartureDate");

            //get carrier and convert to corresponding airline name
            JSONArray carrier_arr= outbound_obj.getJSONArray("CarrierIds");
            int carrier_id=carrier_arr.getInt(0);

            //todo: what to do with matching ID?  hash_table?
            //String airline= carrierIdToName(carrier_id);

            //construct Flight object
            Flight single_flight_object= new Flight(price,departure_date);

            //add Flight object to result_collection
            result_collection.add(single_flight_object);
        }

        return result_collection;
    }

/*    private static String carrierIdToName(int carrier_id) {
        String airline_name;

        return airline_name;
    }*/


}
