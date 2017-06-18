package com.bassem.lastfm;

import com.bassem.lastfm.utils.Constants;
import com.bassem.lastfm.utils.DurationConverter;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class DurationConverterUnitTest {
    static final long FIRST_SECONDS = 360L;
    static final String FIRST_EXPECTED_RESULT = "6:0";

    static final long SECOND_SECONDS = 244L;
    static final String SECOND_EXPECTED_RESULT = "4:4";

    static final long THIRD_SECONDS = 650L;
    static final String THIRD_EXPECTED_RESULT = "10:50";

    static final long FOURTH_SECONDS = 1560L;
    static final String FOURTH_EXPECTED_RESULT = "26:0";

    static final long FIFTH_SECONDS = -10L;
    static final String FIFTH_EXPECTED_RESULT = "0:0";

    static final long SIXTH_SECONDS = 0;
    static final String SIXTH_EXPECTED_RESULT = "0:0";

    @Test
    public void addition_isCorrect() throws Exception {
        //  assertEquals(4, 2 + 2);
        assertEquals(DurationConverter.getDurationInMinutesText(FIRST_SECONDS), FIRST_EXPECTED_RESULT);
        assertEquals(DurationConverter.getDurationInMinutesText(SECOND_SECONDS), SECOND_EXPECTED_RESULT);
        assertEquals(DurationConverter.getDurationInMinutesText(THIRD_SECONDS), THIRD_EXPECTED_RESULT);
        assertEquals(DurationConverter.getDurationInMinutesText(FOURTH_SECONDS), FOURTH_EXPECTED_RESULT);
        assertEquals(DurationConverter.getDurationInMinutesText(FIFTH_SECONDS), FIFTH_EXPECTED_RESULT);
        assertEquals(DurationConverter.getDurationInMinutesText(SIXTH_SECONDS), SIXTH_EXPECTED_RESULT);


    }
}