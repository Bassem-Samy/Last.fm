package com.bassem.lastfm.ui.topalbumslisting.di;

import com.bassem.lastfm.models.TopAlbums;
import com.bassem.lastfm.ui.topalbumslisting.TopAlbumsFragment;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Module;

/**
 * Created by Bassem Samy on 6/17/2017.
 */
@Singleton
@Component(modules = {TopAlbumsModule.class})
public interface TopAlbumsComponent {
    void inject(TopAlbumsFragment target);
}
