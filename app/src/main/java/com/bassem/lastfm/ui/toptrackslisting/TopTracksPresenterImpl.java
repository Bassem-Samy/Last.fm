package com.bassem.lastfm.ui.toptrackslisting;

import com.bassem.lastfm.models.TopTracksResponse;
import com.bassem.lastfm.models.Track;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Bassem Samy on 6/17/2017.
 */

public class TopTracksPresenterImpl implements TopTracksPresenter {
    Disposable mDisposable;
    TopTracksInteractor mInteractor;
    TopTracksView mView;

    public TopTracksPresenterImpl(TopTracksView view, TopTracksInteractor interactor) {
        this.mView = view;
        this.mInteractor = interactor;
    }

    @Override
    public void getTopTracks(String userName, int limit, String apiKey) {
        disposeRequest();
        mView.showProgress();
        mView.hidEmpty();
        mDisposable = mInteractor.getTopTracks(userName, limit, apiKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<TopTracksResponse, List<Track>>() {
                    @Override
                    public List<Track> apply(@NonNull TopTracksResponse topTracksResponse) throws Exception {
                        if (topTracksResponse != null && topTracksResponse.getTopTracks() != null && topTracksResponse.getTopTracks().getTracks() != null) {
                            return topTracksResponse.getTopTracks().getTracks();
                        }
                        return new ArrayList<Track>();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Track>>() {
                    @Override
                    public void accept(@NonNull List<Track> tracks) throws Exception {
                        mView.hideProgress();
                        if (tracks.size() == 0) {
                            mView.showEmpty();
                        }
                        mView.updateData(tracks);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        mView.hideProgress();
                        mView.showError();

                    }
                });
    }

    @Override
    public void onDestroy() {
        disposeRequest();

    }

    private void disposeRequest() {
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }

}
