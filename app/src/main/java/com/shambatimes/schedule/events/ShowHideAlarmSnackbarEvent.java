package com.shambatimes.schedule.events;

import android.widget.ImageView;

import com.shambatimes.schedule.Artist;


public class ShowHideAlarmSnackbarEvent {

    Artist artist;
    ImageView imageView;

    public ShowHideAlarmSnackbarEvent(Artist artist, ImageView imageView) {
        this.artist = artist;
        this.imageView = imageView;
    }

    public Artist getArtist() {
        return (artist);
    }

    public ImageView getImageView() {
        return imageView;
    }
}