package com.shambatimes.schedule.events;

public class ChangeDateEvent {
    private int position = 0;

    public ChangeDateEvent(int position) {
        this.position = position;
    }

    public int getPosition() {
        return (position);
    }
}