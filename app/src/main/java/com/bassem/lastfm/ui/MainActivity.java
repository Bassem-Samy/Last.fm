package com.bassem.lastfm.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bassem.lastfm.R;
import com.bassem.lastfm.adapters.MainPagerAdapter;
import com.bassem.lastfm.models.Album;
import com.bassem.lastfm.models.Artist;
import com.bassem.lastfm.models.Track;
import com.bassem.lastfm.ui.topalbumslisting.TopAlbumsFragment;
import com.bassem.lastfm.ui.topartistslisting.TopArtistsFragment;
import com.bassem.lastfm.ui.toptrackslisting.TopTracksFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnEditorAction;

public class MainActivity extends AppCompatActivity implements TopArtistsFragment.OnFragmentInteractionListener, TopAlbumsFragment.OnFragmentInteractionListener, TopTracksFragment.OnFragmentInteractionListener {

    @BindView(R.id.tl_main)
    TabLayout mTabLayout;
    @BindView(R.id.vp_main)
    ViewPager mViewPager;
    MainPagerAdapter mAdapter;
    @BindView(R.id.edt_search)
    EditText searchEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        looseSearchEditTextFocus();
        initializeFragments();
    }


    private void initializeFragments() {
        mAdapter = new MainPagerAdapter(getSupportFragmentManager(), this);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOffscreenPageLimit(3);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @OnEditorAction(R.id.edt_search)
    boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            if (isValidSearch(v.getText().toString())) {
                searchUser(v.getText().toString());
            } else {
                showEnterValidUserNameToast();
            }
            looseSearchEditTextFocus();
            return true;
        }
        return false;
    }

    private void showEnterValidUserNameToast() {
        Toast.makeText(this, R.string.please_enter_a_user_name, Toast.LENGTH_SHORT).show();
    }

    private boolean isValidSearch(String search) {
        if (TextUtils.isEmpty(search))
            return false;
        return true;
    }

    // loops the base fragments and notify them to search with the given userName
    private void searchUser(String userName) {
        for (Fragment fr : mAdapter.getFragments()
                ) {
            if (fr instanceof BaseFragment) {
                ((BaseFragment) fr).searchUserName(userName);
            }
        }

    }


    @Override
    public void onArtistClicked(Artist artist) {
        // open artist url
        openUrl(artist.getUrl());
    }

    @Override
    public void onAlbumClicked(Album album) {
        openUrl(album.getUrl());
    }

    @Override
    public void onTrackClicked(Track track) {
        openUrl(track.getUrl());
    }

    void openUrl(String url) {
        if (!TextUtils.isEmpty(url)) {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        }
    }

    // hide keyboard after search
    private void looseSearchEditTextFocus() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        searchEditText.clearFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);

        }
    }
}
