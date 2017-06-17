package com.bassem.lastfm.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Bassem Samy on 6/17/2017.
 */

public class TopTracks {

    @SerializedName("track")
    private List<Track> tracks;

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }
}
