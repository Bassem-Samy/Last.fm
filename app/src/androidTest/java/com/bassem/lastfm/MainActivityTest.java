package com.bassem.lastfm;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.bassem.lastfm.ui.MainActivity;
import com.bassem.lastfm.utils.Constants;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    static final int TOTAL_ITEMS_COUNT = 10;
    static final String TOP_ARTISTS_JSON_FILE = "top_artists.json";
    static final String TOP_TRACKS_JSON_FILE = "top_tracks.json";
    static final String TOP_ALBUMS_JSON_FILE = "top_albums.json";
    static final String TOP_ARTISTS_REQUEST_URL = "/test/?method=user.gettopartists&format=json&user=drrobbins&limit=5&api_key=35066a49f2deb23a3c35fd48ff5c9869";
    static final String TOP_ALBUMS_REQUEST_URL = "/test/?method=user.gettopalbums&format=json&user=drrobbins&limit=5&api_key=35066a49f2deb23a3c35fd48ff5c9869";
    static final String TOP_TRACKS_REQUEST_URL = "/test/?method=user.gettoptracks&format=json&user=drrobbins&limit=5&api_key=35066a49f2deb23a3c35fd48ff5c9869";
    static final String LAST_EXPECTED_ARTIST_NAME = "X-Dream";
    static final String LAST_EXPECTED_ARTIST_PLAY_COUNT = "30";

    static final String LAST_EXPECTED_ALBUM_NAME = "Iowa";
    static final String LAST_EXPECTED_ALBUM__PLAY_COUNT = "1163";
    static final String LAST_EXPECTED_ALBUM_ARTIST = "Slipknot";

    static final String LAST_EXPECTED_TRACK_NAME = "Fences";
    static final String LAST_EXPECTED_TRACK_ARTIST = "Paramore";
    static final String LAST_EXPECTED_TRACK_PLAY_COUNT = "200";
    static final String LAST_EXPECTED_TRACK_DURATION = "3:18";
    private static MockWebServer mockWebServer;


    @Rule
    public ActivityTestRule<MainActivity> categoryDetailActivityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    @BeforeClass
    public static void setup() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
        Constants.BASE_URL = mockWebServer.url("test/").toString();
        mockWebServer.setDispatcher(new Dispatcher() {
            @Override
            public MockResponse dispatch(RecordedRequest recordedRequest) throws InterruptedException {
                if (recordedRequest.getPath().equalsIgnoreCase(TOP_ARTISTS_REQUEST_URL)) {
                    try {
                        return new MockResponse().setResponseCode(200)
                                .setBody(AssetsFileReader.readFileAsString(TOP_ARTISTS_JSON_FILE));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (recordedRequest.getPath().equalsIgnoreCase(TOP_TRACKS_REQUEST_URL)) {

                    try {
                        return new MockResponse().setResponseCode(200)
                                .setBody(AssetsFileReader.readFileAsString(TOP_TRACKS_JSON_FILE));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } else if (recordedRequest.getPath().equalsIgnoreCase(TOP_ALBUMS_REQUEST_URL)) {

                    try {
                        return new MockResponse().setResponseCode(200)
                                .setBody(AssetsFileReader.readFileAsString(TOP_ALBUMS_JSON_FILE));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                return null;
            }
        });
    }

    @Test
    public void testDataDisplay() {
        // cehck number of displayed artists
        onView(withId(R.id.rclr_artists)).check(new RecyclerViewItemsCountAssertion(TOTAL_ITEMS_COUNT));
        // scroll to the last item
        onView(withId(R.id.rclr_artists)).perform(scrollToPosition(TOTAL_ITEMS_COUNT - 1));
        // check last item data is displayed correctly
        checkArtistItem(LAST_EXPECTED_ARTIST_NAME, LAST_EXPECTED_ARTIST_PLAY_COUNT, TOTAL_ITEMS_COUNT - 1);
        // go to top albums
        onView(withId(R.id.vp_main)).perform(new ViewPagerSwipeToPositionAction(1));
        // check number of displayed albums
        onView(withId(R.id.rclr_albums)).check(new RecyclerViewItemsCountAssertion(TOTAL_ITEMS_COUNT));
        //scroll to the last album
        onView(withId(R.id.rclr_albums)).perform(scrollToPosition(TOTAL_ITEMS_COUNT - 1));
        // check last album data displayed correctly
        checkAlbumItem(LAST_EXPECTED_ALBUM_NAME, LAST_EXPECTED_ALBUM__PLAY_COUNT, LAST_EXPECTED_ALBUM_ARTIST, TOTAL_ITEMS_COUNT - 1);
        // go to top albums
        onView(withId(R.id.vp_main)).perform(new ViewPagerSwipeToPositionAction(2));
        // check number of displayed tracks
        onView(withId(R.id.rclr_tracks)).check(new RecyclerViewItemsCountAssertion(TOTAL_ITEMS_COUNT));
        //scroll to the last album
        onView(withId(R.id.rclr_tracks)).perform(scrollToPosition(TOTAL_ITEMS_COUNT - 1));
        // check last album data displayed correctly
        checkTrackItem(LAST_EXPECTED_TRACK_NAME, LAST_EXPECTED_TRACK_ARTIST, LAST_EXPECTED_TRACK_PLAY_COUNT, LAST_EXPECTED_TRACK_DURATION, TOTAL_ITEMS_COUNT - 1);


    }


    private void checkArtistItem(String expectedArtistName, String expectedArtistPlayCount, int itemPosition) {
// match artist name
        onView(withId(R.id.rclr_artists)).check(new RecyclerViewItemStringDataAssertion(
                R.id.txt_artist_name,
                expectedArtistName,
                itemPosition));
        // match artist playcount
        onView(withId(R.id.rclr_artists)).check(new RecyclerViewItemStringDataAssertion(
                R.id.txt_plays,
                expectedArtistPlayCount,
                itemPosition));
    }


    private void checkAlbumItem(String expectedAlbumName, String expectedAlbumPlayCount, String expectedAlbumArtist, int itemPosition) {
        // check album name
        onView(withId(R.id.rclr_albums)).check(new RecyclerViewItemStringDataAssertion(
                R.id.txt_album_name,
                expectedAlbumName,
                itemPosition));
        // check album artist
        onView(withId(R.id.rclr_albums)).check(new RecyclerViewItemStringDataAssertion(
                R.id.txt_album_artist,
                expectedAlbumArtist,
                itemPosition));

        // match album playcount
        onView(withId(R.id.rclr_albums)).check(new RecyclerViewItemStringDataAssertion(
                R.id.txt_plays,
                expectedAlbumPlayCount,
                itemPosition));
    }

    private void checkTrackItem(String expectedTrackName, String expectedTrackArtistName, String expectedTrackPlayCount, String expectedTrackDuration, int itemPosition) {
        // match track name
        onView(withId(R.id.rclr_tracks)).check(new RecyclerViewItemStringDataAssertion(
                R.id.txt_track_name,
                expectedTrackName,
                itemPosition));
        // match track artist
        onView(withId(R.id.rclr_tracks)).check(new RecyclerViewItemStringDataAssertion(
                R.id.txt_track_artist,
                expectedTrackArtistName,
                itemPosition));
        // match track play count
        onView(withId(R.id.rclr_tracks)).check(new RecyclerViewItemStringDataAssertion(
                R.id.txt_plays,
                expectedTrackPlayCount,
                itemPosition));
        // match track duration
        onView(withId(R.id.rclr_tracks)).check(new RecyclerViewItemStringDataAssertion(
                R.id.txt_duration,
                expectedTrackDuration,
                itemPosition));
    }

}