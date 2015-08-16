package com.shambatimes.schedule.events;

public class DataChangedEvent {
    boolean isChanged = false;
    long artistId = -1;

    public DataChangedEvent(boolean updated, long artistId) {
        this.isChanged = updated;
        this.artistId = artistId;
    }

    public boolean isChanged() {
        return (isChanged);
    }

    public long getArtistId(){
        return artistId;
    }
}