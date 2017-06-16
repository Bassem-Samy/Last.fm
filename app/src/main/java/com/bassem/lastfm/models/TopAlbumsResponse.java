package com.bassem.lastfm.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Bassem Samy on 6/16/2017.
 */

public class TopAlbumsResponse {
    public TopAlbums getTopAlbums() {
        return topAlbums;
    }

    public void setTopAlbums(TopAlbums topAlbums) {
        this.topAlbums = topAlbums;
    }

    @SerializedName("topalbums")
    TopAlbums topAlbums;
}
