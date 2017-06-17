package com.bassem.lastfm.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Bassem Samy on 6/16/2017.
 */

public class Album {
    @SerializedName("mbid")
    private String mbid;
    @SerializedName("name")
    private String name;
    @SerializedName("image")
    private List<ImageItem> image;
    @SerializedName("playcount")
    private String playCount;
    @SerializedName("artist")
    private Artist artist;
    @SerializedName("url")
    private String url;

    public String getMbid() {
        return mbid;
    }

    public void setMbid(String mbid) {
        this.mbid = mbid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ImageItem> getImage() {
        return image;
    }

    public void setImage(List<ImageItem> image) {
        this.image = image;
    }

    public String getPlaycount() {
        return playCount;
    }

    public void setPlaycount(String playcount) {
        this.playCount = playcount;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageUrl() {
        if (getImage() != null && getImage().size() > 0) {
            for (ImageItem img :
                    getImage()) {
                if (img.getSize().equalsIgnoreCase("large")) {
                    return img.getUrl();
                }
            }
        }
        return null;
    }
}
