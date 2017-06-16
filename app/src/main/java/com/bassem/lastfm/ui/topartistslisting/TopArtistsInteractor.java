package com.bassem.lastfm.ui.topartistslisting;

import com.bassem.lastfm.models.TopArtistsResponse;

import io.reactivex.Single;

/**
 * Created by Bassem Samy on 6/16/2017.
 */

public interface TopArtistsInteractor {
    Single<TopArtistsResponse> getTopArtists(String userName, int limit, String apiKey);
}
