package com.cely404.nomad.model;

import com.google.api.services.youtube.model.SearchResult;

import java.util.ArrayList;
import java.util.List;

/**
 * A Helper class for providing mock data to the app.
 * In a real world scenario you would either hard code the video ID's in the strings file or
 * retrieve them from a web service.
 */
public class YouTubeContent {

    /**
     * An array of YouTube videos
     */
    public static List<SearchResult> ITEMS = new ArrayList<>();
    public static void addItem(final SearchResult item) {
        ITEMS.add(item);
    }
    public static void clear(){
        ITEMS = new ArrayList<>();
    }
    /**
     * A POJO representing a YouTube video
     */

    public static class YouTubeVideo {
        public String id;
        public String title;

        public YouTubeVideo(String id, String content) {
            this.id = id;
            this.title = content;
        }

        @Override
        public String toString() {
            return title;
        }
    }
}