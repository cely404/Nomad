package com.cely404.nomad;


import android.app.DialogFragment;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.cely404.nomad.activities.UserProfile;
import com.cely404.nomad.adapters.VideoListAdapter;
import com.cely404.nomad.data.SearchResults;
import com.cely404.nomad.dialogs.SearchResultDialogFragment;
import com.cely404.nomad.model.YouTubeContent;
import com.google.android.youtube.player.YouTubeIntents;
import com.google.api.services.youtube.model.SearchResult;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchQueryVideoList extends ListFragment implements SearchResultDialogFragment.SearchResultDialogListener{
    public static List<SearchResult> list;
    private static int videoPosition;
    public SearchQueryVideoList() {
        new SearchResults().execute(UserProfile.query);
    }

    @Override
    public void onDialogPositiveClick() {
        Toast.makeText(getActivity(), "chose to add", Toast.LENGTH_LONG).show();
        YouTubeContent.addItem(list.get(videoPosition));
    }

    @Override
    public void onDialogNeutralClick(){
        Toast.makeText(getActivity(), "chose to view", Toast.LENGTH_LONG).show();
        final SearchResult video = list.get(videoPosition);
        if (YouTubeIntents.canResolvePlayVideoIntentWithOptions(getActivity())) {
            //Opens in the YouTube app in fullscreen and returns to this app once the video finishes
            startActivity(YouTubeIntents.createPlayVideoIntentWithOptions(getActivity(),video.getId().getVideoId(), true, true));
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new VideoListAdapter(getActivity(), list));
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        this.videoPosition = position;
        final Context context = getActivity();
        final String DEVELOPER_KEY = getString(R.string.YOUTUBE_API_KEY);
        DialogFragment dialog = new SearchResultDialogFragment();
        dialog.setTargetFragment(this,0);
        dialog.show(getFragmentManager(), "dialogs.SearchResultDialogFragment");

    }

}