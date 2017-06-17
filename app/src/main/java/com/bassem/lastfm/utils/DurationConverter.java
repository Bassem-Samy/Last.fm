package com.bassem.lastfm.utils;

/**
 * Created by Bassem Samy on 6/17/2017.
 */

public class DurationConverter {
    private static final String SEPARATOR = ":";
    private static final String DEFAULT_VALUE = "0:0";

    public static String getDurationInMinutesText(long durationInSeconds) {

        long minutes = durationInSeconds / 60;
        long seconds = durationInSeconds - (minutes * 60);
        return Long.toString(minutes) + SEPARATOR + Long.toString(seconds);
    }
}
