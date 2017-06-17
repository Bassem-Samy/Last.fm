package com.bassem.lastfm;

import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.ViewAssertion;
import android.support.v4.view.ViewPager;
import android.view.View;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.core.IsAnything;

import static org.hamcrest.Matchers.anything;

/**
 * Created by Bassem Samy on 6/18/2017.
 */

public class ViewPagerSwipeToPositionAction implements ViewAction {
    private int mPosition;

    public ViewPagerSwipeToPositionAction(int position) {
        mPosition = position;
    }

    @Override
    public Matcher<View> getConstraints() {
        return new Matcher<View>() {
            @Override
            public boolean matches(Object item) {
                return (item instanceof ViewPager);
            }

            @Override
            public void describeMismatch(Object item, Description mismatchDescription) {

            }

            @Override
            public void _dont_implement_Matcher___instead_extend_BaseMatcher_() {

            }

            @Override
            public void describeTo(Description description) {

            }
        };
    }

    @Override
    public String getDescription() {
        return "set current item for a view pager";
    }

    @Override
    public void perform(UiController uiController, View view) {
        if (view instanceof ViewPager) {
            ViewPager viewPager = (ViewPager) view;
            viewPager.setCurrentItem(mPosition);
        }
    }
}
