package com.shambatimes.schedule;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

import org.joda.time.DateTime;
import org.joda.time.Minutes;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;


/**
 * Created by Ben on 14/02/2015.
 */

public class Artist extends SugarRecord<Artist> {

    private int stage;

    private int startPosition;
    private int endPosition;
    private int day;

    private String artistName;
    private String stageName;
    private String startTimeString;
    private String endTimeString;

    @Ignore
    private DateTime startTime,endTime;


    //private boolean reminderEnabled = false;
    private boolean favorite = false;

    @Ignore

    public Artist(){

    }

    public Artist(int stage, int day, String startTime, String endTime, String artistName) {

        this.stage = stage;
        this.startTimeString = startTime;
        this.endTimeString = endTime;
        this.day = day;
        this.startTime = convertStringTimeToPosition(startTime);
        this.endTime = convertStringTimeToPosition(endTime);
        this.artistName = artistName;

        DateTime baseTime;
        if(day != 3) {
             baseTime = DateTime.now().withZone(Constants.timeZone).withTimeAtStartOfDay().plusMinutes(Constants.GENERAL_REFERENCE_TIME);
        }else{
             baseTime = DateTime.now().withZone(Constants.timeZone).withTimeAtStartOfDay().plusMinutes(Constants.SUNDAY_REFERENCE_TIME);
        }

        DateTimeFormatter dateStringFormat = DateTimeFormat.forPattern("HH:mm");
        String test = dateStringFormat.print(baseTime);

        baseTime = convertStringTimeToPosition(test);

        Minutes minutesToStart = Minutes.minutesBetween(baseTime, this.startTime);
        Minutes minutesToEnd = Minutes.minutesBetween(baseTime, this.endTime);

        startPosition = minutesToStart.getMinutes() / 30;
        endPosition = minutesToEnd.getMinutes() / 30;

        //To handle the midnight cross over
        if (startPosition < 0) startPosition += 48;
        if (endPosition < 0) endPosition += 48;

    }

    private DateTime convertStringTimeToPosition(String stringTime) {

        DateTimeFormatter formatter = DateTimeFormat.forPattern("HH:mm");
        DateTime dateTime = formatter.parseDateTime(stringTime);

        return dateTime;
    }


    public String getStartTimeString() {
        return startTimeString;
    }

    public void setStartTimeString(String startTimeString) {
        this.startTimeString = startTimeString;
    }

    public String getEndTimeString() {
        return endTimeString;
    }

    public void setEndTimeString(String endTimeString) {
        this.endTimeString = endTimeString;
    }

    public int getStage() {
        return stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

    public int getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(int startPosition) {
        this.startPosition = startPosition;
    }

    public int getEndPosition() {
        return endPosition;
    }

    public void setEndPosition(int endPosition) {
        this.endPosition = endPosition;
    }

    public String getAristName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public DateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(DateTime startTime) {
        this.startTime = startTime;
    }

    public DateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(DateTime endTime) {
        this.endTime = endTime;
    }

    //public boolean isReminderEnabled() {
    //    return reminderEnabled;
    //}

    //public void setReminderEnabled(boolean reminderEnabled) {
    //    this.reminderEnabled = reminderEnabled;
    //}

   public boolean isFavorite() {
        return favorite;
   }

   public void setFavorite(boolean favorite) {
        this.favorite = favorite;
   }
}
