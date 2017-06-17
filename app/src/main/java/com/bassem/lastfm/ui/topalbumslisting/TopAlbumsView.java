package com.bassem.lastfm.ui.topalbumslisting;

import com.bassem.lastfm.models.Album;

import java.util.List;

/**
 * Created by Bassem Samy on 6/16/2017.
 */

public interface TopAlbumsView {
    void showProgress();

    void hideProgress();

    void showError();

    void updateData(List<Album> topAlbums);

    void showEmpty();

    void hidEmpty();
}
