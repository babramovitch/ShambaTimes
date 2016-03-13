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
    public static final int BIODOME = 6;

    public static final int MINUTES_IN_DAY = 1440;

    public static int REFERENCE_TIME = 660;
    public static int SUNDAY_REFERENCE_TIME = 720;
    public static int GENERAL_REFERENCE_TIME = 660;

    public static DateTimeZone timeZone = DateTimeZone.forID("America/Los_Angeles");

}
