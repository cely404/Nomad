package com.cely404.nomad;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cely404.nomad.data.Trip;
import com.cely404.nomad.data.TripData;

import java.util.List;

public class TripFragment extends ListFragment {

    List<Trip> flowers = new TripData().getTrips();

    public TripFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TripArrayAdapter adapter = new TripArrayAdapter(getActivity(),
                R.layout.trip_listitem,
                flowers);
        setListAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.trip_fragment, container, false);
    }
}
