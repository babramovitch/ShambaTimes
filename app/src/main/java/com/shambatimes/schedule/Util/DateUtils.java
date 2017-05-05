package com.shambatimes.schedule.Util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

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

    public static DateTimeFormatter getTimeFormat(String format){

        DateTimeFormatter dateStringFormat;

        if(format.equals("24")){
            dateStringFormat = DateTimeFormat.forPattern("HH:mm");
        }else{
            dateStringFormat = DateTimeFormat.forPattern("h:mm aa");
        }

        return dateStringFormat;
    }

    public static DateTimeFormatter getTimeFormatTwo(String format){

        DateTimeFormatter dateStringFormat;

        if(format.equals("24")){
            dateStringFormat = DateTimeFormat.forPattern("HH:mm aa");
        }else{
            dateStringFormat = DateTimeFormat.forPattern("h:mm aa");
        }

        return dateStringFormat;
    }

    public static String formatTime(DateTimeFormatter dateStringFormat, String time){
        return dateStringFormat.print(new LocalTime(time, Constants.timeZone));
    }

    public static int getCurrentTimePosition(Context context) {

        if (DateUtils.getCurrentDay(context) == 3 && Shambhala.getFestivalYear(context).equals("2015")) {
            Constants.REFERENCE_TIME = Constants.SUNDAY_REFERENCE_TIME;
        } else {
            Constants.REFERENCE_TIME = Constants.GENERAL_REFERENCE_TIME;
        }

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

        DateTime startThursday = new DateTime(2016, 8, 4, 11, 0, 0, 0).withZone(Constants.timeZone);
        DateTime endThursday = new DateTime(2016, 8, 5, 10, 59, 59, 99).withZone(Constants.timeZone);
        interval = new Interval(startThursday, endThursday);

        if (interval.contains(DateTime.now().withZone(Constants.timeZone))) {
            day = 0;
        }

        DateTime startFriday = new DateTime(2016, 8, 5, 11, 0, 0, 0).withZone(Constants.timeZone);
        DateTime endFriday = new DateTime(2016, 8, 6, 10, 59, 59, 99).withZone(Constants.timeZone);
        interval = new Interval(startFriday, endFriday);

        if (interval.contains(DateTime.now().withZone(Constants.timeZone))) {
            day = 1;
        }

        DateTime startSaturday = new DateTime(2016, 8, 6, 11, 0, 0, 0).withZone(Constants.timeZone);
        DateTime endSaturday = new DateTime(2016, 8, 7, 10, 59, 59, 99).withZone(Constants.timeZone);
        interval = new Interval(startSaturday, endSaturday);

        if (interval.contains(DateTime.now().withZone(Constants.timeZone))) {
            day = 2;
        }

        DateTime startSunday = new DateTime(2016, 8, 7, 11, 0, 0, 0).withZone(Constants.timeZone);
        DateTime endSunday = new DateTime(2016, 8, 8, 11, 59, 59, 99).withZone(Constants.timeZone);
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

        DateTime startThursday = new DateTime(2016, 8, 4, 11, 0, 0, 0).withZone(Constants.timeZone);
        DateTime endThursday = new DateTime(2016, 8, 5, 10, 59, 59, 99).withZone(Constants.timeZone);
        interval = new Interval(startThursday, endThursday);

        if (interval.contains(DateTime.now().withZone(Constants.timeZone))) {
            day = 0;
        }

        DateTime startFriday = new DateTime(2016, 8, 5, 11, 0, 0, 0).withZone(Constants.timeZone);
        DateTime endFriday = new DateTime(2016, 8, 6, 10, 59, 59, 99).withZone(Constants.timeZone);
        interval = new Interval(startFriday, endFriday);

        if (interval.contains(DateTime.now().withZone(Constants.timeZone))) {
            day = 1;
        }

        DateTime startSaturday = new DateTime(2016, 8, 6, 11, 0, 0, 0).withZone(Constants.timeZone);
        DateTime endSaturday = new DateTime(2016, 8, 7, 10, 59, 59, 99).withZone(Constants.timeZone);
        interval = new Interval(startSaturday, endSaturday);

        if (interval.contains(DateTime.now().withZone(Constants.timeZone))) {
            day = 2;
        }

        DateTime startSunday = new DateTime(2016, 8, 7, 11, 0, 0, 0).withZone(Constants.timeZone);
        DateTime endSunday = new DateTime(2016, 8, 8, 11, 59, 59, 99).withZone(Constants.timeZone);
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

        if (addDay) {
            day = getDayOfWeek(day) + 1;
        } else {
            day = getDayOfWeek(day);
        }

        String[] setTime = artist.getStartTimeString().split(":");

        return new DateTime(artist.getYear(), 8, day, Integer.valueOf(setTime[0]), Integer.valueOf(setTime[1]), 0, 0).withZone(Constants.timeZone);
    }

    private static int getDayOfWeek(int day) {

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
}
