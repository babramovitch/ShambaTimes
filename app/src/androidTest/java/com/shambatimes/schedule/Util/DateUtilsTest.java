package com.shambatimes.schedule.Util;

import com.shambatimes.schedule.Constants;

import junit.framework.TestCase;

import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;

public class DateUtilsTest extends TestCase {

    public void testGetCurrentTimePosition() throws Exception {

        //Test that I get back the correct position for the day, for every starting reference time
        //and every minute thereafter
        long testTime;

        for(int x = 0; x < 48;x++) {  //There are 48 30minute chunks in a day
            DateTimeUtils.setCurrentMillisSystem();
            Constants.REFERENCE_TIME = x*30;
            DateTime testDate = DateTime.now().withZone(Constants.timeZone).withTimeAtStartOfDay();
            for (int i = 0; i < Constants.MINUTES_IN_DAY; i++) {
                testTime = testDate.plusMinutes(i + Constants.REFERENCE_TIME).getMillis();
                DateTimeUtils.setCurrentMillisFixed(testTime);
                assertEquals(DateUtils.getCurrentTimePosition(), i / 30);
            }
        }

        //Reset the default time
        DateTimeUtils.setCurrentMillisSystem();

    }


    public void testGetCurrentDay() throws Exception {

        DateTime testDate;

// Thursday

        testDate = new DateTime(2015, 8, 6, 10, 00, 0, 0);
        DateTimeUtils.setCurrentMillisFixed(testDate.withZone(Constants.timeZone).getMillis());
        assertEquals(DateUtils.getCurrentDay(), 0);

        testDate = new DateTime(2015, 8, 7, 9, 59, 0, 0);
        DateTimeUtils.setCurrentMillisFixed(testDate.withZone(Constants.timeZone).getMillis());
        assertEquals(DateUtils.getCurrentDay(), 0);

// Friday

        testDate = new DateTime(2015, 8, 7, 10, 0, 0, 0);
        DateTimeUtils.setCurrentMillisFixed(testDate.withZone(Constants.timeZone).getMillis());
        assertEquals(DateUtils.getCurrentDay(), 1);

        testDate = new DateTime(2015, 8, 8, 9, 59, 0, 0);
        DateTimeUtils.setCurrentMillisFixed(testDate.withZone(Constants.timeZone).getMillis());
        assertEquals(DateUtils.getCurrentDay(), 1);

// Saturday

        testDate = new DateTime(2015, 8, 8, 10, 0, 0, 0);
        DateTimeUtils.setCurrentMillisFixed(testDate.withZone(Constants.timeZone).getMillis());
        assertEquals(DateUtils.getCurrentDay(), 2);

        testDate = new DateTime(2015, 8, 9, 9, 59, 0, 0);
        DateTimeUtils.setCurrentMillisFixed(testDate.withZone(Constants.timeZone).getMillis());
        assertEquals(DateUtils.getCurrentDay(), 2);

// Sunday

        testDate = new DateTime(2015, 8, 9, 10, 0, 0, 0);
        DateTimeUtils.setCurrentMillisFixed(testDate.withZone(Constants.timeZone).getMillis());
        assertEquals(DateUtils.getCurrentDay(), 3);

        testDate = new DateTime(2015, 8, 10, 9, 59, 0, 0);
        DateTimeUtils.setCurrentMillisFixed(testDate.withZone(Constants.timeZone).getMillis());
        assertEquals(DateUtils.getCurrentDay(), 3);

// Post Festival

        testDate = new DateTime(2015, 8, 6, 10, 00, 0, 0);
        DateTimeUtils.setCurrentMillisFixed(testDate.withZone(Constants.timeZone).getMillis());
        assertEquals(DateUtils.getCurrentDay(), 0);

// Pre Festival

        testDate = new DateTime(2015, 8, 6, 5, 0, 0, 0);
        DateTimeUtils.setCurrentMillisFixed(testDate.withZone(Constants.timeZone).getMillis());
        assertEquals(DateUtils.getCurrentDay(), 0);

// Reset time to normal

        DateTimeUtils.setCurrentMillisSystem();

    }
}