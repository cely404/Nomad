package com.cely404.nomad;


import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ListView;

import com.cely404.nomad.data.YouTubeContent;
import com.google.android.youtube.player.YouTubeIntents;
import com.google.api.services.youtube.model.SearchResult;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoListFragment extends ListFragment {


    public VideoListFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new VideoListAdapter(getActivity(), YouTubeContent.ITEMS));
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        final Context context = getActivity();
        final String DEVELOPER_KEY = getString(R.string.YOUTUBE_API_KEY);
        final SearchResult video = YouTubeContent.ITEMS.get(position);

        if (YouTubeIntents.canResolvePlayVideoIntentWithOptions(getActivity())) {
            //Opens in the YouTube app in fullscreen and returns to this app once the video finishes
            //startActivity(YouTubeIntents.createPlayVideoIntentWithOptions(context, video.id, true, true));
        }

    }

}
