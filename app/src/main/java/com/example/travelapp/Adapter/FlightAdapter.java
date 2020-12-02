package com.example.travelapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelapp.Classes.Flight;
import com.example.travelapp.R;

import java.util.List;

public class FlightAdapter extends RecyclerView.Adapter<FlightAdapter.ViewHolder> {

    List<Flight> flight_collection;
    Context context;

    public FlightAdapter(List<Flight> flight_collection, Context context) {
        this.flight_collection = flight_collection;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View flight_view= LayoutInflater.from(context).inflate(R.layout.flight_item,parent,false);
        return new ViewHolder(flight_view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Flight flight= flight_collection.get(position);
        holder.bind(flight);
    }

    @Override
    public int getItemCount() {
        return flight_collection.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvFlightItemDep;
        TextView tvFlightItemArr;
        TextView tvFlightItemPrice;
        TextView tvFlightItemAirline;
        TextView tvFlightItemDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFlightItemDep=itemView.findViewById(R.id.tvFlightItemDep);
            tvFlightItemArr=itemView.findViewById(R.id.tvFlightItemArr);
            tvFlightItemPrice=itemView.findViewById(R.id.tvFlightItemPrice);
            tvFlightItemAirline=itemView.findViewById(R.id.tvFlightItemAirline);
            tvFlightItemDate=itemView.findViewById(R.id.tvFlightItemDate);
        }

        public void bind(Flight flight) {
            tvFlightItemAirline.setText(flight.getCarrier());
            tvFlightItemDate.setText(flight.getDeparture_date());
            tvFlightItemArr.setText(flight.getArrCity());
            tvFlightItemDep.setText(flight.getDepCity());
            String price_inString=String.valueOf(flight.getPrice());
            tvFlightItemPrice.setText("$"+price_inString);
        }
    }
}
