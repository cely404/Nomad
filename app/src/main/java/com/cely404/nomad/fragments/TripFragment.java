package com.cely404.nomad.fragments;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.cely404.nomad.R;
import com.cely404.nomad.activities.UserProfile;
import com.cely404.nomad.adapters.TripArrayAdapter;
import com.cely404.nomad.data.TripData;
import com.cely404.nomad.model.Trip;

import java.util.List;

public class TripFragment extends ListFragment {

    List<Trip> trips = new TripData().getTrips();

    public TripFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TripArrayAdapter adapter = new TripArrayAdapter(getActivity(),
                R.layout.trip_listitem,
                trips);
        Toast.makeText(getActivity(), "size of trips is " + trips.size(), Toast.LENGTH_LONG).show();
        setListAdapter(adapter);
        setHasOptionsMenu(true);

//        if (Intent.ACTION_SEARCH.equals(getActivity().getIntent().getAction())) {
//            String query = getActivity().getIntent().getStringExtra(SearchManager.QUERY);
//            Toast.makeText(getActivity().getApplicationContext(), "it worked", Toast.LENGTH_LONG);
//            //use the query to search your data somehow
//        }

    }
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.trip_fragment, container, false);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id){
        if(position == 1){
            Intent i = new Intent(getActivity(), UserProfile.class);
            startActivity(i);
        }
        if(position == 2){
            Toast.makeText(getActivity(), "Clicked the second", Toast.LENGTH_LONG).show();
        }
    }
}
