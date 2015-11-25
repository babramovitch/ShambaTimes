package com.shambatimes.schedule.events;

import com.shambatimes.schedule.Artist;


public class ShowHideAlarmSnackbarEvent {

    Artist artist;

    public ShowHideAlarmSnackbarEvent(Artist artist) {
        this.artist = artist;
    }

    public Artist getArtist() {
        return (artist);
    }
}