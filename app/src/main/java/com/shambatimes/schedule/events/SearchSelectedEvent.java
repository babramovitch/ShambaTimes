package com.shambatimes.schedule.events;

import com.shambatimes.schedule.Artist;

/**
 * Created by babramovitch on 8/16/2015.
 */
public class SearchSelectedEvent {

    public Artist artist;

    public SearchSelectedEvent(Artist artist){
        this.artist = artist;
    }

    public Artist getArtist(){
        return artist;
    }
}
