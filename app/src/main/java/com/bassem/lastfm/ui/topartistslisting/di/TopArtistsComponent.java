package com.bassem.lastfm.ui.topartistslisting.di;

import com.bassem.lastfm.models.TopArtists;
import com.bassem.lastfm.ui.topartistslisting.TopArtistsFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Bassem Samy on 6/16/2017.
 */

@Singleton
@Component(modules = {TopArtistsModule.class})
public interface TopArtistsComponent {
    void inject(TopArtistsFragment target);
}
