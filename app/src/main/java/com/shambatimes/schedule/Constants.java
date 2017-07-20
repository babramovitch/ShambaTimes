package com.shambatimes.schedule;

import org.joda.time.DateTimeZone;

/**
 * Created by Ben on 14/02/2015.
 */
public class Constants {

    public static final int PAGODA = 0;
    public static final int FOREST = 1;
    public static final int GROVE = 2;
    public static final int LIVINGROOM = 3;
    public static final int VILLAGE =4;
    public static final int AMPHITHEATER = 5;
    public static final int CEDAR_LOUNGE = 6;

    public static final int MINUTES_IN_DAY = 1440;

    public static int REFERENCE_TIME = 660;
    public static int SUNDAY_REFERENCE_TIME_2015 = 720;
    public static int GENERAL_REFERENCE_TIME = 660;
    public static int GENERAL_REFERENCE_TIME_2017 = 600;

    public static int ANIMATION_DURATION = 400;
    public static int ANIMATION_DURATION_HEARTS = 300;

    public static final String FRAGMENT_TIME = "TIME";
    public static final String FRAGMENT_STAGE = "STAGE";
    public static final String FRAGMENT_FAVOURITE = "FAVORITE";
    public static final String FRAGMENT_ARTISTS = "ARTISTS";
    public static final String FRAGMENT_CALENDAR = "CALENDAR";

    public static DateTimeZone timeZone = DateTimeZone.forID("America/Los_Angeles");

}
