package com.cely404.nomad.model;

import com.google.android.youtube.player.YouTubeThumbnailView;


public class Video {
    String title;
    String videoId;
    YouTubeThumbnailView thumbNail;

    public Video(String title, String videoId, YouTubeThumbnailView thumbNail){
        this.title = title;
        this.videoId = videoId;
        this.thumbNail = thumbNail;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public YouTubeThumbnailView getThumbNail() {
        return thumbNail;
    }

    public void setThumbNail(YouTubeThumbnailView thumbNail) {
        this.thumbNail = thumbNail;
    }



}
