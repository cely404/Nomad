package com.cely404.nomad;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.cely404.nomad.data.SearchResults;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by aracely_payan93 on 3/21/16.
 */
public class temporary extends BaseAdapter implements YouTubeThumbnailView.OnInitializedListener{
    private Context context;
    private Map<View, YouTubeThumbnailLoader> mloader;
    private List<SearchResults> videoList = new ArrayList<SearchResults>();

    public temporary (final Context context, List<SearchResults> videoList) {
        this.context = context;
        mloader = new HashMap<>();
        this.videoList = videoList;
    }

    @Override
    public int getCount() {
        //return YouTubeContent.ITEMS.size();
        return videoList.size();
    }

    @Override
    public Object getItem(int position) {
        //return YouTubeContent.ITEMS.get(position);
        return videoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
        //return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //YouTubeContent.YouTubeVideo item = YouTubeContent.ITEMS.get(position);

        VideoHolder holder;
        View view = convertView;
        if(convertView == null){
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.youtube_thumbnail, null);

            //Create the video holder
            holder = new VideoHolder();

//            //Set the title
//            holder.title = (TextView) view.findViewById(R.id.textView_title);
//            holder.title.setText(item.title);
//
//            //Initialise the thumbnail
//            holder.thumb = (YouTubeThumbnailView) view.findViewById(R.id.imageView_thumbnail);
//            holder.thumb.setTag(item.id);
//            holder.thumb.initialize(context.getString(R.string.YOUTUBE_API_KEY), this);

            view.setTag(holder);
        }
        else{
            //Create it again
            holder = (VideoHolder) view.getTag();
            final YouTubeThumbnailLoader loader = mloader.get(holder.thumb);

//            //Set the title
//            if (item != null) {
//                holder.title.setText(item.title);
//
//                if (loader == null) {
//                    //Loader is currently initialising
//                    holder.thumb.setTag(item.id);
//                } else {
//                    //The loader is already initialised
//                    loader.setVideo(item.id);
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