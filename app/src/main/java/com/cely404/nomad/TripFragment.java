package com.cely404.nomad;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cely404.nomad.R;
import com.cely404.nomad.data.Trip;
import com.cely404.nomad.data.TripData;

import java.util.List;

/**
 * Created by David on 7/16/2014.
 */
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
        View rootView = inflater.inflate(R.layout.trip_fragment, container, false);
        return rootView;
    }
}
