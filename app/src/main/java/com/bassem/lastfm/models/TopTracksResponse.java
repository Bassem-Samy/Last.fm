package com.bassem.lastfm.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Bassem Samy on 6/17/2017.
 */

public class TopTracksResponse {
    @SerializedName("toptracks")
    private TopTracks topTracks;

    public TopTracks getTopTracks() {
        return topTracks;
    }

    public void setToptracks(TopTracks topTracks) {
        this.topTracks = topTracks;
    }
}
