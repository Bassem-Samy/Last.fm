package com.bassem.lastfm.ui.topartistslisting;

import com.bassem.lastfm.models.Artist;

import java.util.List;

/**
 * Created by Bassem Samy on 6/16/2017.
 */

public interface TopArtistsView {
    void showProgress();

    void hideProgress();

    void updateData(List<Artist> topArtists);

    void showError();
    void showEmpty();
    void hidEmpty();

}
