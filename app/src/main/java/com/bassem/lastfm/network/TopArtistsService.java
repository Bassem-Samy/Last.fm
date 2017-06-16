package com.bassem.lastfm.network;

import com.bassem.lastfm.models.TopArtistsResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Bassem Samy on 6/16/2017.
 */

public interface TopArtistsService {
    @GET("?method=user.gettopartists&format=json")
    Single<TopArtistsResponse> getTopArtists(@Query("user") String user, @Query("limit") int limit, @Query("api_key") String apiKey);
}
