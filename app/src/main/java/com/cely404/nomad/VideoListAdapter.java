package com.cely404.nomad;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;
import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class VideoListAdapter extends BaseAdapter implements YouTubeThumbnailView.OnInitializedListener{
    private Context context;
    private Map<View, YouTubeThumbnailLoader> mloader;
    private List<SearchResult> videoList = new ArrayList<>();

    public VideoListAdapter(final Context context, List<SearchResult> videoList) {
        this.context = context;
        mloader = new HashMap<>();
        if(videoList!=null){
            this.videoList = videoList;
        }
    }

    @Override
    public int getCount() {
        return videoList.size();
    }

    @Override
    public Object getItem(int position) {
        return videoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SearchResult singleVideo = videoList.get(position);
        ResourceId rId = singleVideo.getId();

        VideoHolder holder;
        View view = convertView;
        if(convertView == null){
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.youtube_thumbnail, null);

            //Create the video holder
            holder = new VideoHolder();

            //Set the title
            holder.title = (TextView) view.findViewById(R.id.textView_title);
            holder.title.setText(singleVideo.getSnippet().getTitle());

            //Initialise the thumbnail
            holder.thumb = (YouTubeThumbnailView) view.findViewById(R.id.imageView_thumbnail);
            holder.thumb.setTag(rId.getVideoId());
            holder.thumb.initialize(context.getString(R.string.YOUTUBE_API_KEY), this);

            view.setTag(holder);
        }
        else{
            //Create it again
            holder = (VideoHolder) view.getTag();
            final YouTubeThumbnailLoader loader = mloader.get(holder.thumb);

            //Set the title
//            if (singleVideo != null) {
//                holder.title.setText(singleVideo.getSnippet().getTitle());
//
//                if (loader == null) {
//                    //Loader is currently initialising
//                    holder.thumb.setTag(rId.getVideoId());
//                } else {
//                    //The loader is already initialised
//                    loader.setVideo(rId.getVideoId());
//                }
//
//            }
        }
        return view;

    }

    @Override
    public void onInitializationSuccess(YouTubeThumbnailView view, YouTubeThumbnailLoader loader) {
        mloader.put(view, loader);
        loader.setVideo((String) view.getTag());
    }

    @Override
    public void onInitializationFailure(YouTubeThumbnailView thumbnailView, YouTubeInitializationResult errorReason) {
        final String errorMessage = errorReason.toString();
        Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show();
    }

    static class VideoHolder {
        YouTubeThumbnailView thumb;
        TextView title;
    }
}