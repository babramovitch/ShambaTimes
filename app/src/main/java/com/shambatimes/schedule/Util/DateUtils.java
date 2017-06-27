package com.shambatimes.schedule.Util;

import android.content.Context;

import com.shambatimes.schedule.Artist;
import com.shambatimes.schedule.Constants;
import com.shambatimes.schedule.Shambhala;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Created by Ben on 21/02/2015.
 */
public final class DateUtils {

    public static void setReferenceTime(Context context) {
        setReferenceTime(context, DateUtils.getCurrentDay(context));
    }

    public static void setReferenceTime(Context context, int currentDay) {
        if (currentDay == 3 && Shambhala.getFestivalYear(context).equals("2015")) {
            Constants.REFERENCE_TIME = Constants.SUNDAY_REFERENCE_TIME_2015;
        } else {
            Constants.REFERENCE_TIME = Constants.GENERAL_REFERENCE_TIME;
        }

        //To add a future special time, the following must be modified
        //The Artist class base time condition
        //The ChangeDateEvent in TimeCardScheduleFragment and TimeScheduleFragment
    }

    public static DateTimeFormatter getTimeFormat(String format) {

        DateTimeFormatter dateStringFormat;

        if (format.equals("24")) {
            dateStringFormat = DateTimeFormat.forPattern("HH:mm");
        } else {
            dateStringFormat = DateTimeFormat.forPattern("h:mm aa");
        }

        return dateStringFormat;
    }

    public static DateTimeFormatter getTimeFormatTwo(String format) {

        DateTimeFormatter dateStringFormat;

        if (format.equals("24")) {
            dateStringFormat = DateTimeFormat.forPattern("HH:mm");
        } else {
            dateStringFormat = DateTimeFormat.forPattern("h:mm aa");
        }

        return dateStringFormat;
    }

    public static String formatTime(DateTimeFormatter dateStringFormat, String time) {
        return dateStringFormat.print(new LocalTime(time, Constants.timeZone));
    }

    public static int getCurrentTimePosition(Context context) {

        setReferenceTime(context);

        int referenceMinutes = Constants.REFERENCE_TIME;
        int minutesInDay = Constants.MINUTES_IN_DAY;

        DateTime now = DateTime.now().withZone(Constants.timeZone);
        int minutes = now.getMinuteOfDay();
        int timePosition;

        //Adjust the time based off starting reference point for the day.  E.G. 10am-10am
        if (minutes >= referenceMinutes) {
            minutes = minutes - referenceMinutes;
        } else if (minutes < referenceMinutes) {
            minutes += (minutesInDay - referenceMinutes);
        }

        timePosition = minutes / 30;

        return timePosition;
    }

    public static boolean isPrePostFestival(Context context) {

        Interval interval;
        int day = -1;

        if (!Shambhala.getFestivalYear(context).equals(Shambhala.CURRENT_YEAR)) {
            return true;
        }

        DateTime startThursday = new DateTime(2017, 8, 10, 11, 0, 0, 0).withZone(Constants.timeZone);
        DateTime endThursday = new DateTime(2017, 8, 11, 10, 59, 59, 99).withZone(Constants.timeZone);
        interval = new Interval(startThursday, endThursday);

        if (interval.contains(DateTime.now().withZone(Constants.timeZone))) {
            day = 0;
        }

        DateTime startFriday = new DateTime(2017, 8, 11, 11, 0, 0, 0).withZone(Constants.timeZone);
        DateTime endFriday = new DateTime(2017, 8, 12, 10, 59, 59, 99).withZone(Constants.timeZone);
        interval = new Interval(startFriday, endFriday);

        if (interval.contains(DateTime.now().withZone(Constants.timeZone))) {
            day = 1;
        }

        DateTime startSaturday = new DateTime(2017, 8, 12, 11, 0, 0, 0).withZone(Constants.timeZone);
        DateTime endSaturday = new DateTime(2017, 8, 13, 10, 59, 59, 99).withZone(Constants.timeZone);
        interval = new Interval(startSaturday, endSaturday);

        if (interval.contains(DateTime.now().withZone(Constants.timeZone))) {
            day = 2;
        }

        DateTime startSunday = new DateTime(2017, 8, 13, 11, 0, 0, 0).withZone(Constants.timeZone);
        DateTime endSunday = new DateTime(2017, 8, 14, 11, 59, 59, 99).withZone(Constants.timeZone);
        interval = new Interval(startSunday, endSunday);

        if (interval.contains(DateTime.now().withZone(Constants.timeZone))) {
            day = 3;
        }

        if (day == -1) {
            return true;
        } else {
            return false;
        }
    }


    public static int getCurrentDay(Context context) {

        Interval interval;
        int day = 0;

        if (!Shambhala.getFestivalYear(context).equals(Shambhala.CURRENT_YEAR)) {
            return 0;
        }

        DateTime startThursday = new DateTime(2017, 8, 10, 11, 0, 0, 0).withZone(Constants.timeZone);
        DateTime endThursday = new DateTime(2017, 8, 11, 10, 59, 59, 99).withZone(Constants.timeZone);
        interval = new Interval(startThursday, endThursday);

        if (interval.contains(DateTime.now().withZone(Constants.timeZone))) {
            day = 0;
        }

        DateTime startFriday = new DateTime(2017, 8, 11, 11, 0, 0, 0).withZone(Constants.timeZone);
        DateTime endFriday = new DateTime(2017, 8, 12, 10, 59, 59, 99).withZone(Constants.timeZone);
        interval = new Interval(startFriday, endFriday);

        if (interval.contains(DateTime.now().withZone(Constants.timeZone))) {
            day = 1;
        }

        DateTime startSaturday = new DateTime(2017, 8, 12, 11, 0, 0, 0).withZone(Constants.timeZone);
        DateTime endSaturday = new DateTime(2017, 8, 13, 10, 59, 59, 99).withZone(Constants.timeZone);
        interval = new Interval(startSaturday, endSaturday);

        if (interval.contains(DateTime.now().withZone(Constants.timeZone))) {
            day = 2;
        }

        DateTime startSunday = new DateTime(2017, 8, 13, 11, 0, 0, 0).withZone(Constants.timeZone);
        DateTime endSunday = new DateTime(2017, 8, 14, 11, 59, 59, 99).withZone(Constants.timeZone);
        interval = new Interval(startSunday, endSunday);

        if (interval.contains(DateTime.now().withZone(Constants.timeZone))) {
            day = 3;
        }

        return day;

    }

    public static DateTime getFullDateTimeForArtist(Artist artist) {

        int day = artist.getDay();

        //TODO THIS IS NOT TRUE!  Not all artists had a leading 0 (OOPS!) and cedar lounge had one starting at 10:00am
        //Since I don't have full dates available, and no sets begin before 10:00AM, 0 means next day
        boolean addDay = artist.getStartTimeString().substring(0, 1).equals("0");

        //This is a really lame exception I have to add due to the awkwardness of making different days start at different times
        if (artist.getYear() == 2017 && artist.getArtistName().equals("Marty Carter and Russ")) {
            addDay = true;
        }

        if (addDay) {
            day = getDayOfWeek(day, artist.getYear()) + 1;
        } else {
            day = getDayOfWeek(day, artist.getYear());
        }

        String[] setTime = artist.getStartTimeString().split(":");

        return new DateTime(artist.getYear(), 8, day, Integer.valueOf(setTime[0]), Integer.valueOf(setTime[1]), 0, 0).withZone(Constants.timeZone);
    }

    public static DateTime getFullDateEndTimeForArtist(Artist artist) {

        int day = artist.getDay();

        //TODO THIS IS NOT TRUE!  Not all artists had a leading 0 (OOPS!) and cedar lounge had one starting at 10:00am
        //Since I don't have full dates available, and no sets begin before 10:00AM, 0 means next day
        boolean addDay = artist.getEndTimeString().substring(0, 1).equals("0") || artist.getStartTimeString().substring(0, 1).equals("0");

        //This is a really lame exception I have to add due to the awkwardness of making different days start at different times
        if (artist.getYear() == 2017 && artist.getArtistName().equals("Marty Carter and Russ")) {
            addDay = true;
        }

        if (addDay) {
            day = getDayOfWeek(day, artist.getYear()) + 1;
        } else {
            day = getDayOfWeek(day, artist.getYear());
        }

        String[] setTime = artist.getEndTimeString().split(":");

        return new DateTime(artist.getYear(), 8, day, Integer.valueOf(setTime[0]), Integer.valueOf(setTime[1]), 0, 0).withZone(Constants.timeZone);
    }


    private static int getDayOfWeek(int day, int year) {

        switch (year) {
            case 2015:
                day = get2015DayOfWeek(day);
                break;
            case 2016:
                day = get2016DayOfWeek(day);
                break;
            case 2017:
                day = get2017DayOfWeek(day);
                break;
        }

        return day;

    }

    private static int get2015DayOfWeek(int day) {
        switch (day) {

            case 0:
                day = 6;
                break;
            case 1:
                day = 7;
                break;
            case 2:
                day = 8;
                break;
            case 3:
                day = 9;
                break;
        }
        return day;
    }

    private static int get2016DayOfWeek(int day) {
        switch (day) {

            case 0:
                day = 4;
                break;
            case 1:
                day = 5;
                break;
            case 2:
                day = 6;
                break;
            case 3:
                day = 7;
                break;
        }
        return day;
    }

    private static int get2017DayOfWeek(int day) {
        switch (day) {

            case 0:
                day = 10;
                break;
            case 1:
                day = 11;
                break;
            case 2:
                day = 12;
                break;
            case 3:
                day = 13;
                break;
        }
        return day;
    }


    public static DateTime getEndOfFestivalGridDate(Context context) {

        String festivalYear = Shambhala.getFestivalYear(context);

        DateTime endTime;

        switch (festivalYear) {

            case "2015":
                endTime = new DateTime(2015, 8, 10, 11, 0, 0, 0).withZone(Constants.timeZone);
                break;
            case "2016":
                endTime = new DateTime(2016, 8, 8, 11, 0, 0, 0).withZone(Constants.timeZone);
                break;
            case "2017":
                endTime = new DateTime(2017, 8, 14, 11, 0, 0, 0).withZone(Constants.timeZone);
                break;
            default:
                endTime = new DateTime(2017, 8, 14, 11, 0, 0, 0).withZone(Constants.timeZone);
        }


        return endTime;
    }
}
