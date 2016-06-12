package com.shambatimes.schedule.events;

public class ChangeDateEvent {
    private int position = 0;
    private boolean artistEvent = false;

    public ChangeDateEvent(int position) {
        this.position = position;
    }

    public ChangeDateEvent(int position, boolean artistEvent) {
        this.position = position;
        this.artistEvent = artistEvent;
    }

    public int getPosition() {
        return (position);
    }

    public boolean isArtistEvent() {
        return artistEvent;
    }
}
