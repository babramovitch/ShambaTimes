package com.shambatimes.schedule.events;

import com.shambatimes.schedule.Artist;

import java.util.ArrayList;

public class ArtistListLoadDoneEvent {


    private ArrayList<Artist> artists;

    public ArtistListLoadDoneEvent(ArrayList<Artist> artists) {
        this.artists = artists;
    }

    public ArrayList<Artist> getArtists() {
        return artists;
    }


}