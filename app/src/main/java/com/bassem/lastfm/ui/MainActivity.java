package com.bassem.lastfm.ui;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.bassem.lastfm.R;
import com.bassem.lastfm.adapters.MainPagerAdapter;
import com.bassem.lastfm.ui.artistslisting.ArtistsFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnEditorAction;

public class MainActivity extends AppCompatActivity implements ArtistsFragment.OnFragmentInteractionListener {

    @BindView(R.id.tl_main)
    TabLayout mTabLayout;
    @BindView(R.id.vp_main)
    ViewPager mViewPager;
    MainPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initializeFragments();
    }

    private void initializeFragments() {
        mAdapter = new MainPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @OnEditorAction(R.id.edt_search)
    boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            searchUser(v.getText().toString());
            return true;
        }
        return false;
    }

    private void searchUser(String userName) {
        for (int i = 0; i < mAdapter.getCount(); i++) {
            Fragment fr = mAdapter.getItem(i);
            if (fr instanceof BaseFragment) {
                ((BaseFragment) fr).searchUserName(userName);
            }
        }
    }
}
