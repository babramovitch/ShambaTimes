package com.shambatimes.schedule.Util;

import android.content.Context;

import com.shambatimes.schedule.Constants;
import com.shambatimes.schedule.Shambhala;

import org.joda.time.DateTime;
import org.joda.time.Interval;

/**
 * Created by Ben on 21/02/2015.
 */
public final class DateUtils {

    public static int getCurrentTimePosition(Context context) {

        if(DateUtils.getCurrentDay() == 3 && Shambhala.getFestivalYear(context).equals("2015")){
            Constants.REFERENCE_TIME = Constants.SUNDAY_REFERENCE_TIME;
        }else{
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

    public static boolean isPrePostFestival() {

        Interval interval;
        int day = -1;

        DateTime startThursday = new DateTime(2015, 8, 6, 10, 00, 0, 0).withZone(Constants.timeZone);
        DateTime endThursday = new DateTime(2015, 8, 7, 9, 59, 59, 99).withZone(Constants.timeZone);
        interval = new Interval(startThursday, endThursday);

        if (interval.contains(DateTime.now().withZone(Constants.timeZone))) {
            day = 0;
        }

        DateTime startFriday = new DateTime(2015, 8, 7, 10, 00, 0, 0).withZone(Constants.timeZone);
        DateTime endFriday = new DateTime(2015, 8, 8, 9, 59, 59, 99).withZone(Constants.timeZone);
        interval = new Interval(startFriday, endFriday);

        if (interval.contains(DateTime.now().withZone(Constants.timeZone))) {
            day = 1;
        }

        DateTime startSaturday = new DateTime(2015, 8, 8, 10, 00, 0, 0).withZone(Constants.timeZone);
        DateTime endSaturday = new DateTime(2015, 8, 9, 9, 59, 59, 99).withZone(Constants.timeZone);
        interval = new Interval(startSaturday, endSaturday);

        if (interval.contains(DateTime.now().withZone(Constants.timeZone))) {
            day = 2;
        }

        DateTime startSunday = new DateTime(2015, 8, 9, 10, 00, 0, 0).withZone(Constants.timeZone);
        DateTime endSunday = new DateTime(2015, 8, 10, 9, 59, 59, 99).withZone(Constants.timeZone);
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


    public static int getCurrentDay() {

        Interval interval;
        int day = 0;

        DateTime startThursday = new DateTime(2015, 8, 6, 10, 00, 0, 0).withZone(Constants.timeZone);
        DateTime endThursday = new DateTime(2015, 8, 7, 9, 59, 59, 99).withZone(Constants.timeZone);
        interval = new Interval(startThursday, endThursday);

        if (interval.contains(DateTime.now().withZone(Constants.timeZone))) {
            day = 0;
        }

        DateTime startFriday = new DateTime(2015, 8, 7, 10, 00, 0, 0).withZone(Constants.timeZone);
        DateTime endFriday = new DateTime(2015, 8, 8, 9, 59, 59, 99).withZone(Constants.timeZone);
        interval = new Interval(startFriday, endFriday);

        if (interval.contains(DateTime.now().withZone(Constants.timeZone))) {
            day = 1;
        }

        DateTime startSaturday = new DateTime(2015, 8, 8, 10, 00, 0, 0).withZone(Constants.timeZone);
        DateTime endSaturday = new DateTime(2015, 8, 9, 9, 59, 59, 99).withZone(Constants.timeZone);
        interval = new Interval(startSaturday, endSaturday);

        if (interval.contains(DateTime.now().withZone(Constants.timeZone))) {
            day = 2;
        }

        DateTime startSunday = new DateTime(2015, 8, 9, 10, 00, 0, 0).withZone(Constants.timeZone);
        DateTime endSunday = new DateTime(2015, 8, 10, 9, 59, 59, 99).withZone(Constants.timeZone);
        interval = new Interval(startSunday, endSunday);

        if (interval.contains(DateTime.now().withZone(Constants.timeZone))) {
            day = 3;
        }

        return day;

    }

}
