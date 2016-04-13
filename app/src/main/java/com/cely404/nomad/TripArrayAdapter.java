package com.cely404.nomad;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cely404.nomad.data.Trip;

import java.util.List;

public class TripArrayAdapter extends ArrayAdapter<Trip> {
    private Context context;
    private List<Trip> objects;

    public TripArrayAdapter(Context context, int resource, List<Trip> objects){
        super(context, resource, objects);
        this.context = context;
        this.objects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Trip trip = objects.get(position);

        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.trip_listitem, null);

        TextView tvDepartureCity = (TextView) view.findViewById(R.id.tvDepartureCity);
        TextView tvDescription = (TextView) view.findViewById(R.id.tvDescription);
        ImageView image = (ImageView) view.findViewById(R.id.userPic);
        image.setImageResource(R.drawable.studentgirl);
        tvDepartureCity.setText(trip.getDepartingCity());
        tvDescription.setText(trip.getDescription());

        return view;
    }
}
