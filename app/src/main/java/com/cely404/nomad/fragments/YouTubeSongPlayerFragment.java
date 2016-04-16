package com.cely404.nomad.fragments;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cely404.nomad.R;
import com.cely404.nomad.model.YouTubeContent;
import com.google.api.services.youtube.model.SearchResult;

import java.util.List;

public class YouTubeSongPlayerFragment extends ListFragment {
    List<SearchResult> videos = YouTubeContent.ITEMS;

    public YouTubeSongPlayerFragment() {
    }

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        VideoListAdapter adapter = new VideoListAdapter(getActivity(),
//                R.layout.trip_listitem,
//                videos);
//        setListAdapter(adapter);
//    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.trip_fragment, container, false);
    }
}
